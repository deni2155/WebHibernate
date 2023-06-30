package com.kindcat.archivemedo.context;

import com.kindcat.archivemedo.db.utils.SessionFactoryUtil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import org.slf4j.bridge.SLF4JBridgeHandler;
/**
*Класс для создания и закрытия необходимых объектов для работы приложения при старте приложения
*Класс подключен в конфиге WEB-INF/web.xml
*/
public class ReinitializingContext implements ServletContextListener {

    /**
     * Инициализирую контекст приложения и в нём подключаю логирование
     *
     * @param arg
     */
    @Override
    public void contextInitialized(ServletContextEvent arg) {
        SessionFactoryUtil.setSessionFactory();
Logger logger = Logger.getLogger(ReinitializingContext.class);
logger.debug("Контекст загружен");
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        session.close();

        //System.out.println("contextInitialized");
        //remove the jsf root logger, avoid duplicated logging
        //try comment out this and see the different on the console
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
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
    }
}
