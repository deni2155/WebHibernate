package com.kindcat.archivemedo.db.utils;

import com.kindcat.archivemedo.db.models.Users;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Класс по созданию сессии hibernate
 */
public class SessionFactoryUtil {

    /**
     * Переменная сессии hibernate
     */
    private static SessionFactory sessionFactory;

    /**
     * Создание объекта сессии
     *
     */
    public static void setSessionFactory() {
        Logger logger = Logger.getLogger(SessionFactoryUtil.class);
        if (sessionFactory == null) {
            try {
                Configuration conf = new Configuration().configure();
                conf.addAnnotatedClass(Users.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
                sessionFactory = conf.buildSessionFactory(builder.build());
                logger.debug("Выполнена иницализация объекта SessionFactory");
            } catch (HibernateException ex) {
                logger.error("Ошибка при инициализации объекта SessionFactory для подключения к БД:", ex);
            }
        }
    }

    /**
     * @return sessionFactory - объект для работы с БД
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
