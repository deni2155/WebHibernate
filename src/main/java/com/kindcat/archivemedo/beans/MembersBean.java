package com.kindcat.archivemedo.beans;

/**
 *
 * @author dreamer
 * @version 1.0.0.23 Бин для работы с данными участников МЭДО
 */
class MembersBean {

    private String nameOrg;//наименование организации
    private String emailOrg;//наименование организации
    private String guidOrg;//наименование организации

    String getNameOrg() {
        return nameOrg;
    }

    void setNameOrg(String nameOrg) {
        this.nameOrg = nameOrg;
    }

    String getEmailOrg() {
        return emailOrg;
    }

    void setEmailOrg(String emailOrg) {
        this.emailOrg = emailOrg;
    }

    String getGuidOrg() {
        return guidOrg;
    }

    void setGuidOrg(String guidOrg) {
        this.guidOrg = guidOrg;
    }
}
