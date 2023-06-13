package com.kindcat.archivemedo.db.utils;

import com.kindcat.archivemedo.db.models.Users;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        Logger logger = Logger.getLogger(HibernateSessionFactoryUtil.class);
        if(sessionFactory==null){
            try{
                Configuration conf = new Configuration().configure();
                conf.addAnnotatedClass(Users.class);
                StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
                sessionFactory = conf.buildSessionFactory(builder.build());
            }catch (HibernateException ex) {
                logger.error("Ошибка при создании сессии для подключения к БД",ex);
            }
        }
        return sessionFactory;
    }
}
