package com.kindcat.archivemedo.signin;

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

/**
 *
 * @author dreamer
 * @version 1.0.0.7
 */
@WebFilter(filterName = "existsSessionFilter", urlPatterns = {"/"})
public class ExistsSessionFilter implements Filter {

    private final Logger logger;

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
     * @param request - запрос servlet для обработки
     * @param response - ответ servlet, который создаётся
     * @param chain - цепочка фильтов для обработки
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("Выполняется фильтр");

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);//получаю существующую сессию

        String login = null;
        String password = null;
        int idUser = 0;
        //получаю значение переменных из сессии
        if (session != null) {
            logger.debug("Найдена ранее созданная сессия, фильтр получает параметры из сессии");
            login = (String) session.getAttribute("login");

        } else {
            logger.debug("Не найдена ранее существующая сессия");
        }
        //если хотя бы один атрибут сессии пустой
        if (login == null) {
            logger.debug("Не найдены параметры сессии");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
        } else {
            logger.debug("Получены параметры сессии");
            logger.debug(login);
            String inputRequest = httpRequest.getHeader("referer");//получаю url, с которого пришёл запрос
            if (inputRequest != null) {
                request.getRequestDispatcher(inputRequest).forward(request, response);
            } else {
                request.getRequestDispatcher("/pages/archive.jsp").forward(request, response);
            }
        }
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
        logger.debug("Фильтр завершил работу");
    }
}
