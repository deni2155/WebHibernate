package com.kindcat.archivemedo.servlet.member.delete;

import com.kindcat.archivemedo.beans.SuperBeans;
import com.kindcat.archivemedo.db.dao.ImplDao;
import com.kindcat.archivemedo.db.dao.SuperDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.kindcat.archivemedo.beans.ImplBeans;

/**
 *
 * @author dreamer
 * @version 1.0.0.32
 */
@WebServlet(name = "DeleteMemberServlet", urlPatterns = {"/deleteMemberServlet"})
public class DeleteMemberServlet extends HttpServlet {

    private final Logger logger;
    private String stringlog;
    private final StringBuilder logBuilder;
    private StackTraceElement[] stackTrace;

    public DeleteMemberServlet() {
        logger = Logger.getLogger(DeleteMemberServlet.class);
        logBuilder = new StringBuilder();
    }

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //если в бд есть документы, связанные с участником МЭДО
        //возвращаем ответ, что удаление участника не возможно
        //иначе, удаляем

        //получаю сеществующую сессию
        HttpSession session = request.getSession(false);
        String login = session.getAttribute("login").toString();
        //проверяю наличие в БД участника МЭДО по email и GUID
        try (PrintWriter out = response.getWriter()) {
            //получаю ссылку на класс для работы с данными формы            
            ImplBeans beans = new SuperBeans();
            //добавляю в класс значения, полученные из запроса для более удобной работы
            if (request.getParameter("idDeleteMember") != null && request.getParameter("nameDeleteMember") != null) {
                beans.setBeanIdOrg(Short.parseShort(request.getParameter("idDeleteMember")));
                beans.setBeanNameOrg(request.getParameter("nameDeleteMember"));
                ImplDao memberDao = new SuperDao();
                if (memberDao.deleteOldMember(beans.getBeanIdOrg()) > 0) {
                    logBuilder.setLength(0);
                    logBuilder.append("Пользователь \"");
                    logBuilder.append(login);
                    logBuilder.append("\" успешно удалил участника МЭДО \"");
                    logBuilder.append(beans.getBeanNameOrg());
                    logBuilder.append("\"");
                    stringlog = logBuilder.toString();
                    logger.info(stringlog);
                    out.println("green");
                    out.print("Участник успешно удалён");
                } else {
                    logBuilder.setLength(0);
                    logBuilder.append("При удалении участника МЭДО \"");
                    logBuilder.append(beans.getBeanNameOrg());
                    logBuilder.append("\"");
                    logBuilder.append("\" c идентификатором \"");
                    logBuilder.append(beans.getBeanIdOrg());
                    logBuilder.append("\" пользователем \"");
                    logBuilder.append(login);
                    logBuilder.append("\" возникла программная ошибка.\r\n\tКласс - MembersDao, процедура - deleteMember");
                    stringlog = logBuilder.toString();
                    logger.info(stringlog);
                    out.println("red");
                    out.print("Изменения не сохранены");
                }
            } else {
                logger.info("Пользователь \"" + login + "\" умудрился отправить форму с пустими полями для удаления участника");
            }
        } catch (Exception ex) {
            logBuilder.setLength(0);
            logBuilder.append("При удалении участника МЭДО возникла программная ошибка");
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
