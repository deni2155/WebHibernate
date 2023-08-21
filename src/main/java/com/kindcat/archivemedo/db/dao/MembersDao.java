package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.Members;
import com.kindcat.archivemedo.db.utils.SessionFactoryUtil;
import java.util.Iterator;
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
    private List<Members> listMembers;

    MembersDao() {
        logger = Logger.getLogger(MembersDao.class);
    }

    /**
     * Получение списка участников МЭДО без выборки
     */
    List<Members> getMembersFindAll() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "from Members";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql, Members.class);//создаю массив объектов с клссом Users и созданным запросом
            listMembers = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массиву
//            query.setFirstResult(41);
//            query.setMaxResults(20);
            query.setCacheMode(CacheMode.IGNORE); // данные yне кешируются
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            transaction.commit();
            session.close();
            logger.debug("Успешно выполнен запрос для получения списка участников МЭДО");
        } catch (HibernateException ex) {
            logger.fatal("При открытии сессии для подключения к БД и получении списка участников МЭДО произошла программная ошибка", ex);
        }
        return listMembers;
    }

    /**
     * Получение данных об участнике по id
     *
     * @param id - процедура получает идентификатор участника МЭДО
     * @return возвращает информацию об участнике
     */
    Members findById(int id) {
        return SessionFactoryUtil.getSessionFactory().openSession().find(Members.class, id);
    }

    /**
     * Добавление участника МЭДО
     *
     * @param nameOrg - наименование организации
     * @param email - email участника
     * @param guid - идентификатор участника
     * @return boolean - успешно или не успешно добавлен
     */
    boolean addMember(String nameOrg, String email, String guid) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Members members = new Members();
            members.setNameOrg(nameOrg);
            members.setAddr(email);
            members.setGuid(guid);
            Transaction transaction = session.beginTransaction();
            session.persist(members);
            transaction.commit();
            session.close();
            logger.debug("Успешно выполнен запрос для добавления нового участника МЭДО");
            return true;
        } catch (HibernateException ex) {
            logger.fatal("При добавлении нового участника МЭДО произошла программная ошибка", ex);
            return false;
        }
    }

    /**
     * Проверка существования записи в БД при добавлении нового участника МЭДО
     */
    long existsEntry(String email, String guid) {
        long idMember = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "select count(idMembers) from Members where addr=:a or guid=:g";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setParameter("a", email);
            query.setParameter("g", guid);
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            idMember =(long) query.uniqueResult();
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
//            for (Iterator<Members> it = query.list().iterator(); it.hasNext();) {
//                idMember = it.next().getIdMembers();
//            }
            //listMembers = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массиву
//            query.setFirstResult(41);
//            query.setMaxResults(20);
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logger.fatal("При открытии сессии для подключения к БД и выполнения запроса для проверки наличия добавляемого пользователем участника МЭДО в системе возникла программная ошибка", ex);
        }
        return idMember;
    }
}
