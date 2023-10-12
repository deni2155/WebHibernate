package com.kindcat.archivemedo.beans;

public class SuperBeans implements ImplBeans {

    private final UsersBeans usersBeans;//пользователи системы
    private final MembersBean memberBean;//участники МЭДО

    public SuperBeans() {
        usersBeans = new UsersBeans();
        memberBean = new MembersBean();
    }

    /*


    Пользователи системы



     */
    @Override
    public int getBeansIdUser() {
        return usersBeans.getBeanIdUser();
    }

    @Override
    public void setBeansIdUser(int beansIdUser) {
        usersBeans.setBeanIdUser(beansIdUser);
    }

    @Override
    public String getBeansLoginUser() {
        return usersBeans.getBeanLogin();
    }

    @Override
    public void setBeansLoginUser(String beansLogin) {
        usersBeans.setBeanLogin(beansLogin);
    }

    @Override
    public String getBeansHash() {
        return usersBeans.getBeanHash();
    }

    @Override
    public void setBeansHash(String beansHash) {
        usersBeans.setBeansHash(beansHash);
    }

    @Override
    public String getBeansFNameUser() {
        return usersBeans.getBeanFName();
    }

    @Override
    public void setBeanFNameUser(String beansFName) {
        usersBeans.setBeanFName(beansFName);
    }

    /*


Участники МЭДО


     */

    @Override
    public Short getBeanIdOrg() {
        return memberBean.getBeansIdOrg();
    }

    @Override
    public void setBeanIdOrg(Short idOrg) {
        memberBean.setBeansIdOrg(idOrg);
    }

    @Override
    public String getBeanNameOrg() {
        return memberBean.getBeansNameOrg();
    }

    @Override
    public void setBeanNameOrg(String nameOrg) {
        memberBean.setBeansNameOrg(nameOrg);
    }

    @Override
    public String getBeanEmailOrg() {
        return memberBean.getBeansEmailOrg();
    }

    @Override
    public void setBeanEmailOrg(String emailOrg) {
        memberBean.setBeansEmailOrg(emailOrg);
    }

    @Override
    public String getBeanGuidOrg() {
        return memberBean.getBeansGuidOrg();
    }

    @Override
    public void setBeanGuidOrg(String guidOrg) {
        memberBean.setBeansGuidOrg(guidOrg);
    }
//
//    UsersBeans userBeans = new UsersBeans();//ссылка на бины УЗ пользователя
//
//    /**
//     *
//     * Информация об УЗ пользователе
//     *
//     */
//    /**
//     * @return beansIdUser - идентификатор УЗ пользователя
//     */
//    @Override
//    public int getUserBeansIdUser() {
//        return userBeans.getBeansIdUser();
//    }
//
//    /**
//     * @param beansIdUser - идентификатор УЗ пользователя
//     */
//    @Override
//    public void setUserBeansIdUser(int beansIdUser) {
//        userBeans.setBeansIdUser(beansIdUser);
//    }
//
//    /**
//     * @return beansLogin - логин пользователя
//     */
//    @Override
//    public String getUserBeansLogin() {
//        return userBeans.getBeansLogin();
//    }
//
//    /**
//     * @param beansLogin - логин пользователя
//     */
//    @Override
//    public void setUserBeansLogin(String beansLogin) {
//        userBeans.setBeansLogin(beansLogin);
//    }
//
//    /**
//     * @return beansHash - хеш пароля пользователя
//     */
//    @Override
//    public String getUserBeansHash() {
//        return userBeans.getBeansHash();
//    }
//
//    /**
//     * @param beansHash - хеш пароля пользователя
//     */
//    @Override
//    public void setUserBeansHash(String beansHash) {
//        userBeans.setBeansHash(beansHash);
//    }
//
//    /**
//     * @return beansFName - полное имя пользователя
//     */
//    @Override
//    public String getUserBeansFName() {
//        return userBeans.getBeansFName();
//    }
//
//    /**
//     * @param beansFName - полное имя пользователя
//     */
//    @Override
//    public void setUserBeansFName(String beansFName) {
//        userBeans.setBeansFName(beansFName);
//    }
}
