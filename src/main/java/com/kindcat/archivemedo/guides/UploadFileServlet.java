package com.kindcat.archivemedo.guides;

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
 * @version 1.0.0.11 сервлет для азгрузки файла со списком участников МЭДО
 */
@WebServlet(name = "DownloadFileServlet", urlPatterns = {"/uploadFileServlet"})
public class UploadFileServlet extends HttpServlet {

    private Logger logger;//класс для логирования

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        UploadFile uploadFile = new UploadFile();
        uploadFile.distributingExtensionFile(request);
        request.setAttribute("message", uploadFile.getMessage());
        request.getRequestDispatcher("/pages/guides/resultDownloadGuides.jsp").forward(request, response);
    }

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            logger.error("При отправки формы с файлом со списком участников МЭДО возникла программная ошибка", ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            logger.error("При отправки формы с файлом со списком участников МЭДО возникла программная ошибка", ex);
        }
    }
}
