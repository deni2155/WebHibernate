package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.Users;
import com.kindcat.archivemedo.db.utils.HibernateSessionFactoryUtil;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class UsersDao {
    //
    //Поиск пользователя по ID
    //
    public Users findById(int id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Users.class, id);
    }

    public int findByLogin(String login){
        int idUser=0;   
        Session session=HibernateSessionFactoryUtil.getSessionFactory().openSession();
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
