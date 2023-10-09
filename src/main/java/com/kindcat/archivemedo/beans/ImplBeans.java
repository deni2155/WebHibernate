package com.kindcat.archivemedo.beans;

public interface ImplBeans {

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

    public String getBeansLoginUser();

    public void setBeansLoginUser(String beansLogin);

    public String getBeansHash();

    public void setBeansHash(String beansHash);

    public String getBeansFNameUser();

    public void setBeanFNameUser(String beansFName);
}
