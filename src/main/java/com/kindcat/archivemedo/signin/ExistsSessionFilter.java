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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author dreamer
 * @version 1.0.0.9
 */
@WebFilter(filterName = "existsSessionFilter", urlPatterns = {"/*"})
public class ExistsSessionFilter implements Filter {

    private final Logger logger;

    private HttpServletRequest httpRequest;
    private HttpServletResponse httpResponse;
    private HttpSession session;

    public ExistsSessionFilter() {
        logger = Logger.getLogger(ExistsSessionFilter.class);
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
        httpRequest = (HttpServletRequest) request;
        httpResponse = (HttpServletResponse) response;
        session = httpRequest.getSession();//получаю текущую сессию

        if (session.getAttribute("login") != null && session.getAttribute("idUser") != null && (String) session.getAttribute("fName") != null) {
            getSession();
//            chain.doFilter(httpRequest, httpResponse);
        } else if (session.getAttribute("login") == null || session.getAttribute("idUser") == null || (String) session.getAttribute("fName") == null) {
            setSession();
//            chain.doFilter(httpRequest, httpResponse);
        }
        //проверка на null
//        String login = httpRequest.getParameter("login");
//        String password = httpRequest.getParameter("password");

//        HttpSession session = httpRequest.getSession();
//        ImplDao userDao = new SuperDao();
////авторизован ранее
//        if (session != null && session.getAttribute("login") != null && session.getAttribute("password") != null) {
////авторизовываетс
//        } else if (userDao.findUserInLoginByLogin(login) > 0) {
////пользователь не найден и редирект на главную
//        } else {
//        }
    }

    /**
     * Найдена ранее существующая сессия и найдены параметры сессии
     */
    private void getSession() throws ServletException, IOException {
        logger.debug("Фильтр нашёл параметры сессии пользоваетля \"" + session.getAttribute("login") + "\"");
        String inputRequest = httpRequest.getHeader("referer");//получаю url, с которого пришёл запрос
        if (inputRequest != null) {
            httpRequest.getRequestDispatcher(inputRequest).forward(httpRequest, httpResponse);
        } else {
            httpRequest.getRequestDispatcher("/pages/archive.jsp").forward(httpRequest, httpResponse);
        }
    }

    private void setSession() throws ServletException, IOException {
        logger.debug("Не найдена ранее существующая сессия");
        if (httpRequest.getParameter("login") != null && httpRequest.getParameter("password") != null) {
            logger.debug("Получены данные формы для авторизации");
            ImplDao userDao = new SuperDao();
            String login = httpRequest.getParameter("login");
            String password = httpRequest.getParameter("password");
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
//                    logger.info("Идентификатор пользователя при верификации " + idUser);
                    logger.info("Проверка пароля пользователя \"" + login + "\" прошла успешно");
                    session = httpRequest.getSession();
                    session.setAttribute("login", httpRequest.getParameter("login"));
                    session.setAttribute("idUser", idUser);
                    session.setAttribute("fName", userDao.findUserById(idUser).getFullName());
                    httpRequest.getRequestDispatcher("/pages/archive.jsp").forward(httpRequest, httpResponse);
                } else {
                    logger.debug("Пользователь \"" + login + "\" ввёл не верный пароль");
                    httpRequest.setAttribute("message", "Не верный пароль");
                    httpRequest.getRequestDispatcher("/signin.jsp").forward(httpRequest, httpResponse);
                }
            }
        } else if (httpRequest.getParameter("login") == null || httpRequest.getParameter("password") == null) {
            //logger.debug("Попытка авторизации без ввода логина и пароля");
            //httpRequest.setAttribute("message", "Не указаны логин и пароль");
            httpRequest.getRequestDispatcher("/signin.jsp").forward(httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {
        logger.debug("Фильтр завершил работу");
    }
}
