package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.Users;
import com.kindcat.archivemedo.db.utils.SessionFactoryUtil;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
//import org.hibernate.Query;
import org.hibernate.Session;
//import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * Получение данных с таблицы Users, класс для работы с моделью БД пользователя
 *
 * @version 1.0.36
 */
class UsersDao {

    private final Logger logger;
    private final StringBuilder logBuilder;
    private String stringlog;
    private StackTraceElement[] stackTrace;

    UsersDao() {
        logger = Logger.getLogger(UsersDao.class);
        logBuilder = new StringBuilder();
    }

    /**
     * Получение данных о пользователе по id
     *
     * @param id - процедура получает идентификатор пользователя
     * @return возвращает информацию о пользователе
     */
    Users findById(int id) {
        return SessionFactoryUtil.getSessionFactory().openSession().find(Users.class, id);
    }

    /**
     * Получение информации о пользователе по логину
     *
     * @param login - процедура получает логин пользователя
     * @return id пользователя
     */
    int findInLoginById(String login) {
        int idUser = 0;
        //если передан путсой логин пользователя
        if (!login.isEmpty()) {
            try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
                String hql = "from Users where login=:login";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
                Query<Users> query = session.createQuery(hql, Users.class);//создаю массив объектов с клссом Users и созданным запросом
                query.setParameter("login", login);//добавляю параметр в запрос
                query.setCacheMode(CacheMode.NORMAL); // данные читаются из кеша и добавляются в него
//                Transaction transaction = session.beginTransaction();//запускаю транзакцию
                for (Iterator<Users> it = query.list().iterator(); it.hasNext();) {
                    idUser = it.next().getIdUser();
                }
//                transaction.commit();
                session.close();
                logger.debug("Успешно выполнен запрос для получения информации об УЗ пользователя по логину");
            } catch (HibernateException ex) {
                logBuilder.setLength(0);
                logBuilder.append("При открытии сессии для подключения к БД и получении логина пользователя произошла программная ошибка");
                logBuilder.append("\n");
                logBuilder.append(ex.getMessage());
                logBuilder.append("\n");
                stackTrace = ex.getStackTrace();
                for (StackTraceElement element : stackTrace) {
                    logBuilder.append(element.toString());
                    logBuilder.append("\n");
                }
                stringlog = logBuilder.toString();
                logger.error(stringlog);
            }
        } else {
            logger.warn("Получен пустой логин для поиска информации о пользователе");
        }
        return idUser;
    }
}
//Другой вариант выполнения запроса
//        try (Session session = SessionFactoryUtil.getSessionFactory().openSession();) {
//            Query query = session.createSQLQuery("SELECT id_user,login,hash,fname FROM users WHERE login=:login").addEntity(Users.class).setParameter("login", login);
//            Transaction transaction = session.beginTransaction();
//            List<Users> users = query.list();
//            for (Iterator<Users> it = users.iterator(); it.hasNext();) {
//                idUser = it.next().getId_user();
//            }
//            transaction.commit();
//            session.close();
////        for (Iterator<Users> it=users.iterator();it.hasNext();){
////            Users user=(Users) it.next();
////        }
////        return (Users) users;
//        } catch (HibernateException ex) {
//            logger.fatal("При открытии сессии для подключения к БД и получении логина пользователя произошла программная ошибка", ex);
//        }
