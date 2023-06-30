package com.kindcat.archivemedo.context;

import com.kindcat.archivemedo.db.utils.SessionFactoryUtil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

/**
 * Класс для создания и уничтожения необходимых объектов для работы приложения
 * при старте приложения Класс подключен в конфиге WEB-INF/web.xml
 */
public class ReinitializingContext implements ServletContextListener {

    private final Logger logger;

    public ReinitializingContext() {
        logger = Logger.getLogger(ReinitializingContext.class);
    }

    /**
     * Инициализирую контекст приложения и в нём подключаю логирование
     *
     * @param arg
     */
    @Override
    public void contextInitialized(ServletContextEvent arg) {
        //Создаю объект sessionFactory для работы с БД
        SessionFactoryUtil.setSessionFactory();
        logger.debug("Выполнена инициализация контекста приложения");

        /*
        * Настройки нужны для работы логирования с JSF, для JSP они не нужны
         */
        //remove the jsf root logger, avoid duplicated logging
        //try comment out this and see the different on the console
//        SLF4JBridgeHandler.removeHandlersForRootLogger();
//        SLF4JBridgeHandler.install();
    }

    /**
     * Уничтожение объектов контекста
     *
     * @param arg
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg) {
        //закрываю объект sessionFactory
        if (SessionFactoryUtil.getSessionFactory() != null) {
            SessionFactoryUtil.getSessionFactory().close();
        }
        logger.debug("Выполнена очистка контекста приложения");
    }
}
