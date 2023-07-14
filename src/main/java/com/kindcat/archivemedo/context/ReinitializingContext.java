package com.kindcat.archivemedo.context;

import com.kindcat.archivemedo.context.var.VarsApp;
import com.kindcat.archivemedo.db.utils.SessionFactoryUtil;
import java.io.File;
import javax.servlet.ServletContext;
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
     * @version 1.0.0.15
     * @param arg
     */
    @Override
    public void contextInitialized(ServletContextEvent arg) {
        logger.debug("Запущена инициализация контекста приложения");
        //Создаю объект sessionFactory для работы с БД
        SessionFactoryUtil.setSessionFactory();

//        //получаю значение переменных из файла web.xml
//        ServletContext context = arg.getServletContext();
//        //получаю ссылку на врекменный каталог приложения
//        if (context.getInitParameter("tempDir") != null) {
//            String tempDir = (String) context.getInitParameter("tempDir");
//            logger.debug("Получена ссылка на временный каталог приложения из переменной \"tempDir\" конфигурационного файла \"web.xml\": " + tempDir);
//            VarsApp.setTempDir(new File(tempDir));
//        } else {
//            logger.debug("В конфигурационном файле \"web.xml\" не указана ссылка на временный каталог приложения (получено значение null)\nЗагрузка файлов на сервер через web-интерфейс невозможна");
//        }
        
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
