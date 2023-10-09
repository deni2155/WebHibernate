package com.kindcat.archivemedo.servlet.signin;

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
import org.mindrot.jbcrypt.BCrypt;
import com.kindcat.archivemedo.beans.ImplBeans;

/**
 *
 * @author dreamer
 * @version 1.0.0.33 Класс для авторизации пользователя
 */
@WebServlet(name = "CreateSessionServlet", urlPatterns = {"/createSessionServlet"})
public class CreateSessionServlet extends HttpServlet {

    private final Logger logger;
    private final StringBuilder logBuilder;
    private String stringlog;
    private StackTraceElement[] stackTrace;

    public CreateSessionServlet() {
        logger = Logger.getLogger(CreateSessionServlet.class);
        logBuilder = new StringBuilder();
    }

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Не найдена ранее существующая сессия");

        // Получаем объект PrintWriter
        try (PrintWriter out = response.getWriter()) {
            //если получены данные авторизации
            if (!request.getParameter("login").isEmpty() && request.getParameter("password") != null) {
                logger.debug("Получены данные формы для авторизации");
                ImplBeans userBeans = new SuperBeans();
                userBeans.setBeansLoginUser(request.getParameter("login").replace("\\", "").replace("'", "").replace("\"", "").replace("%", "").replace("+", "").replace("<", "").replace(">", ""));
                userBeans.setBeansHash(request.getParameter("password").replace("\\", "\\\\").replace("'", "\\'").replace("\"", "\\\"").replace("%", "\\%").replace("+", "\\+").replace("<", "\\<").replace(">", "\\>"));
                ImplDao userDao = new SuperDao();
                //получаю по логину идентификатор пользователя
                userBeans.setBeansIdUser(userDao.findUserInLoginByLogin(userBeans.getBeansLoginUser()));
                //если по логину пользователя в БД найден идентификатор
                if (userBeans.getBeansIdUser() > 0) {
                    logger.debug("При авторизации в БД найден пользователь \"" + userBeans.getBeansLoginUser() + "\"");
                    //верификация пароля пользователя
                    if (BCrypt.checkpw(userBeans.getBeansHash(), userDao.findUserById(userBeans.getBeansIdUser()).getHash())) {
                        logger.info("Проверка пароля пользователя \"" + userBeans.getBeansLoginUser() + "\" прошла успешно");
                        HttpSession session = request.getSession();
                        session.setAttribute("login", userBeans.getBeansLoginUser());
                        session.setAttribute("idUser", userBeans.getBeansIdUser());
                        session.setAttribute("fName", userDao.findUserById(userBeans.getBeansIdUser()).getFullName());
                        out.println("true");
                        out.println("Авторизация прошла успешно. Ожидайте перезагрузку страницы");
                    } else {
                        out.println("false");
                        out.println("Неверный пароль");
                        logger.debug("Пользователь \"" + userBeans.getBeansLoginUser() + "\" ввёл не верный пароль");
                    }
                } else {
                    logger.info("Не удачная попытка авторизации, логин \"" + userBeans.getBeansLoginUser() + "\" не найден в БД");
                    out.println("false");
                    out.println("Логин не найден");
                }
            } else {
                logger.info("Пользователь отправил пустую форму авторизации");
                out.println("false");
                out.println("Укажите логин и пароль");
            }
        } catch (Exception ex) {
            logBuilder.setLength(0);
            logBuilder.append("При авторизации пользователя возникла программная ошибка");
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
