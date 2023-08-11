//package com.kindcat.archivemedo.filter;
//
//import java.io.IOException;
//import java.io.PrintStream;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.apache.log4j.Logger;
//
///**
// *
// * @author dreamer
// * @version 1.0.0.18
// */
////@WebFilter(filterName = "AddMemberForListGuisedFilter", urlPatterns = {"/*"})
//public class AddMemberForListGuisedFilter implements Filter {
//
//    private final Logger logger;//класс для логирования
//
//    public AddMemberForListGuisedFilter() {
//        logger = Logger.getLogger(AddMemberForListGuisedFilter.class);
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
//     * @param request The servlet request we are processing
//     * @param response The servlet response we are creating
//     * @param chain The filter chain we are processing
//     *
//     * @exception IOException if an input/output error occurs
//     * @exception ServletException if a servlet error occurs
//     */
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
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
