package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.SchemaXml;
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
 * @version 1.0.4.41 Класс для вывода формата xml
 */
class SchemaXmlDao {

    private List<SchemaXml> listTypePkg;

    private final Logger logger;
    private final StringBuilder logBuilder;
    private String stringlog;
    private StackTraceElement[] stackTrace;

    SchemaXmlDao() {
        logger = Logger.getLogger(SchemaXmlDao.class);
        logBuilder = new StringBuilder();
    }

    /**
     * Получение списка xml-форматов
     */
    List<SchemaXml> getAllList() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "from SchemaXml order by nameSchema asc";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе SchemaXml
            Query query = session.createQuery(hql, SchemaXml.class);//создаю массив объектов с клссом SchemaXml и созданным запросом
//            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            query.setCacheMode(CacheMode.IGNORE); // данные yне кешируются
            listTypePkg = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массив
//            transaction.commit();
            session.close();
            logger.debug("Успешно выполнен запрос для получения списка схем xml");
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При открытии сессии для подключения к БД и получении списка схем xml произошла программная ошибка");
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
        return listTypePkg;
    }
}
