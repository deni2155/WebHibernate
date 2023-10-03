package com.kindcat.archivemedo.servlet.member.update;

import com.kindcat.archivemedo.beans.SuperBean;
import com.kindcat.archivemedo.beans.SuperBeanImpl;
import com.kindcat.archivemedo.db.dao.ImplDao;
import com.kindcat.archivemedo.db.dao.SuperDao;
import com.kindcat.archivemedo.members.regex.SuperVerification;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.kindcat.archivemedo.members.regex.ImplVerification;

/**
 *
 * @author dreamer
 * @version 1.0.0.31 Сервлет для обновления участников МЭДО
 */
@WebServlet(name = "UpdateMemberServlet", urlPatterns = {"/updateMemberServlet"})
public class UpdateMemberServlet extends HttpServlet {

    private final Logger logger;
    private final StringBuilder logBuilder;
    private StackTraceElement[] stackTrace;
    private String stringlog;

    public UpdateMemberServlet() {
        logger = Logger.getLogger(UpdateMemberServlet.class);
        logBuilder = new StringBuilder();
    }

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //получаю сеществующую сессию
        HttpSession session = request.getSession(false);
        String login = session.getAttribute("login").toString();
        String logString = null;//строка записи информации в лог
        try (PrintWriter out = response.getWriter()) {
            //если пользователь отправил форму не с пустыми полями
            if (request.getParameter("idUpdateMember") != null && request.getParameter("nameOrgUpdateMember") != null && request.getParameter("emailUpdateMember") != null && request.getParameter("guidUpdateMember") != null) {
                //получаю ссылку на класс для работы с данными формы            
                SuperBeanImpl beans = new SuperBean();
                //добавляю в класс значения, полученные из запроса для более удобной работы
                beans.setBeanIdOrg(Integer.parseInt(request.getParameter("idUpdateMember")));
                beans.setBeanNameOrg(request.getParameter("nameOrgUpdateMember"));
                beans.setBeanEmailOrg(request.getParameter("emailUpdateMember"));
                beans.setBeanGuidOrg(request.getParameter("guidUpdateMember"));

                //создаю ссылку на класс для проверки валидности отправленных пользователем данных
                ImplVerification memberProcess = new SuperVerification(login, beans.getBeanNameOrg(), beans.getBeanEmailOrg(), beans.getBeanGuidOrg());
                //проверяю, чтобы отправленные данные пользователя соответствовали регулярным выражения
                if (memberProcess.verifProcessRegex()) {
                    ImplDao memberDao = new SuperDao();

                    //проверяю наличие в БД участника МЭДО по email и GUID
                    logger.debug("Получен идентификатор записи в БД " + beans.getBeanIdOrg());
//
//
//
//Если для участника, которого хочет изменить пользователь, в бд есть документы
///запретить менять реквизиты организации, что бы ничего не сломалось
//
//
//
                    //если с полученными данными от пользователя (с email не найдено записей и c GUID не найдено записей) не найдено записей
                    if (memberDao.getCountMembersByEmailOrGuidAndNotEqualsId(beans.getBeanIdOrg(), beans.getBeanEmailOrg(), beans.getBeanGuidOrg()) == 0) {
                        //изменяем запись
                        if (memberDao.updateOldMember(beans.getBeanIdOrg(), beans.getBeanNameOrg(), beans.getBeanEmailOrg(), beans.getBeanGuidOrg()) > 0) {
                            logBuilder.setLength(0);
                            logBuilder.append("Пользователь \"");
                            logBuilder.append(login);
                            logBuilder.append("\" успешно изменил участника МЭДО с идентификатором \"");
                            logBuilder.append(beans.getBeanIdOrg());
                            logBuilder.append("\"");
                            logString = logBuilder.toString();
                            logger.info(logString);
                            out.println("green");
                            out.print("Изменения сохранены");
                        } else {
                            logBuilder.setLength(0);
                            logBuilder.append("При изменении участника МЭДО \"");
                            logBuilder.append(beans.getBeanNameOrg());
                            logBuilder.append("\"");
                            logBuilder.append("\" c GUID \"");
                            logBuilder.append(beans.getBeanGuidOrg());
                            logBuilder.append("\" и email \"");
                            logBuilder.append(beans.getBeanEmailOrg());
                            logBuilder.append("\" пользователем \"");
                            logBuilder.append(login);
                            logBuilder.append("\" возникла программная ошибка.\r\n\tКласс - MembersDao, процедура - updateMember");
                            logString = logBuilder.toString();
                            logger.info(logString);
                            out.println("red");
                            out.print("Изменения не сохранены");
                        }
                        //если с полученными от пользователя данными найдено больше одной записи
                    } else {
                        logBuilder.setLength(0);
                        logBuilder.append("Пользователь \"");
                        logBuilder.append(login);
                        logBuilder.append("\" пытался изменить участника МЭДО с идентификатором \"");
                        logBuilder.append(beans.getBeanIdOrg());
                        logBuilder.append("\". Изменения отклонены, т.к. от пользователя получены данные организации, с которыми в системе существует другая организация");
                        logString = logBuilder.toString();
                        logger.info(logString);
                        out.println("red");
                        out.print("Указанный адресат или уникальный идентификатор участника  \"" + beans.getBeanNameOrg() + "\" присутствует в системе");
                    }
                    //отправленные данные пользователя для добавления участника МЭДО не соответствуют регулярным выражения
                } else {
                    logger.info("Пользователь \"" + login + "\" умудрился отправить форму с полями, не соответствующими регулярным выражениям");
                }
                //memberProcess.addMemberProcess(session.getAttribute("login").toString(), beans.getBeanNameOrg(), beans.getBeanEmailOrg(), beans.getBeanGuidOrg());
                //форма отправлена с пустыми полями
            } else {
                logger.info("Пользователь \"" + login + "\" умудрился отправить форму для изменения участников МЭДО с пустыми полями");
            }
        } catch (Exception ex) {
            logBuilder.setLength(0);
            logBuilder.append("При изменении участника МЭДО возникла программная ошибка");
            logBuilder.append("\n");
            logBuilder.append(ex.getMessage());
            logBuilder.append("\n");
            stackTrace = ex.getStackTrace();
            for (StackTraceElement element : stackTrace) {
                logBuilder.append(element.toString());
                logBuilder.append("\n");
            }
            stringlog = logBuilder.toString();
            logger.error(stringlog);
        }

    }

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
