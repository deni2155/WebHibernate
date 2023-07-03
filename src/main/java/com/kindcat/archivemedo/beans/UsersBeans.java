package com.kindcat.archivemedo.beans;

/**
 * Класс для хранения информации о пользователе
 */
class UsersBeans {

    private int beansIdUser;
    private String beansLogin;
    private String beansHash;
    private String beansFName;

    int getBeansIdUser() {
        return beansIdUser;
    }

    void setBeansIdUser(int beansIdUser) {
        this.beansIdUser = beansIdUser;
    }

    String getBeansLogin() {
        return beansLogin;
    }

    void setBeansLogin(String beansLogin) {
        this.beansLogin = beansLogin;
    }

    String getBeansHash() {
        return beansHash;
    }

    void setBeansHash(String beansHash) {
        this.beansHash = beansHash;
    }

    String getBeansFName() {
        return beansFName;
    }

    void setBeansFName(String beansFName) {
        this.beansFName = beansFName;
    }
}
