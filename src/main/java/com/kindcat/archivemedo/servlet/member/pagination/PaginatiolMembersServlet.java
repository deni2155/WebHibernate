package com.kindcat.archivemedo.servlet.member.pagination;

import com.kindcat.archivemedo.db.dao.ImplDao;
import com.kindcat.archivemedo.db.dao.SuperDao;
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
 * @version 1.0.1.35
Класс для пагинации в разделе со списком участников МЭДО
 */
@WebServlet(name = "PaginatiolMembersServlet", urlPatterns = {"/paginatiolMembersServlet"})
public class PaginatiolMembersServlet extends HttpServlet {

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger logger = Logger.getLogger(PaginatiolMembersServlet.class);
        ImplDao membersDao = new SuperDao();

        int membersCountForOnePage = 20;//число записей на одной странице
        int countMembers = Math.toIntExact(membersDao.getAllCountMembers());//общее число записей в БД
        int pageCount = countMembers / membersCountForOnePage;//получаю число страниц через деление общего числа записей в БД на число записей на одной странице
        int page = 0;
        String link = "linkListMembersServlet";
        if (request.getAttribute("page") != null) {
            page = (int) request.getAttribute("page");
            link = link + "page=" + page;
        }
        int skip = membersCountForOnePage * page;//число пропущенных записей
        request.setAttribute("listMembers", membersDao.getAllListMembers(skip, 20));//массив с записями для отображения на странице
        request.setAttribute("pageCount", pageCount);//число странице для отображения
        if (membersDao.getAllListMembers(skip, 20).isEmpty()) {
            logger.info("Получен пустой массив со списком участников МЭДО");
        } else {
            request.getRequestDispatcher("/"+link).forward(request, response);
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
