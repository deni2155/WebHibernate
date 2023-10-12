package com.kindcat.archivemedo.beans;

/**
 *
 * @author dreamer
 * @version 1.0.0.23 Бин для работы с данными участников МЭДО
 */
class MembersBean {

    private Short idOrg;//идентификатор участника МЭДО
    private String nameOrg;//наименование организации
    private String emailOrg;//наименование организации
    private String guidOrg;//наименование организации

    Short getBeansIdOrg() {
        return idOrg;
    }

    void setBeansIdOrg(Short idOrg) {
        this.idOrg = idOrg;
    }

    String getBeansNameOrg() {
        return nameOrg;
    }

    void setBeansNameOrg(String nameOrg) {
        this.nameOrg = nameOrg;
    }

    String getBeansEmailOrg() {
        return emailOrg;
    }

    void setBeansEmailOrg(String emailOrg) {
        this.emailOrg = emailOrg;
    }

    String getBeansGuidOrg() {
        return guidOrg;
    }

    void setBeansGuidOrg(String guidOrg) {
        this.guidOrg = guidOrg;
    }
}
