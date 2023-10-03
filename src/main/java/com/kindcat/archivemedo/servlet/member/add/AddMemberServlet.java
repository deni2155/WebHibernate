package com.kindcat.archivemedo.servlet.member.add;

import com.kindcat.archivemedo.beans.SuperBean;
import com.kindcat.archivemedo.beans.SuperBeanImpl;
import com.kindcat.archivemedo.db.dao.ImplDao;
import com.kindcat.archivemedo.db.dao.SuperDao;
import com.kindcat.archivemedo.members.regex.SuperVerification;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import java.io.PrintWriter;
import com.kindcat.archivemedo.members.regex.ImplVerification;

/**
 *
 * @author dreamer
 * @version 1.0.0.1 класс для добавления участников МЭДО
 */
@WebServlet(name = "AddMemberServlet", urlPatterns = {"/addMemberServlet"})
public class AddMemberServlet extends HttpServlet {

    private final Logger logger;
    private final StringBuilder logBuilder;
    private StackTraceElement[] stackTrace;
    private String stringlog;

    public AddMemberServlet() {
        logger = Logger.getLogger(AddMemberServlet.class);
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
        String logString = null;
        // Получаем объект PrintWriter
        try (PrintWriter out = response.getWriter()) {
            out.print("");
            //если пользователь отправил форму не с пустыми полями
            if (request.getParameter("nameOrgAddMember") != null && request.getParameter("emailAddMember") != null && request.getParameter("guidAddMember") != null) {
                //получаю ссылку на класс для работы с данными формы            
                SuperBeanImpl beans = new SuperBean();
                //добавляю в класс значения, полученные из запроса для более удобной работы
                //перед передачей данных в класс удаляем пробелы в начале и конце, а так же переносы строк заменяем на пробелы
                beans.setBeanNameOrg(deleteSpaceAndLineBreakReplacement(request.getParameter("nameOrgAddMember")));
                beans.setBeanEmailOrg(deleteSpaceAndLineBreakReplacement(request.getParameter("emailAddMember")));
                beans.setBeanGuidOrg(deleteSpaceAndLineBreakReplacement(request.getParameter("guidAddMember")));

                //создаю ссылку на класс для работы с БД
                ImplVerification memberProcess = new SuperVerification(session.getAttribute("login").toString(), beans.getBeanNameOrg(), beans.getBeanEmailOrg(), beans.getBeanGuidOrg());
                //проверяю, чтобы отправленные данные пользователя соответствовали регулярным выражения
                if (memberProcess.verifProcessRegex()) {
                    ImplDao memberDao = new SuperDao();
                    //если записей по указанным критериям нет
                    if (memberDao.getCountMembersByEmailOrGuid(beans.getBeanEmailOrg(), beans.getBeanGuidOrg()) == 0) {
                        //добавляем запись
                        if (memberDao.addNewMember(beans.getBeanNameOrg(), beans.getBeanEmailOrg(), beans.getBeanGuidOrg())) {
                            logBuilder.setLength(0);
                            logBuilder.append("Пользователь \"");
                            logBuilder.append(login);
                            logBuilder.append("\" успешно добавил добавил участника МЭДО \"");
                            logBuilder.append(beans.getBeanNameOrg());
                            logBuilder.append("\" c GUID \"");
                            logBuilder.append(beans.getBeanGuidOrg());
                            logBuilder.append("\" и email \"");
                            logBuilder.append(beans.getBeanEmailOrg());
                            logBuilder.append("\"");
                            logString = logBuilder.toString();
                            logger.info(logString);
                            out.println("green");
                            out.print("Участник успешно добавлен");
                        } else {
                            logBuilder.setLength(0);
                            logBuilder.append("При добавлении нового участника МЭДО \"");
                            logBuilder.append(beans.getBeanNameOrg());
                            logBuilder.append("\"");
                            logBuilder.append("\" c GUID \"");
                            logBuilder.append(beans.getBeanGuidOrg());
                            logBuilder.append("\" и email \"");
                            logBuilder.append(beans.getBeanEmailOrg());
                            logBuilder.append("\" пользователем \"");
                            logBuilder.append(login);
                            logBuilder.append("\" возникла программная ошибка.\r\n\tКласс - MembersDao, процедура - addMember");
                            logString = logBuilder.toString();
                            logger.info(logString);
                            out.println("red");
                            out.print("Участник не добавлен");
                        }
                    } else {
                        logBuilder.setLength(0);
                        logBuilder.append("Пользователь \"");
                        logBuilder.append(login);
                        logBuilder.append("\" пытался добавить в систему уже существующего участника МЭДО \"");
                        logBuilder.append(beans.getBeanNameOrg());
                        logBuilder.append("\" c GUID \"");
                        logBuilder.append(beans.getBeanGuidOrg());
                        logBuilder.append("\" и email \"");
                        logBuilder.append(beans.getBeanEmailOrg());
                        logBuilder.append("\"");
                        logString = logBuilder.toString();
                        logger.info(logString);
                        out.println("red");
                        out.print("Участник \"" + beans.getBeanNameOrg() + "\" присутствует в системе");
                    }
                    //отправленные данные пользователя для добавления участника МЭДО не соответствуют регулярным выражения
                } else {
                    logger.info("Пользователь \"" + login + "\" умудрился отправить форму с полями, не соответствующими регулярным выражениям");
                }
                //memberProcess.addMemberProcess(session.getAttribute("login").toString(), beans.getBeanNameOrg(), beans.getBeanEmailOrg(), beans.getBeanGuidOrg());
                //форма отправлена с пустыми полями
            } else {
                logger.info("Пользователь \"" + login + "\" умудрился отправить форму для добавления участников МЭДО с пустыми полями");
            }
            out.close();
        } catch (Exception ex) {
            logBuilder.setLength(0);
            logBuilder.append("При добавлении участника МЭДО возникла программная ошибка");
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
     * Удаляем пробелы в начале и конце, так же меняем перенос строки на пробел
     */
    private String deleteSpaceAndLineBreakReplacement(String lineSource) {
        String lineDeleteSpace = lineSource.trim();//удаляем пробелы
        String lineResult = lineDeleteSpace.replace("\n", " ");//замена переноса строки на пробелы
        return lineResult;
    }

    /**
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
