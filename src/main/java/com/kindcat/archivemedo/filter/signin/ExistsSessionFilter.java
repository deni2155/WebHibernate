package com.kindcat.archivemedo.filter.signin;

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
 * @version 1.0.0.10
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

        //исключаю обработку фильтром сss, js и png файлы
        if (httpRequest.getRequestURI().endsWith(".css")) {
            httpResponse.setContentType("text/css");
            chain.doFilter(request, response);
        } else if (httpRequest.getRequestURI().endsWith(".js")) {
            httpResponse.setContentType("text/javascript");
            chain.doFilter(request, response);
        } else if (httpRequest.getRequestURI().endsWith(".png")) {
            httpResponse.setContentType("image/png");
            chain.doFilter(request, response);
            //если пользователь отправил данные авторизациии, то фильтруется сервлет для создания сессии
        } else if ("/createSessionServlet".equals(httpRequest.getServletPath())) {
            //если запрос формы авторизации получен через POST
            if ("POST".equals(httpRequest.getMethod())) {
                chain.doFilter(request, response);
            } else {
                logger.info("Пользователь умудрился отправить форму авторизации get-запросом");
            }
        } else {
            session = httpRequest.getSession();//получаю текущую сессию
            //если сессия существует и в ней есть атрибуты
            if (session.getAttribute("login") != null && session.getAttribute("idUser") != null && (String) session.getAttribute("fName") != null) {
                //getSession();
                httpRequest.getRequestDispatcher("linkArchiveServlet").forward(httpRequest, httpResponse);
//                chain.doFilter(request, response);//пропускаем выполнение запросов для перехода по ссылкам
                //httpRequest.getRequestDispatcher("/linkListMembersServlet").forward(httpRequest, httpResponse);
                //если нет сессии и атрибутов сессии
            } else if (session.getAttribute("login") == null || session.getAttribute("idUser") == null || (String) session.getAttribute("fName") == null) {
                httpRequest.getRequestDispatcher("/signin.jsp").forward(httpRequest, httpResponse);
                //chain.doFilter(request, response);//пропускаем выполнение запросов для перехода по ссылкам
            }
//else if (session.getAttribute("login") == null || session.getAttribute("idUser") == null || (String) session.getAttribute("fName") == null) {
//                setSession();
//            }
        }
    }

    /**
     * Найдена ранее существующая сессия и найдены параметры сессии
     */
    private void setSession() throws ServletException, IOException {
        logger.debug("Не найдена ранее существующая сессия");
        if ("POST".equals(httpRequest.getMethod())) {
            if (httpRequest.getParameter("login") != null && httpRequest.getParameter("password") != null) {
                logger.debug("Получены данные формы для авторизации");
                ImplDao userDao = new SuperDao();
                String login = httpRequest.getParameter("login").replace("\\", "").replace("'", "").replace("\"", "").replace("%", "").replace("+", "").replace("<", "").replace(">", "");
                String password = httpRequest.getParameter("password").replace("\\", "\\\\").replace("'", "\\'").replace("\"", "\\\"").replace("%", "\\%").replace("+", "\\+").replace("<", "\\<").replace(">", "\\>");
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
                        session.setAttribute("login", login);
                        session.setAttribute("idUser", idUser);
                        session.setAttribute("fName", userDao.findUserById(idUser).getFullName());
                        httpRequest.getRequestDispatcher("linkArchiveServlet").forward(httpRequest, httpResponse);
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
        } else {
//            httpRequest.setAttribute("message", "Ой, а у кого тут шаловливые ручёнки:)");
//            logger.warn("Выполнена попытка авторизации через get-запрос с клиента IP=" + httpRequest.getRemoteAddr());
            httpRequest.getRequestDispatcher("/signin.jsp").forward(httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {
        logger.debug("Фильтр завершил работу");
    }
}
