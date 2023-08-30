package com.kindcat.archivemedo.members.regex;

import org.apache.log4j.Logger;

/**
 *
 * @author dreamer
 * @version 1.0.0.23
 */
class VerificationMembers {

    private final Logger logger;

    private final String userLogin;
    private final String nameOrg;
    private final String emailOrg;
    private final String guidOrg;

    VerificationMembers(String userLogin, String nameOrg, String emailOrg, String guidOrg) {
        logger = Logger.getLogger(VerificationMembers.class);
        this.userLogin = userLogin;
        this.nameOrg = nameOrg;
        this.emailOrg = emailOrg;
        this.guidOrg = guidOrg;
    }

    /**
     * Сыоответствует ли полученная от пользователя информация регулярным
     * вырабжения строки
     */
    boolean verifRegex() {

        boolean verif = true;
        //если хотя бы одна строка не соответствует регулярному выражения, то проверка не выполнится
        if (!nameOrg.matches("^[а-яА-Я\\s()«»,-]+$")) {
            logger.info("Пользователь \"" + userLogin + "\" указал не валидное наименование организации при добавлении участника МЭДО");
            verif = false;
        }
        if (!emailOrg.matches("^[A-Z~_\\s-]+$")) {
            logger.info("Пользователь \"" + userLogin + "\" указал не валидный email организации организации при добавлении участника МЭДО");
            verif = false;
        }
        if (!guidOrg.matches("^[a-z0-9-]+$")) {
            logger.info("Пользователь \"" + userLogin + "\" указал не валидный GUID организации организации при добавлении участника МЭДО");
            verif = false;
        }
        return verif;
    }

    String returnExistsId() {
        return "";
    }
}
