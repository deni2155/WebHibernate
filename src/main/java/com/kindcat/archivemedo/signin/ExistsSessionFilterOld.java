package com.kindcat.archivemedo.signin;
//
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import org.apache.log4j.Logger;
//
///**
// *
// * @author dreamer
// * @version 1.0.0.7
// */
//@WebFilter(filterName = "existsSessionFilterOld", urlPatterns = {"/"})
//public class ExistsSessionFilterOld implements Filter {
//
//    private final Logger logger;
//
//    public ExistsSessionFilterOld() {
//        logger = Logger.getLogger(ExistsSessionFilterOld.class);
//    }
//
//    /**
//     * Init method for this filter
//     *
//     * @param filterConfig
//     */
//    @Override
//    public void init(FilterConfig filterConfig) {
//        logger.debug("Запущена инициализация фильтра");
//    }
//
//    /**
//     *
//     * @param request - запрос servlet для обработки
//     * @param response - ответ servlet, который создаётся
//     * @param chain - цепочка фильтов для обработки
//     *
//     * @exception IOException if an input/output error occurs
//     * @exception ServletException if a servlet error occurs
//     */
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        logger.debug("Выполняется фильтр");
//
//        final HttpServletRequest httpRequest = (HttpServletRequest) request;
//        final HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        HttpSession session = httpRequest.getSession(false);//получаю существующую сессию
//
//        String login=null;
//        int idUser = 0;
//        String fName=null;
//        //получаю значение переменных из сессии
//        if (session != null) {
//            logger.debug("Найдена ранее созданная сессия, фильтр получает параметры из сессии");
//            login = (String) session.getAttribute("login");
//            idUser = (int) session.getAttribute("idUser");
//            fName = (String) session.getAttribute("fName");
//        } else {
//            logger.debug("Не найдена ранее существующая сессия");
//        }
//        //если хотя бы один атрибут сессии пустой
//        if (login == null || fName==null || idUser==0) {
//            request.getRequestDispatcher("/signin.jsp").forward(request, response);
//        } else if(login.isEmpty()==false && fName.isEmpty()==false && idUser>0){
//            logger.debug("Получены параметры сессии пользователя \""+login+"\"");
//            String inputRequest = httpRequest.getHeader("referer");//получаю url, с которого пришёл запрос
//            if (inputRequest != null) {
//                request.getRequestDispatcher(inputRequest).forward(request, response);
//            } else {
//                request.getRequestDispatcher("/pages/archive.jsp").forward(request, response);
//            }
//        }
//    }
//
//    /**
//     * Destroy method for this filter
//     */
//    @Override
//    public void destroy() {
//        logger.debug("Фильтр завершил работу");
//    }
//}
