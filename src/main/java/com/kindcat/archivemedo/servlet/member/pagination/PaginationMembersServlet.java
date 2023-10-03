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
 * @version 1.0.1.35 Класс для пагинации в разделе со списком участников МЭДО
 */
@WebServlet(name = "PaginationMembersServlet", urlPatterns = {"/paginationMembersServlet"})
public class PaginationMembersServlet extends HttpServlet {

    private final Logger logger;
    private final ImplDao membersDao;

    public PaginationMembersServlet() {
        logger = Logger.getLogger(PaginationMembersServlet.class);
        membersDao = new SuperDao();
    }

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int membersCountForOnePage = 20;//число записей на одной странице
        int countMembers = Math.toIntExact(membersDao.getAllCountMembers());//общее число записей в БД
        int pageCount = countMembers / membersCountForOnePage;//получаю число страниц через деление общего числа записей в БД на число записей на одной странице
        int currentPage = 1;//выбранная страинца для расчёта пагинации
        int skip = 0;//число пропущенных записей
        String link = "linkListMembersServlet";

        //если остаток от деления больше нуля при делении общего числа записей на число записей на одной странице, добавляем одну страницу
        if ((countMembers % membersCountForOnePage) > 0) {
            pageCount++;
        }

        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        //если от пользователя получен номер страницы, превышающий общее число страниц
        if (currentPage > pageCount) {
            currentPage = pageCount;//отправляем пользователя на последнюю страницу
        }//если от пользователя получен номер страницы меньше нуля, отпавляем на персую страницу
        if (currentPage < 1) {
            currentPage = 1;
        }

        skip = membersCountForOnePage * (currentPage - 1);//число пропущенных записей

        request.setAttribute("listMembers", membersDao.getAllListMembers(skip, membersCountForOnePage));//массив с записями для отображения на странице
        request.setAttribute("pageCount", pageCount);//число странице для отображения
        request.setAttribute("currentPage", currentPage);//текущая страница

        if (membersDao.getAllListMembers(skip, membersCountForOnePage).isEmpty()) {
            logger.info("Получен пустой массив со списком участников МЭДО");
        }
        request.getRequestDispatcher(link).forward(request, response);
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
