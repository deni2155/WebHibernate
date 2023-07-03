package com.kindcat.archivemedo.beans;

public class SuperBeans implements SuperBeansImpl {

    UsersBeans userBeans = new UsersBeans();//ссылка на бины УЗ пользователя

    /**
     *
     * Информация об УЗ пользователе
     *
     */
    /**
     * @return beansIdUser - идентификатор УЗ пользователя
     */
    @Override
    public int getUserBeansIdUser() {
        return userBeans.getBeansIdUser();
    }

    /**
     * @param beansIdUser - идентификатор УЗ пользователя
     */
    @Override
    public void setUserBeansIdUser(int beansIdUser) {
        userBeans.setBeansIdUser(beansIdUser);
    }

    /**
     * @return beansLogin - логин пользователя
     */
    @Override
    public String getUserBeansLogin() {
        return userBeans.getBeansLogin();
    }

    /**
     * @param beansLogin - логин пользователя
     */
    @Override
    public void setUserBeansLogin(String beansLogin) {
        userBeans.setBeansLogin(beansLogin);
    }

    /**
     * @return beansHash - хеш пароля пользователя
     */
    @Override
    public String getUserBeansHash() {
        return userBeans.getBeansHash();
    }

    /**
     * @param beansHash - хеш пароля пользователя
     */
    @Override
    public void setUserBeansHash(String beansHash) {
        userBeans.setBeansHash(beansHash);
    }

    /**
     * @return beansFName - полное имя пользователя
     */
    @Override
    public String getUserBeansFName() {
        return userBeans.getBeansFName();
    }

    /**
     * @param beansFName - полное имя пользователя
     */
    @Override
    public void setUserBeansFName(String beansFName) {
        userBeans.setBeansFName(beansFName);
    }
}
