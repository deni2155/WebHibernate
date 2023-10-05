package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.Documents;
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
 */
class DocumentsDao {

    private List<Documents> listDocs;

    private final Logger logger;
    private final StringBuilder logBuilder;
    private String stringlog;
    private StackTraceElement[] stackTrace;

    DocumentsDao() {
        logger = Logger.getLogger(DocumentsDao.class);
        logBuilder = new StringBuilder();
    }

    /**
     * Получение списка xml-форматов
     */
    List<Documents> getAllListByTypePkg(Short idTypePkg) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "from Documents where idInOut=" + idTypePkg;//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе SchemaXml
            Query query = session.createQuery(hql, Documents.class);//создаю массив объектов с клссом SchemaXml и созданным запросом
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            query.setCacheMode(CacheMode.IGNORE); // данные yне кешируются
            listDocs = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массив
            transaction.commit();
            session.close();
            logger.debug("Успешно выполнен запрос для получения списка документов");
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При открытии сессии для подключения к БД и получении списка документов произошла программная ошибка");
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
        return listDocs;
    }
}
