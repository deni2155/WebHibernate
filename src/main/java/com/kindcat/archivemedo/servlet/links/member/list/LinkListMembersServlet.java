package com.kindcat.archivemedo.servlet.links.member.list;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dreamer
 * @verion 1.0.0.10 переход на страницу со списком участников МЭДО
 */
@WebServlet(name = "LinkListMembersServlet", urlPatterns = {"/linkListMembersServlet"})
public class LinkListMembersServlet extends HttpServlet {

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/members/list_members.jsp").forward(request, response);
//        Logger logger = Logger.getLogger(LinkListMembersServlet.class);
//        ImplDao membersDao = new SuperDao();
//
//        int membersCountForOnePage = 20;//число записей на одной странице
//        int countMembers = Math.toIntExact(membersDao.getAllCountMembers());
//        int pageCount = countMembers / membersCountForOnePage;//получаю число страниц через деление общего числа записей в БД на число записей на одной странице
//        int page = 0;
//        String link = "/pages/members/listShow.jsp";
//        if (request.getAttribute("page") != null) {
//            page = (int) request.getAttribute("page");
//            link = link + "page=" + page;
//        }
//        int skip = membersCountForOnePage * page;
//        request.setAttribute("listMembers", membersDao.getAllListMembers(0, 20));
//        request.setAttribute("pageCount", pageCount);
//
//        if (membersDao.getAllListMembers(0, 20).isEmpty()) {
//            logger.info("Получен пустой массив со списком участников МЭДО");
//        }
//        request.getRequestDispatcher("/pages/members/listShow.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
