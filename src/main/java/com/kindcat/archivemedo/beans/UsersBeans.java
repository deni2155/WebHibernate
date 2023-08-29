package com.kindcat.archivemedo.beans;

/**
 * Класс для хранения информации о пользователе
 */
class UsersBeans {

    private int beanIdUser;
    private String beanLogin;
    private String beanHash;
    private String beanFName;

    int getBeanIdUser() {
        return beanIdUser;
    }

    void setBeanIdUser(int beansIdUser) {
        this.beanIdUser = beansIdUser;
    }

    String getBeanLogin() {
        return beanLogin;
    }

    void setBeanLogin(String beansLogin) {
        this.beanLogin = beansLogin;
    }

    String getBeanHash() {
        return beanHash;
    }

    void setBeansHash(String beansHash) {
        this.beanHash = beansHash;
    }

    String getBeanFName() {
        return beanFName;
    }

    void setBeanFName(String beansFName) {
        this.beanFName = beansFName;
    }
}
