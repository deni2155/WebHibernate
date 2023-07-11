package com.kindcat.archivemedo.logger;

import org.apache.log4j.Logger;

/**
 * Класс для логирования на JSP- страницах
 */
public class LoggerForJsp implements LoggerForJspImpl {

    Logger logger = Logger.getLogger(LoggerForJsp.class);

    /**
     * записываю авторизацию существующей сессии на index.jspS
     *
     * @param login - логин пользователя
     */
    @Override
    public void setLoggerSignInJsp(String login) {
        logger.debug(login);
    }
}
