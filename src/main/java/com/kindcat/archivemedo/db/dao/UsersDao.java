package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.Users;
import com.kindcat.archivemedo.db.utils.SessionFactoryUtil;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Получение данных с таблицы Users
 */
class UsersDao {

    Logger logger = Logger.getLogger(UsersDao.class);

    /**
     * Получение данных о пользователе по id
     *
     * @param id - процедура получает идентификатор пользователя
     * @return возвращает информацию о пользователе
     */
    Users findById(int id) {
        Users user = new Users();
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession();) {
            user = session.get(Users.class, id);
            session.close();

        } catch (HibernateException ex) {
            logger.fatal("При открытии сессии для подключения к БД и получении УЗ пользователя произошла программная ошибка", ex);
        }
        return user;
        //было
        //return SessionFactoryUtil.getSessionFactory().openSession().get(Users.class, id);
    }

    /**
     * Получение информации о пользователе
     *
     * @param login - процедура получает логин пользователя
     * @return id пользователя
     */
    int findByLogin(String login) {
        int idUser = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession();) {
            Query query = session.createSQLQuery("SELECT id_user,login,hash,fname FROM users WHERE login=:login").addEntity(Users.class).setParameter("login", login);
            Transaction transaction = session.beginTransaction();
            List<Users> users = query.list();
            for (Iterator<Users> it = users.iterator(); it.hasNext();) {
                idUser = it.next().getId_user();
            }
            transaction.commit();
            session.close();
//        for (Iterator<Users> it=users.iterator();it.hasNext();){
//            Users user=(Users) it.next();
//        }
//        return (Users) users;
        } catch (HibernateException ex) {
            logger.fatal("При открытии сессии для подключения к БД и получении логина пользователя произошла программная ошибка", ex);
        }
        return idUser;
    }
}
