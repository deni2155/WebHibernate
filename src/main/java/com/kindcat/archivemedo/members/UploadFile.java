package com.kindcat.archivemedo.members;

//package com.kindcat.archivemedo.guides;
//
////import com.kindcat.archivemedo.config.ConfigFile;
//import com.kindcat.archivemedo.config.ConfigFileImpl;
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FilenameUtils;
//import org.apache.log4j.Logger;
//
///**
// * Распределяет файлы по расширению
// */
//class UploadFile {
//
//    private final Logger logger;//класс для логирования
////    private final ConfigFileImpl configFile;//класс для работы с конфигурационным файлом
//    private final DiskFileItemFactory diskFactory;//фабрика для загрузки файла
//    private String message;//сообщение для пользователя, которое отправляется в запрос
//
//    UploadFile() throws IOException {
//        logger = Logger.getLogger(UploadFile.class);
////        configFile = new ConfigFile();
//        diskFactory = new DiskFileItemFactory();
//    }
//
//    /**
//     * загружаю файл с данными
//     */
//    void distributingExtensionFile(HttpServletRequest request) throws Exception {
//        //проверяю существование временной папки (сообщения по false пишутся в отдельной процедуре)
//        if (existsTempFolder()) {
//            //создаю слушателя из фабрики
//            ServletFileUpload sFileUpload = new ServletFileUpload(diskFactory);
//            int maxFileSize = 50 * 1024 * 1024;//максимальный размер загружаемого файла 50 Мб.
//            sFileUpload.setSizeMax(maxFileSize);
//            logger.debug("Создан слушатель для загрузки файла со списком участников МЭДО");
//            //проверяю, что есть запрос на загрузку файла
//            if (ServletFileUpload.isMultipartContent(request)) {
//
//                logger.debug("Получен запрос на загрузку файла со списком участников МЭДО");
//                try {
//                    List<FileItem> multiparts = sFileUpload.parseRequest(request);
//                    String fileName = null;
//                    for (FileItem item : multiparts) {
//                        if (!item.isFormField()) {// проверяем, что это не обычное текстовое поле, а файл
//                            fileName = FilenameUtils.getName(item.getName());//получаю имя файла 
//                            File pathFile = new File(configFile.getTempFolder().getAbsolutePath() + "/" + fileName);//формирую путь к файлу на сервере
//                            item.write(pathFile);
//                        }
//                    }
//                    logger.debug("Файл со списком участников МЭДО успешно загружен на сервер");
//                    if (fileName != null) {
////                        if (fileName.endsWith("xlsx")) {
////                            logger.info("На сервер загружен список участников МЭДО в формате Excel");
////                        } else if (fileName.endsWith("csv")) {
//                        if (fileName.endsWith("csv")) {
//                            logger.info("На сервер загружен список участников МЭДО в формате csv");
//                        } else {
//                            logger.info("На сервер загружен список участников МЭДО в не соответствующем формате");
//                        }
//                    } else {
//                        logger.warn("Не удалось определить расширение файла, т.к. имя файла равно null");
//                    }
//
//                } catch (FileUploadException ex) {
//                    logger.error("При загрузки файла со списком участников МЭДО произошла программная ошибка", ex);
//                    message = "При загрузки файла произошла программная ошибка\nСообщите об ошибке администратору";
//                } catch (Exception ex) {
//                    logger.error("При загрузки файла со списком участников МЭДО произошла программная ошибка", ex);
//                    message = "При загрузки файла произошла программная ошибка\nСообщите об ошибке администратору";
//                }
//            }
//        }
//    }
//
//    /**
//     * временная папка не равна null и существует
//     */
//    private boolean existsTempFolder() {
//        if (configFile.getTempFolder() != null) {
//            boolean isCreateTempDirs = false;
//            if (!configFile.getTempFolder().exists()) {
//                logger.debug(configFile.getTempFolder().getAbsolutePath());
//                File createTempDirs = new File(configFile.getTempFolder().getAbsolutePath());
//                isCreateTempDirs = createTempDirs.mkdirs();
//                logger.debug("Создан временнный каталог приложения: " + configFile.getTempFolder().getAbsolutePath());
//            }
//            if (isCreateTempDirs) {
//                int maxMemSize = 0;//если файлы не привышают этого размера в МБ, то они записываются в ОЗУ
//                diskFactory.setSizeThreshold(maxMemSize);
//                diskFactory.setRepository(configFile.getTempFolder());//устанавливает папку для хранения файлов
//                logger.debug("Сооздана фабрика для загрузки файла со списком участников МЭДО");
//                return true;
//            } else {
//                logger.warn("Путь к временной дирректории приложения получен, но дирректория не существует");
//                message = "Не найдена временная дирректория приложения";
//                return false;
//            }
//        } else {
//            logger.warn("Путь к временной дирректории приложения равен null");
//            message = "Путь к временной дирректории приложения равен null";
//            return false;
//        }
//    }
//
//    String getMessage() {
//        return message;
//    }
//}
