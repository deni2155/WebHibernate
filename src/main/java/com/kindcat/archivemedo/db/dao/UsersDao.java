package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.Users;
import com.kindcat.archivemedo.db.utils.SessionFactoryUtil;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
* Получение данных с таблицы Users
*/
class UsersDao{
    /**
    * Получение данных о пользователе по id
    * @param id - процедура получает идентификатор пользователя
    * @return возвращает информацию о пользователе
    */
    Users findById(int id){
        return SessionFactoryUtil.getSessionFactory().openSession().get(Users.class, id);
    }

    /**
    * Получение информации о пользователе
    * @param login - процедура получает логин пользователя
    * @return id пользователя
    */
    int findByLogin(String login){
        int idUser=0;   
        Session session=SessionFactoryUtil.getSessionFactory().openSession();
        Query query=session.createSQLQuery("SELECT id_user,login,hash,fname FROM users WHERE login=:login").addEntity(Users.class).setParameter("login", login);
        List<Users> users=query.list();
        for(Iterator<Users> it=users.iterator();it.hasNext();){
            idUser=it.next().getId_user();
        }
//        for (Iterator<Users> it=users.iterator();it.hasNext();){
//            Users user=(Users) it.next();
//        }
//        return (Users) users;
        return idUser;
    }
}
