package com.kindcat.archivemedo.config;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dreamer Формирую путь к конфигурационному файлу
 */
class PathConfig {

    //путь к конфигурационному файлу
    private File pathConfigFile;

    /**
     * Конструктор класса PathConfig
     *
     * @set путь к конфигурационному файлу приложения
     */
    PathConfig() {
        //указываю путь к файлу свойств по умолчанию в tomcat
        pathConfigFile = new File(String.format("%s/%s", ConfigFile.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "config.properties"));
        //если файл в tomcat не найден, то ищем файл в проекте на этапе отладки
        if (!pathConfigFile.exists()) {
            pathConfigFile = new File("/home/dreamer/NetBeansProjects/WebMedo/target/WebMedo-0.0.2/WEB-INF/classes/config.properties");
        }
        //подключаю логирование
        Logger logger = LoggerFactory.getLogger(ConfigFile.class);
        //логирую ифнормацию о получении пути к кастомному конфигурационному файлу на уровне debug
        StringBuilder stringBuilder=new StringBuilder();
        if(pathConfigFile!=null){
            stringBuilder.append("Переменной pathConfigFile успешно присвоен путь к кастомному конфигурационному файлу - ");
            stringBuilder.append(pathConfigFile.getAbsoluteFile());
            String messageForLogging=stringBuilder.toString();
            logger.debug(messageForLogging);
        }
    }

    /**
     * @return isExistsConfigFile - существование файла и что переменная не имеет значение null
     */
    boolean isExistsConfigFile() {
        return pathConfigFile.exists() && pathConfigFile != null;
    }

    /**
     * @return pathConfigFile - путь к конфигурационному файлу
     */
    File getPathConfigFile() {
        return pathConfigFile;
    }
}
