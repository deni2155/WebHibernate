package com.kindcat.archivemedo.guides;

import java.io.File;
import org.apache.log4j.Logger;

/**
 *
 * @author dreamer
 * @version 1.0.0.15 Проверка существования временного каатлога приложения для
 * работы web-фильтра UploadListGuidesFilter
 */
class ExistsTempFolder {

    private final Logger logger;//класс для логирования
    private String message;//сообщение пользователю

    ExistsTempFolder() {
        logger = Logger.getLogger(ExistsTempFolder.class);
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
}
