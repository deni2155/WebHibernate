package com.kindcat.archivemedo.filter.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.apache.log4j.Logger;

/**
 *
 * @author dreamer
 * @version 0.0.0.5
 */
@WebFilter(filterName = "encodingRequestFilter", urlPatterns = {"/*"})
public class EncodingRequestFilter implements Filter {

    private final Logger logger;
    private final StringBuilder logBuilder;
    private String stringlog;
    private StackTraceElement[] stackTrace;

    /**
     * В конструкторе инициализирую класс для логирования
     */
    public EncodingRequestFilter() {
        logger = Logger.getLogger(EncodingRequestFilter.class);
        logBuilder = new StringBuilder();
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
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            chain.doFilter(request, response);
        } catch (IOException ex) {
            logBuilder.setLength(0);
            logBuilder.append("Программная ошибка при работе фильтра для перекодировки запросов");
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

    @Override
    public void destroy() {
        logger.debug("Фильтр завершил работу");
    }
}
