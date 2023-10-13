package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.TypePkg;
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
 * @version 1.0.3.39 Класс для работы с типом пакетов (входящий или исходящий)
 */
class TypePkgDao {

    private List<TypePkg> listTypePkg;

    private final Logger logger;
    private final StringBuilder logBuilder;
    private String stringlog;
    private StackTraceElement[] stackTrace;

    TypePkgDao() {
        logger = Logger.getLogger(TypePkgDao.class);
        logBuilder = new StringBuilder();
    }

    /**
     * Получение данных о пользователе по id
     *
     * @param id - процедура получает идентификатор пользователя
     * @return возвращает информацию о пользователе
     */
//    TypePkg findById(int id) {
//        return SessionFactoryUtil.getSessionFactory().openSession().find(TypePkg.class, id);
//    }

    /**
     * Получение списка участников МЭДО без выборки
     */
    List<TypePkg> getAllList() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "from TypePkg order by idTypePkg asc";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql, TypePkg.class);//создаю массив объектов с клссом Users и созданным запросом
//            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            query.setCacheMode(CacheMode.IGNORE); // данные yне кешируются
            listTypePkg = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массив
//            transaction.commit();
            session.close();
            logger.debug("Успешно выполнен запрос для получения списка типа пакетов (входящий или исходящий)");
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При открытии сессии для подключения к БД и получении списка типа пакетов (входящий или исходящий) произошла программная ошибка");
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
