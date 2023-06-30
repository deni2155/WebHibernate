package com.kindcat.archivemedo.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Класс для работы с личным конфигурационным файлом
 */
public class ConfigFile extends PathConfig implements ConfigFileImpl {

    private Properties properties = new Properties();
    private File tempFolder;
//
//    @Override
//    public File getTempFolder() {
//        return tempFolder;
//    }

    public ConfigFile() throws IOException {
        //подключаю логирование
        Logger logger = LoggerFactory.getLogger(ConfigFile.class);
        //если путь к конфигурационному файл не равен null и конфигурационный файл найден
        if (super.isExistsConfigFile()) {
            try (FileInputStream fileStream = new FileInputStream(super.getPathConfigFile())) {
                properties.load(fileStream);
            } catch (Exception ex) {
                logger.error("При чтении конфигурационного файла " + super.getPathConfigFile() + " возникла программная ошибка: " + ex);
            }
            //если путь к конфигурационному файл равен null или конфигурационный файл найден
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Не найден конфигурационный файл \"config.properties\"\n");
            stringBuilder.append("В конфигурационном файле нужно указать следующие переменные:\n\t");
            stringBuilder.append("tempFolder=путь к временной папке приложения\n");
            String messageForLog = stringBuilder.toString();
            logger.info(String.format(messageForLog));
        }
    }

    /**
     * @return ссылка на временный каталог приложения
     */
    @Override
    public File getTempFolder() {
        TempFolder classTempFolder = new TempFolder(properties);
        return classTempFolder.getTempFolder();
//перед вызовом функции проверить значение на null и записать сообщение об отсутствии знаечния переменной в log
    }
}
