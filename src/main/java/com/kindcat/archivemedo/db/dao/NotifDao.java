package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.Notifs;
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
 * @vesrion 1.0.4.49 Класс для работы с таблицей уведомлений
 */
class NotifDao {

    private List<Notifs> listNotif;

    private final Logger logger;
    private final StringBuilder logBuilder;
    private String stringlog;
    private StackTraceElement[] stackTrace;

    NotifDao() {
        logger = Logger.getLogger(NotifDao.class);
        logBuilder = new StringBuilder();
    }

    /**
     * возвращает общее число записей в таблице с документами
     */
    long getAllCount(Short idTypePkg) {
        long count = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//            String hql = "select count(idNotif) from Notifs where idInOut=:idInOut";
            String hql = "select count(idNotif) from Notifs where idInOut=" + idTypePkg;
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            //query.setParameter("idInOut", idTypePkg);
            count = (long) query.uniqueResult();
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При выполнения запроса к БД для получения числа записей с уведомлениями возникла программная ошибка");
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
        return count;
    }

    /**
     * Получение списка xml-форматов
     */
    List<Notifs> getAllListByTypePkg(Short idTypePkg, int skip, int notifCountForOnePage) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "from Notifs where idInOut=:idInOut";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе SchemaXml
            Query query = session.createQuery(hql, Notifs.class);//создаю массив объектов с клссом SchemaXml и созданным запросом
            query.setFirstResult(skip);//число пропущенных элементов
            query.setMaxResults(notifCountForOnePage);//число отображаемых элементов
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            query.setCacheMode(CacheMode.IGNORE); // данные yне кешируются
            query.setParameter("idInOut", idTypePkg);
            listNotif = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массив
            transaction.commit();
            session.close();
            logger.debug("Успешно выполнен запрос для получения списка уведомлений");
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При открытии сессии для подключения к БД и получении списка уведомлений произошла программная ошибка");
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
        return listNotif;
    }
}
