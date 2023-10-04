package com.kindcat.archivemedo.servlet.home.pagination;

import com.kindcat.archivemedo.db.dao.ImplDao;
import com.kindcat.archivemedo.db.dao.SuperDao;
import com.kindcat.archivemedo.db.models.Documents;
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
 * @version 1.0.3.39 Класс для отображения информации о квитанциях, уведомления,
 * документа
 */
@WebServlet(name = "PaginationArchiveServlet", urlPatterns = {"/paginationArchiveServlet"})
public class PaginationArchiveServlet extends HttpServlet {

    private final Logger logger;

    public PaginationArchiveServlet() {
        logger = Logger.getLogger(PaginationArchiveServlet.class);
    }

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String link = "linkArchiveServlet";
        ImplDao dao = new SuperDao();
        request.setAttribute("listTypePkg", dao.getAllListTypePkg());//массив с записями для отображения на странице
        if (dao.getAllListTypePkg().isEmpty()) {
            logger.warn("Получен пустой массив со списком типов пакетов (входящий\\исходящий)");
        }
        request.setAttribute("listSchemaXml", dao.getAllListSchemaXml());//массив с записями для отображения на странице
        if (dao.getAllListSchemaXml().isEmpty()) {
            logger.warn("Получен пустой массив со списком схем xml");
        }
        request.setAttribute("listDocs", dao.getAllListDocs());
        if (dao.getAllListDocs().isEmpty()) {
            logger.warn("Получен пустой массив со списком документов");
        }
        System.out.println(dao.getAllListDocs().get(0).getSchemaXml().getNameSchema());
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
