package com.kindcat.archivemedo.db.models;

import java.util.Iterator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//
//Модель для работы с таблицей users в БД
//
@Entity
@Table(name="users")
public class Users {
    //идентификатор УЗ пользователя
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_user")
    private int id_user;

    //логин пользователя
    @Column(name="login")
    private String login;

    //хэш пароля пользователя
    @Column(name="hash")
    private String hash;

    //ФИО пользователя
    @Column(name="fname")
    private String fullName;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
