package com.kindcat.archivemedo.input.beans;

/**
Класс для хранения данных о пользователе
*/
public class UserBeans implements UserBeansImpl {
    //идентификатор пользователя
    private int idUser;
    //логин пользователя
    private String username;
    //пароль пользователя
    private String password;
    //полное имя пользователя
    private String fname;

    /**
    * @return id пользователя
    */
    @Override
    public int getIdUser() {
        return idUser;
    }
    /**
    * @param idUser 
    * записывает идентификатор пользователя
    */
    @Override
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    /**
    * @return логин пользователя
    */
    @Override
    public String getUsername() {
        return username;
    }
    /**
    * @param username
    * записывает логин пользователя
    */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }
    /**
    * @return пароль пользователя
    */
    @Override
    public String getPassword() {
        return password;
    }
    /**
    * @param password
    * Записывает пароль пользователя
    */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    /**
    * @return возвращает полное имя пользователя
    */
    @Override
    public String getFname() {
        return fname;
    }
    /**
     * @param fname
    * возвращает полное имя пользователя
    */
    @Override
    public void setFname(String fname) {
        this.fname = fname;
    }
}
