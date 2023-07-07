package com.kindcat.archivemedo.signin;

import java.io.IOException;
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
 * @version 1.0.0.6
 */
@WebServlet(name = "SignInServlet", urlPatterns = {"/signInServlet"})
public class SignInServlet extends HttpServlet {

    private final Logger logger;

    public SignInServlet() {
        logger = Logger.getLogger(SignInServlet.class);
    }

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
//        logger.info("Идентификатор пользователя в запросе " + request.getParameter("idUser"));
//        session.setAttribute("idUser", request.getParameter("idUser"));
//        logger.info("Идентификатор пользователя в сессии " + session.getAttribute("idUser"));
//        logger.info("Полное имя пользователя в запросе " + request.getParameter("fName"));
//        session.setAttribute("fName", request.getParameter("fName"));
//        logger.info("Полное имя пользователя в сессии " + session.getAttribute("fName"));
        logger.info("Логин пользователя в запросе " + request.getParameter("login"));
        session.setAttribute("login", request.getParameter("login"));
        logger.info("Логин пользователя в сессии " + session.getAttribute("login"));
        logger.info("Пользователь \"" + request.getParameter("login") + "\" успешно авторизован");
        request.getRequestDispatcher("/pages/archive.jsp").forward(request, response);
    }

    /**
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
     *
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
