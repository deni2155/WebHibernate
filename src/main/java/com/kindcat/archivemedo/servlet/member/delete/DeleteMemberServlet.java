package com.kindcat.archivemedo.servlet.member.delete;

import com.kindcat.archivemedo.beans.SuperBean;
import com.kindcat.archivemedo.beans.SuperBeanImpl;
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

/**
 *
 * @author dreamer
 * @version 1.0.0.32
 */
@WebServlet(name = "DeleteMemberServlet", urlPatterns = {"/deleteMemberServlet"})
public class DeleteMemberServlet extends HttpServlet {

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
        Logger logger = Logger.getLogger(DeleteMemberServlet.class);
        //получаю сеществующую сессию
        HttpSession session = request.getSession(false);
        String login = session.getAttribute("login").toString();
        //проверяю наличие в БД участника МЭДО по email и GUID
        StringBuilder logBuilder = new StringBuilder();
        String logString = null;//строка записи информации в лог
        try (PrintWriter out = response.getWriter()) {
            //получаю ссылку на класс для работы с данными формы            
            SuperBeanImpl beans = new SuperBean();
            //добавляю в класс значения, полученные из запроса для более удобной работы
            if (request.getParameter("idDeleteMember") != null && request.getParameter("nameDeleteMember") != null) {
                beans.setBeanIdOrg(Integer.parseInt(request.getParameter("idDeleteMember")));
                beans.setBeanNameOrg(request.getParameter("nameDeleteMember"));
                ImplDao memberDao = new SuperDao();
                if (memberDao.deleteOldMember(beans.getBeanIdOrg()) > 0) {
                    logBuilder.setLength(0);
                    logBuilder.append("Пользователь \"");
                    logBuilder.append(login);
                    logBuilder.append("\" успешно удалил участника МЭДО \"");
                    logBuilder.append(beans.getBeanNameOrg());
                    logBuilder.append("\"");
                    logString = logBuilder.toString();
                    logger.info(logString);
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
                    logString = logBuilder.toString();
                    logger.info(logString);
                    out.println("red");
                    out.print("Изменения не сохранены");
                }
            } else {
                logger.info("Пользователь \"" + login + "\" умудрился отправить форму с пустими полями для удаления участника");
            }
        } catch (Exception ex) {
            logger.error("При удалении участника МЭДО возникла программная ошибка " + ex);
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
