package com.kindcat.archivemedo.config;

import java.io.File;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dreamer Класс для получения ссылки из конфигурационного файла до
 * временной папки приложения
 */
class TempFolder {

    private File tempFolder;

    /**
     * @return ссылка на временную папку приложения
     */
    File getTempFolder() {
        return tempFolder;
    }

    TempFolder(Properties properties) {
        //подключаю логирование
        Logger logger = LoggerFactory.getLogger(TempFolder.class);
        //если переменная указана в конфигурационном файле или не равна null
        if (properties.getProperty("tempFolder") != null) {
            tempFolder = new File(properties.getProperty("tempFolder"));
        } else if (properties.getProperty("tempFolder") == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("В конфигурационном файле \"config.properties\" не найдена переменная \"tempFolder\"\\n\t");
            stringBuilder.append("tempFolder=временный каталог приложения");
            String messageForLog = stringBuilder.toString();
            logger.warn(messageForLog);
        }
    }
}
