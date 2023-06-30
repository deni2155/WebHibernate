package com.kindcat.archivemedo.db.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
Модель для работы с таблицей users в БД
*/
@Entity
@Table(name="Users")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="Users")
public class Users implements Serializable {
    //идентификатор УЗ пользователя
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_user")
    private int idUser;

    //логин пользователя
    @Column(name="login")
    private String login;

    //хэш пароля пользователя
    @Column(name="hash")
    private String hash;

    //ФИО пользователя
    @Column(name="fname")
    private String fullName;

    public int getIdUser() {
        return idUser;
    }

    public void setId_user(int idUser) {
        this.idUser = idUser;
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
