package com.kindcat.archivemedo.members;

import com.kindcat.archivemedo.db.dao.ImplDao;
import com.kindcat.archivemedo.db.dao.SuperDao;
import org.apache.log4j.Logger;

/**
 *
 * @author dreamer
 * @version 1.0.0.23
 */
class VerifMemberForAddProcess {

    private final Logger logger;
    private ImplDao superDao;

    private final String userLogin;
    private final String nameOrg;
    private final String emailOrg;
    private final String guidOrg;

    VerifMemberForAddProcess(String userLogin, String nameOrg, String emailOrg, String guidOrg) {
        logger = Logger.getLogger(VerifMemberForAddProcess.class);
        this.userLogin = userLogin;
        this.nameOrg = nameOrg;
        this.emailOrg = emailOrg;
        this.guidOrg = guidOrg;
    }

    void addMember() {

        //если значение переменных соответствует регулярным выражениям
//        if (verifRegx == true) {
//            ImplDao superDao = new SuperDao();
//            if (superDao.existsEntryMembers(guidOrg, userLogin) == null) {
//                if (superDao.addNewMember(nameOrg, emailOrg, guidOrg)) {
//                    logger.info("Пользователем \"" + userLogin + "\" добавлен новый участника МЭДО с идентификатором " + guidOrg);
//                } else {
//                    StringBuilder logBuilder = new StringBuilder();
//                    logBuilder.append("При добавлении нового участника МЭДО пользователем \"");
//                    logBuilder.append(userLogin);
//                    logBuilder.append("\" произошла программная ошибка\r\n");
//                    logBuilder.append("\r\n\t\tНаименование организации - ");
//                    logBuilder.append(nameOrg);
//                    logBuilder.append("\r\n\t\tАдресат - ");
//                    logBuilder.append(emailOrg);
//                    logBuilder.append("\r\n\t\tGUID - ");
//                    logBuilder.append(guidOrg);
//                    String logString = logBuilder.toString();
//                    logger.info(logString);
//                }
//            } else {
//
//            }
//
//        }
    }

    /**
     * Сыоответствует ли полученная от пользователя информация регулярным
     * вырабжения строки
     */
    boolean verifRegex() {

        boolean verif = true;
        //если хотя бы одна строка не соответствует регулярному выражения, то проверка не выполнится
        if (!nameOrg.matches("^[а-яА-Я\\s()]+$")) {
            logger.info("Пользователь \"" + userLogin + "\" указал не валидное наименование организации при добавлении участника МЭДО");
            verif = false;
        }
        if (!emailOrg.matches("^[A-Z~_\\s]+$")) {
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
