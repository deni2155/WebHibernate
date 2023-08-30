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
 * @version 1.0.1.33 класс для работы со списком участников МЭДО в БД
 */
class MembersDao {

    private final Logger logger;
    private List<Members> listMembers;

    MembersDao() {
        logger = Logger.getLogger(MembersDao.class);
    }

    long getCountMembers() {
        long count = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "select count(idMembers) from Members";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            count = (long) query.uniqueResult();
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logger.fatal("При выполнения запроса к БД для проверки наличия записи по email в системе возникла программная ошибка", ex);
        }
        return count;
    }

    /**
     * Получение списка участников МЭДО без выборки
     */
    List<Members> getMembersFindAll() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "from Members order by idMembers asc";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            Query query = session.createQuery(hql, Members.class);//создаю массив объектов с клссом Users и созданным запросом
//            query.setFirstResult(20);//число пропущенных элементов
//            query.setMaxResults(20);//число отображаемых элементов
            listMembers = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массиву
            query.setCacheMode(CacheMode.IGNORE); // данные yне кешируются
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
     * Обновление участника МЭДО
     */
    int updateMember(int idMember, String nameOrg, String email, String guid) {
        int resultUpdate = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "update Members set nameOrg=:n, addr=:a, guid=:g where idMembers=" + idMember;//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setParameter("n", nameOrg);
            query.setParameter("a", email);
            query.setParameter("g", guid);
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            resultUpdate = query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logger.fatal("При открытии сессии для выполнения запроса на имзменение участника МЭДО возникла программная ошибка", ex);
        }
        return resultUpdate;
    }

    /**
     * Удаление участника МЭДО
     */
    int deleteMember(int idMember) {
        int resultDelete = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "delete Members where idMembers=" + idMember;//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            resultDelete = query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logger.fatal("При открытии сессии для выполнения запроса для удаления участника МЭДО возникла программная ошибка", ex);
        }
        return resultDelete;
    }

    /**
     * Проверка существования записи с такими же данными в БД при добавлении
     * нового участника МЭДО
     */
    long getCountByEmailOrGuid(String email, String guid) {
        long count = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "select count(idMembers) from Members where addr=:a or guid=:g";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setParameter("a", email);
            query.setParameter("g", guid);
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            count = (long) query.uniqueResult();
//            for (Iterator<Members> it = query.list().iterator(); it.hasNext();) {
//                idMember = it.next().getIdMembers();
//            }
            //listMembers = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массиву
//            query.setFirstResult(41);
//            query.setMaxResults(20);
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logger.fatal("При выполнения запроса к БД для проверки наличия добавляемого пользователем участника МЭДО в системе возникла программная ошибка", ex);
        }
        return count;
    }

    /**
     * проверка сущестование других записей в БД с такими же данными при
     * обновлении текущей записи
     */
    long getCountByEmailOrGuidAndNotEqualsId(int idMember, String email, String guid) {
        long count = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "select count(idMembers) from Members where (not(idMembers=" + idMember + ")) and (addr=:a or guid=:g)";
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setParameter("a", email);
            query.setParameter("g", guid);
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            count = (long) query.uniqueResult();
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logger.fatal("При проверки наличия участника МЭДО для внесения изменений под пользователем участника МЭДО в системе возникла программная ошибка", ex);
        }
        return count;
    }

    /**
     * удаление участника МЭДО
     */
    /**
     * Поиск участников в системе по email для проверки при внесении изменений
     */
//    long getCountByEmailOrg(String email) {
//        long count = 0;
//        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//            String hql = "select count(idMembers) from Members where addr=:a";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
//            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
//            query.setParameter("a", email);
//            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
//            count = (long) query.uniqueResult();
//            Transaction transaction = session.beginTransaction();//запускаю транзакцию
////            for (Iterator<Members> it = query.list().iterator(); it.hasNext();) {
////                idMember = it.next().getIdMembers();
////            }
//            //listMembers = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массиву
////            query.setFirstResult(41);
////            query.setMaxResults(20);
//            transaction.commit();
//            session.close();
//        } catch (HibernateException ex) {
//            logger.fatal("При выполнения запроса к БД для проверки наличия записи по email в системе возникла программная ошибка", ex);
//        }
//        return count;
//    }
    /**
     * Поиск участников в системе по GUID для проверки при внесении изменений
     */
//    long countGuidOrg(String guid) {
//        long count = 0;
//        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//            String hql = "select count(idMembers) from Members where guid=:g";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
//            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
//            query.setParameter("g", guid);
//            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
//            count = (long) query.uniqueResult();
//            Transaction transaction = session.beginTransaction();//запускаю транзакцию
////            for (Iterator<Members> it = query.list().iterator(); it.hasNext();) {
////                idMember = it.next().getIdMembers();
////            }
//            //listMembers = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массиву
////            query.setFirstResult(41);
////            query.setMaxResults(20);
//            transaction.commit();
//            session.close();
//        } catch (HibernateException ex) {
//            logger.fatal("При выполнении запроса для проверки наличия записи по GUID в системе возникла программная ошибка", ex);
//        }
//        return count;
//    }
}
