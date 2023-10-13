package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.Receipts;
import com.kindcat.archivemedo.db.utils.SessionFactoryUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
//import org.hibernate.Query;
import org.hibernate.Session;
//import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author dreamer
 * @version 1.0.4.51 Класс для работы с моделью класса
 */
class ReceiptDao {

    private List<Receipts> listReceipts;

    private final Logger logger;
    private final StringBuilder logBuilder;
    private String stringlog;
    private StackTraceElement[] stackTrace;

    ReceiptDao() {
        logger = Logger.getLogger(ReceiptDao.class);
        logBuilder = new StringBuilder();
    }

    /**
     * возвращает общее число записей в таблице с уведомлениями
     */
    long getAllCount(Short idTypePkg) {
        long count = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "select count(idReceipt) from Receipts where idInOut=:idInOut";
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            query.setParameter("idInOut", idTypePkg);
            count = (long) query.uniqueResult();
//            Transaction transaction = session.beginTransaction();//запускаю транзакцию
//            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При выполнения запроса к БД для получения числа записей с квитанциями возникла программная ошибка");
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
    List<Receipts> getAllListByTypePkg(Short idTypePkg, int skip, int receiptsCountForOnePage) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "from Receipts where idInOut=" + idTypePkg;//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе SchemaXml
            Query query = session.createQuery(hql, Receipts.class);//создаю массив объектов с клссом SchemaXml и созданным запросом
            query.setFirstResult(skip);//число пропущенных элементов
            query.setMaxResults(receiptsCountForOnePage);//число отображаемых элементов
//            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            query.setCacheMode(CacheMode.IGNORE); // данные yне кешируются
            listReceipts = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массив
//            transaction.commit();
//            session.close();
            logger.debug("Успешно выполнен запрос для получения списка квитанций");
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При открытии сессии для подключения к БД и получении списка квитанций произошла программная ошибка");
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
        return listReceipts;
    }
}
