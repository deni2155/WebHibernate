//package com.kindcat.archivemedo.context.var;
//
//import java.io.File;
//import org.apache.log4j.Logger;
//
///**
// *
// * @author dreamer
// * @version 1.0.0.15 Переменные контекста из файла web.xml
// */
//public class VarsApp {
//
//    private static File tempDir;//ссылка на временный каталог приложения
//    private static Logger logger;//класс для логирования
//
//    static {
//        logger = Logger.getLogger(VarsApp.class);
//    }
//
//    public static File getTempDir() {
//        return tempDir;
//    }
//
//    public static void setTempDir(File linkTempDir) {
//        boolean createDir = false;
//        if (linkTempDir.exists()) {
//            createDir = true;
//            logger.debug("Найден временный каталог приложения: " + linkTempDir.getAbsolutePath());
//        } else {
//            createDir = linkTempDir.mkdirs();
//            logger.debug("Создан временный каталог приложения: " + linkTempDir.getAbsolutePath());
//        }
//        if (createDir) {
//            tempDir = linkTempDir;
//            logger.debug("Переменная со ссылкой на временный каталог успешно инициализирована: " + linkTempDir.getAbsolutePath());
//        }
//    }
//}
