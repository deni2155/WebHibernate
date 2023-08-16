package com.kindcat.archivemedo.members;

import com.kindcat.archivemedo.db.dao.ImplDao;
import com.kindcat.archivemedo.db.dao.SuperDao;
import org.apache.log4j.Logger;

/**
 *
 * @author dreamer
 * @version 1.0.0.23
 */
class AddMemberProcess {

    void addMember(String userLogin, String nameOrg, String emailOrg, String guidOrg) {
        Logger logger = Logger.getLogger(AddMemberProcess.class);
        //соответствует ли полученная от пользователя информация регулярным вырабжения строки
        boolean verifRegx = true;
//если хотя бы одна строка не соответствует регулярному выражения, то проверка не выполнится
        if (!nameOrg.matches("^[а-яА-Я\\s()]+$")) {
            logger.info("Пользователь \"" + userLogin + "\" указал не валидное наименование организации при добавлении участника МЭДО");
            verifRegx = false;
        }
        if (!emailOrg.matches("^[A-Z~_\\s]+$")) {
            logger.info("Пользователь \"" + userLogin + "\" указал не валидный email организации организации при добавлении участника МЭДО");
            verifRegx = false;
        }
        if (!guidOrg.matches("^[a-z0-9-]+$")) {
            logger.info("Пользователь \"" + userLogin + "\" указал не валидный GUID организации организации при добавлении участника МЭДО");
            verifRegx = false;
        }

        //если значение переменных соответствует регулярным выражениям
        if (verifRegx == true) {
            ImplDao superDao = new SuperDao();
            if (superDao.addNewMember(nameOrg, emailOrg, guidOrg)) {
                logger.info("Пользователем \"" + userLogin + "\" добавлен новый участника МЭДО с идентификатором " + guidOrg);
            } else {
                StringBuilder logBuilder = new StringBuilder();
                logBuilder.append("При добавлении нового участника МЭДО пользователем \"");
                logBuilder.append(userLogin);
                logBuilder.append("\" произошла программная ошибка\r\n");
                logBuilder.append("\r\n\t\tНаименование организации - ");
                logBuilder.append(nameOrg);
                logBuilder.append("\r\n\t\tАдресат - ");
                logBuilder.append(emailOrg);
                logBuilder.append("\r\n\t\tGUID - ");
                logBuilder.append(guidOrg);
                String logString = logBuilder.toString();
                logger.info(logString);
            }
        }
    }
}
