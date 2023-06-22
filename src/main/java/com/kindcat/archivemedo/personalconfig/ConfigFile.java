package com.kindcat.archivemedo.personalconfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Класс для работы с личным конфигурационным файлом
 */
public final class ConfigFile implements ConfigFileImpl {

    private File tempFolder;

    @Override
    public File getTempFolder() {
        return tempFolder;
    }

    public ConfigFile() throws IOException {
        //подключаю логирование
        Logger logger = LoggerFactory.getLogger(ConfigFile.class);
        //указываю путь к файлу свойств по умолчанию в tomcat
        File pathConfigFile=new File(String.format("%s/%s",ConfigFile.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"config.properties"));
        //если файл в tomcat не найден, то ищем файл в проекте на этапе отладки
        if(!pathConfigFile.exists()){
            pathConfigFile=new File("/home/dreamer/NetBeansProjects/WebMedo/target/WebMedo-0.0.2/WEB-INF/classes/config.properties");
        }

        //если конфигурационный файл существует
        if (pathConfigFile.exists()) {
            try (FileInputStream fileStream = new FileInputStream(pathConfigFile)) {
                Properties properties = new Properties();
                properties.load(fileStream);
                if (properties.getProperty("tempFolder") != null) {
                    tempFolder = new File(properties.getProperty("tempFolder"));
                } else if (properties.getProperty("tempFolder") == null) {
                    logger.warn(String.format("В конфигурационном файле %s не найдена переменная \"tempFolder\". Она должна быть равна ссылке на временный каталог операционной системы", pathConfigFile));
                }
            } catch (Exception ex) {
                logger.error(String.format("При чтении конфигурационного файла %s возникла программная ошибка", pathConfigFile), ex);
            }
        } else {
            logger.info(String.format("Не найден конфигурационный файл %s\nВ файле нужно указать переменную tempFolder, значение которой должно быть равным ссылке на временный каталог операционной системы", pathConfigFile));
        }
    }
}
