package com.kindcat.archivemedo.beans;

public interface SuperBeanImpl {

    /*


Участники МЭДО


     */
    public int getBeanIdOrg();

    public void setBeanIdOrg(int idOrg);

    public String getBeanNameOrg();

    public void setBeanNameOrg(String nameOrg);

    public String getBeanEmailOrg();

    public void setBeanEmailOrg(String emailOrg);

    public String getBeanGuidOrg();

    public void setBeanGuidOrg(String guidOrg);

    /*
     *


     * Информация об УЗ пользователя


     *
     */
    public int getBeansIdUser();

    public void setBeansIdUser(int beansIdUser);

    public String getBeansLogin();

    public void setBeansLogin(String beansLogin);

    public String getBeansHash();

    public void setBeansHash(String beansHash);

    public String getBeansFName();

    public void setBeanFName(String beansFName);
}
