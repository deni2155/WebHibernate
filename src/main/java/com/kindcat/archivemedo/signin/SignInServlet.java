package com.kindcat.archivemedo.signin;

import com.kindcat.archivemedo.db.dao.ImplDao;
import com.kindcat.archivemedo.db.dao.SuperDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

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
        HttpSession session = request.getSession(false);
        logger.debug("Идентификатор пользователя в сессии " + session.getAttribute("idUser"));
        logger.debug("Логин пользователя в сессии " + session.getAttribute("login"));
        logger.debug("Полное имя пользователя в сессии " + session.getAttribute("fName"));
        request.getRequestDispatcher("/pages/archive.jsp").forward(request, response);
//параметры формы не пустые
//        if (request.getParameter("login")!=null && request.getParameter("password")!=null) {
//            logger.debug("Получены данные формы для авторизации");
//            ImplDao userDao = new SuperDao();
//            String login = request.getParameter("login");
//            String password = request.getParameter("password");
//            int idUser = 0;
//            idUser = userDao.findUserInLoginByLogin(login);//ищу пользователя по логину в БД
//            if (idUser > 0) {
//                logger.debug("При авторизации в БД найден пользователь \"" + login + "\"");
//                if (BCrypt.checkpw(password, userDao.findUserById(idUser).getHash())) {//верификация пароля пользователя
//                    logger.debug("Идентификатор пользователя при верификации " + idUser);
//                    logger.info("Проверка пароля пользователя \"" + login + "\" прошла успешно");
//                    HttpSession session = request.getSession();
//                    session.setAttribute("login", request.getAttribute("login"));
//                    session.setAttribute("idUser", idUser);
//                    session.setAttribute("fName", userDao.findUserById(idUser).getFullName());
//                    request.getRequestDispatcher("/pages/archive.jsp").forward(request, response);
//                }
//            }
//        }
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
