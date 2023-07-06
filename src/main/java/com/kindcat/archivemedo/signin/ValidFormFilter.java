package com.kindcat.archivemedo.signin;

import com.kindcat.archivemedo.db.dao.ImplDao;
import com.kindcat.archivemedo.db.dao.SuperDao;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author dreamer
 * @version 1.0.0.5
 */
@WebFilter(filterName = "validFormFilter", urlPatterns = {"/signInServlet"})
public class ValidFormFilter implements Filter {

    private final Logger logger;

    public ValidFormFilter() {
        logger = Logger.getLogger(ValidFormFilter.class);
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        logger.debug("Запущена инициализация фильтра");
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("Выполняется фильтр");
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        //получен параметры формы авторизации
//        if (httpRequest.getParameter("login") != null && httpRequest.getParameter("login") != null) {
        //параметры формы не пустые
        if (httpRequest.getParameter("login").isEmpty() == false && httpRequest.getParameter("password").isEmpty() == false) {
            logger.debug("Получены данные формы для авторизации");
            ImplDao userDao = new SuperDao();
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            int idUser = 0;
            idUser = userDao.findUserInLoginByLogin(login);//ищу пользователя по логину в БД
            //пользователь не нйден в БД
            if (idUser == 0) {
                logger.info("Не удачная попытка авторизации, логин \"" + login + "\" не найден в БД");
                httpRequest.setAttribute("message", "Пользователь с логином \"" + login + "\" не найден");
                httpRequest.getRequestDispatcher("/signin.jsp").forward(httpRequest, httpResponse);
                //пользователь найден
            } else if (idUser > 0) {
                logger.debug("При авторизации в БД найден пользователь \"" + login + "\"");
                if (BCrypt.checkpw(password, userDao.findUserById(idUser).getHash())) {//верификация пароля пользователя
                    logger.info("Проверка пароля пользователя \"" + login + "\" прошла успешно");
                    httpRequest.setAttribute("idUser", idUser);
                    httpRequest.setAttribute("fName", userDao.findUserById(idUser).getFullName());
                } else {
                    logger.debug("Пользователь \"" + login + "\" ввёл не верный пароль");
                    httpRequest.setAttribute("message", "Не верный пароль");
                    httpRequest.getRequestDispatcher("/signin.jsp").forward(httpRequest, httpResponse);
                }
            }
            //параметры формы авторизации пустые
        } else if (httpRequest.getParameter("login").isEmpty() == true || httpRequest.getParameter("password").isEmpty() == true) {
            logger.debug("Попытка авторизации без ввода логина и пароля");
            httpRequest.setAttribute("message", "Не заполнены логин и пароль");
            httpRequest.getRequestDispatcher("/signin.jsp").forward(httpRequest, httpResponse);

        }
        //не получен запрос и ответ
//        } else if (httpRequest.getParameter("login") == null && httpRequest.getParameter("login") == null) {
//            httpRequest.getRequestDispatcher("/signin.jsp").forward(httpRequest, httpResponse);
//        }

    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
        logger.debug("Фильтр завершил работу");
    }
}
