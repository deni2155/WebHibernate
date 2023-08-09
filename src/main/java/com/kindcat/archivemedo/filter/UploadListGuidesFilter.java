package com.kindcat.archivemedo.filter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author dreamer
 * @version 1.0.0.15
 */
//@WebFilter(filterName = "UploadListGuidesFilter", urlPatterns = {"/uploadListGuidesFilter"})
public class UploadListGuidesFilter implements Filter {

    private DiskFileItemFactory diskFactory;//фабрика для загрузки файла
    //private SuperUploadGuides superUploadGuides;//ссылка на класс для работы с файлом
    private ServletFileUpload sFileUpload;//слушатель для загрузки файлов
    private final Logger logger;//класс для логирования
    private File tempFolder;//ссылка на временную папку

    public UploadListGuidesFilter() {
        logger = Logger.getLogger(UploadListGuidesFilter.class);
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        logger.debug("Запущена инициализация фильтра");
        if (filterConfig.getInitParameter("tempFolder") != null) {
            tempFolder = new File(filterConfig.getInitParameter("tempFolder"));
            logger.debug("Из конфигурационного файла \"web.xml\" получена ссылка на временную дирректорию приложения: " + tempFolder.getAbsolutePath());
            if (getExistsFolder(tempFolder)) {
                diskFactory = new DiskFileItemFactory();
                diskFactory.setSizeThreshold(0);//если файлы не привышают этого размера в МБ, то они записываются в ОЗУ
                diskFactory.setRepository(tempFolder);//устанавливает папку для хранения файлов
                logger.debug("Создана фабрика DiskFileItemFactory для загрузки файла со списком участников МЭДО");
                sFileUpload = new ServletFileUpload(diskFactory);
                int maxFileSize = 50 * 1024 * 1024;//максимальный размер загружаемого файла 50 Мб.
                sFileUpload.setSizeMax(maxFileSize);
                logger.debug("Создан слушатель ServletFileUpload для загрузки файла со списком участников МЭДО");
            } else {
                logger.debug("Не удалось создать фабрику DiskFileItemFactory для загрузки файла со списком участников МЭДО\nзагрузка файлов невозможна");
            }
        } else {
            logger.debug("Переменная \"tempFolder\" в конфигурационном файле \"web.xml\" содержит значение null. Переменная должна содержать ссылку на временную дирректорию приложения");
        }
    }

    /**
     * Проверка существования временного каталога для загрузки файлов
     */
    boolean getExistsFolder(File tempDir) {
        if (!tempDir.exists()) {
            logger.debug("Создан временнный каталог приложения: " + tempDir.getAbsolutePath());
            return tempDir.mkdirs();
        } else {
            logger.debug("Найден временнный каталог приложения: " + tempDir.getAbsolutePath());
            return true;
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String message;

        //проверяю, что есть запрос на загрузку файла
        if (ServletFileUpload.isMultipartContent(httpRequest)) {
            logger.debug("Получен запрос на загрузку файла со списком участников МЭДО");
            try {
                List<FileItem> multiparts = sFileUpload.parseRequest(httpRequest);
                String fileName = null;
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {// проверяем, что это не обычное текстовое поле, а файл
                        if (!FilenameUtils.getName(item.getName()).isEmpty()) {//проверяю наличие файла в отправленной форме
                            fileName = FilenameUtils.getName(item.getName());//получаю имя файла 
                            File pathFile = new File(tempFolder.getAbsolutePath(), fileName);//формирую путь к файлу на сервере
                            item.write(pathFile);
                        } else {
                            logger.warn("Пользователь \"" + session.getAttribute("login") + "\" отправил форму без файла");
                            message = "Добавьте файл для загрузки";
                            httpRequest.setAttribute("message", message);
                            httpRequest.getRequestDispatcher("/pages/guides/downloadGuides.jsp").forward(httpRequest, httpResponse);
                            break;
                        }
                    }
                }
                if (fileName != null) {
                    if (fileName.endsWith("csv")) {
                        logger.info("На сервер загружен список участников МЭДО в формате csv");
//запускаем обработку 
                    } else {
                        logger.info("На сервер загружен список участников МЭДО в не соответствующем формате");
                        message = "Загружен файла не соответсвующего формата";
                        httpRequest.setAttribute("message", message);
                        httpRequest.getRequestDispatcher("/pages/guides/downloadGuides.jsp").forward(httpRequest, httpResponse);
                    }
                } else {
                    logger.warn("Не удалось определить расширение файла, т.к. имя файла равно null");
                }
            } catch (FileUploadException ex) {
                logger.error("При загрузки файла со списком участников МЭДО произошла программная ошибка", ex);
                message = "При загрузки файла произошла программная ошибка\r\nСообщите о проблеме администратору";
                httpRequest.setAttribute("message", message);
                httpRequest.getRequestDispatcher("/pages/guides/downloadGuides.jsp").forward(httpRequest, httpResponse);
//                message = "При загрузки файла произошла программная ошибка\nСообщите об ошибке администратору";
            } catch (Exception ex) {
                logger.error("При загрузки файла со списком участников МЭДО произошла программная ошибка", ex);
                message = "При загрузки файла произошла программная ошибка\r\nСообщите о проблеме администратору";
                httpRequest.setAttribute("message", message);
                httpRequest.getRequestDispatcher("/pages/guides/downloadGuides.jsp").forward(httpRequest, httpResponse);
//                message = "При загрузки файла произошла программная ошибка\nСообщите об ошибке администратору";
            }
        } else {
            logger.warn("Получен не пустой запрос на зугрузку файл, но без файл");
            message = "На сервер отправлен не корректный запрос\r\nСообщите о проблеме администратору";
            httpRequest.setAttribute("message", message);
            httpRequest.getRequestDispatcher("/pages/guides/downloadGuides.jsp").forward(httpRequest, httpResponse);
        }
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
        tempFolder = null;
        diskFactory = null;
        sFileUpload = null;
        logger.debug("Фильтр завершил работу");
    }
}
