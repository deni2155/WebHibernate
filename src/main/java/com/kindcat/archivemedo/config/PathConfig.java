package com.kindcat.archivemedo.config;

import java.io.File;

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
    }

    /**
     * @return существование файла и что переменная не имеет значение null
     */
    boolean isExistsConfigFile() {
        return pathConfigFile.exists() && pathConfigFile != null;
    }

    /**
     * @return путь к конфигурационному файлу
     */
    File getPathConfigFile() {
        return pathConfigFile;
    }
}
