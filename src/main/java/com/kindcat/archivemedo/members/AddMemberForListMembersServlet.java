package com.kindcat.archivemedo.members;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author dreamer
 * @version 1.0.0.1 класс для добавления участников МЭДО
 */
@WebServlet(name = "AddMemberForListMembersServlet", urlPatterns = {"/addMemberForListMembersServlet"})
public class AddMemberForListMembersServlet extends HttpServlet {

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger logger = Logger.getLogger(AddMemberForListMembersServlet.class);
//        StringBuilder stringBuilder = new StringBuilder();
//        HttpSession session = request.getSession(false);//получаю текущую сессию
//        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("nameOrgMemberListGuides") != null && request.getParameter("emailMemberListGuides") != null && request.getParameter("guidMemberListGuides") != null) {
            String orgName = request.getParameter("nameOrgMemberListGuides");
            String orgEmail = request.getParameter("emailMemberListGuides");
            String orgGuid = request.getParameter("guidMemberListGuides");
            //соответствует ли полученная от пользователя информация регулярным вырабжения строки
            boolean verificationRegx = true;
            //если хотя бы одна строка не соответствует регулярному выражения, то проверка не выполнится
            if (!orgName.matches("^[а-яА-Я\\s]+$")) {
                verificationRegx = false;
            }
            if (!orgEmail.matches("^[A-Z~\\s]+$")) {
                verificationRegx = false;
            }
            if (!orgGuid.matches("^[a-z-]+$")) {
                verificationRegx = false;
            }
            //если значение переменных соответствует регулярным выражениям
            if (verificationRegx == true) {

                //значение переменных не соответствует регулярным выражениям
            } else {
                logger.info("Пользователь пытался добавить данные участника МЭДО, не соответствующие формату");
            }
        } else {
//            stringBuilder.append("Пользователь отправил запрос на добавление нового участника МЭДО с пустыми поля");
//            stringBuilder.append(session.getAttribute("login"));
//            stringBuilder.append(" отправил запрос на добавление нового участника МЭДО с пустыми поля");
//            logger.warn(" отправил запрос на добавление");
//            String messageForLog = stringBuilder.toString();
            logger.info("Пользователь отправил запрос на добавление нового участника МЭДО с пустыми поля");
        }
        request.getRequestDispatcher("/pages/guides/listGuides.jsp").forward(request, response);
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
