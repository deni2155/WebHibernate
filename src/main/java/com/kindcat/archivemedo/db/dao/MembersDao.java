package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.Members;
import com.kindcat.archivemedo.db.utils.SessionFactoryUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author dreamer
 * @version 1.0.0.18 класс для работы со списком участников МЭДО в БД
 */
class MembersDao {

    private final Logger logger;

    MembersDao() {
        logger = Logger.getLogger(MembersDao.class);
    }

    List<Members> getMembersFindAll() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "from Members";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql, Members.class);//создаю массив объектов с клссом Users и созданным запросом
            List<Members> members = query.list();
//            query.setFirstResult(41);
//            query.setMaxResults(20);
            query.setCacheMode(CacheMode.IGNORE); // данные yне кешируются
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            transaction.commit();
            session.close();
            logger.debug("Успешно выполнен запрос для получения списка участников МЭДО");
            return members;
        }
//        } catch (HibernateException ex) {
//            logger.fatal("При открытии сессии для подключения к БД и получении списка участников МЭДО произошла программная ошибка", ex);
//        }
    }
//    List<Members> MembersFindAll() {
//        List<Members> members = (List<Members>)  SessionFactoryUtil.getSessionFactory().openSession().createQuery("from Members").list();
//        return members;
//    }
}
