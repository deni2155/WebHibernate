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
    private final StringBuilder logBuilder;
    private String stringlog;
    private StackTraceElement[] stackTrace;

    private List<Members> listMembers;

    MembersDao() {
        logger = Logger.getLogger(MembersDao.class);
        logBuilder = new StringBuilder();
    }

    long getAllCountMembers() {
        long count = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "select count(idMembers) from Members";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            count = (long) query.uniqueResult();
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При выполнения запроса к БД для проверки наличия записи по email в системе возникла программная ошибка");
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
     * Получение списка участников МЭДО без выборки
     */
    List<Members> getAllList(int skip, int membersCountForOnePage) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
//            String hql = "from Members order by idMembers asc";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            String hql = "from Members";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
//            String hql = "from Members order by idMembers asc";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql, Members.class);//создаю массив объектов с клссом Users и созданным запросом
            query.setFirstResult(skip);//число пропущенных элементов
            query.setMaxResults(membersCountForOnePage);//число отображаемых элементов
            query.setCacheMode(CacheMode.IGNORE); // данные yне кешируются
            listMembers = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массив
            transaction.commit();
            session.close();
            logger.debug("Успешно выполнен запрос для получения списка участников МЭДО");
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При открытии сессии для подключения к БД и получении списка участников МЭДО произошла программная ошибка");
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
        return listMembers;
    }

    /**
     * Возвращает число строк при поиске по названию организации
     */
    long getCountListSearchByNameOrg(String nameOrg) {
        long count = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "select count(idMembers) from Members as m where lower(m.nameOrg) like :org";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            query.setParameter("org", "%" + nameOrg + "%");
            count = (long) query.uniqueResult();
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При выполнении запроса к БД для вычисления числа записей при поиске орагнизации по её наименованию возникла программная ошибка");
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
     * Выполняет поиск по названию организации
     */
    List<Members> getListSearchByNameOrg(String nameOrg, int skip, int membersCountForOnePage) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "from Members as m where lower(m.nameOrg) like :org order by idMembers asc";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql, Members.class);//создаю массив объектов с клссом Users и созданным запросом
            query.setParameter("org", "%" + nameOrg + "%");
            query.setFirstResult(skip);//число пропущенных элементов
            query.setMaxResults(membersCountForOnePage);//число отображаемых элементов
            query.setCacheMode(CacheMode.IGNORE); // данные yне кешируются
            listMembers = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массив
            transaction.commit();
            session.close();
            logger.debug("Успешно выполнен запрос для получения списка участников МЭДО");
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При открытии сессии для поиска участника МЭДО в БД по наимаенованию организации произошла программная ошибка");
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
        return listMembers;
    }

    /**
     * Возвращает число строк при поиске по email участника
     */
    long getCountListSearchByEmailOrg(String emailOrg) {
        long count = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "select count(idMembers) from Members as m where upper(m.addr) like :email";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            query.setParameter("email", "%" + emailOrg + "%");
            count = (long) query.uniqueResult();
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При выполнении запроса к БД для вычисления числа записей при поиске орагнизации по email возникла программная ошибка");
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
     * Выполняет поиск по email организации
     */
    List<Members> getListSearchByEmailOrg(String emailOrg, int skip, int membersCountForOnePage) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "from Members as m where upper(m.addr) like :email order by idMembers asc";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql, Members.class);//создаю массив объектов с клссом Users и созданным запросом
            query.setParameter("email", "%" + emailOrg + "%");
            query.setFirstResult(skip);//число пропущенных элементов
            query.setMaxResults(membersCountForOnePage);//число отображаемых элементов
            query.setCacheMode(CacheMode.IGNORE); // данные yне кешируются
            listMembers = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массив
            transaction.commit();
            session.close();
            logger.debug("Успешно выполнен запрос для получения списка участников МЭДО");
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При открытии сессии для поиска участника МЭДО в БД по email организации произошла программная ошибка");
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
        return listMembers;
    }

    /**
     * Возвращает число строк при поиске по GUID участника
     */
    long getCountListSearchByGuidOrg(String guidOrg) {
        long count = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "select count(idMembers) from Members as m where lower(m.guid) like :guid";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            query.setParameter("guid", "%" + guidOrg + "%");
            count = (long) query.uniqueResult();
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При выполнении запроса к БД для вычисления числа записей при поиске орагнизации по GUID возникла программная ошибка");
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
     * Выполняет поиск по GUID организации
     */
    List<Members> getListSearchByGuidOrg(String guidOrg, int skip, int membersCountForOnePage) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "from Members as m where lower(m.guid) like :guid order by idMembers asc";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql, Members.class);//создаю массив объектов с клссом Users и созданным запросом
            query.setParameter("guid", "%" + guidOrg + "%");
            query.setFirstResult(skip);//число пропущенных элементов
            query.setMaxResults(membersCountForOnePage);//число отображаемых элементов
            query.setCacheMode(CacheMode.IGNORE); // данные yне кешируются
            listMembers = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массив
            transaction.commit();
            session.close();
            logger.debug("Успешно выполнен запрос для получения списка участников МЭДО");
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При открытии сессии для поиска участника МЭДО в БД по GUID организации произошла программная ошибка");
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
        return listMembers;
    }

    /**
     * Возвращает число строк при поиске по названию и email организации
     */
    long getCountListSearchByNameAndEmailOrg(String nameOrg, String emailOrg) {
        long count = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "select count(idMembers) from Members as m where lower(m.nameOrg) like :org and upper(m.addr) like :email";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            query.setParameter("org", "%" + nameOrg + "%");
            query.setParameter("email", "%" + emailOrg + "%");
            count = (long) query.uniqueResult();
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При выполнении запроса к БД для вычисления числа записей при поиске орагнизации по её наименованию и email возникла программная ошибка");
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
     * Выполняет поиск по названию организации и email орагнизации
     */
    List<Members> getListSearchByNameAndEmailOrg(String nameOrg, String emailOrg, int skip, int membersCountForOnePage) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "from Members as m where lower(m.nameOrg) like :org and upper(m.addr) like :email order by idMembers asc";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql, Members.class);//создаю массив объектов с клссом Users и созданным запросом
            query.setParameter("org", "%" + nameOrg + "%");
            query.setParameter("email", "%" + emailOrg + "%");
            query.setFirstResult(skip);//число пропущенных элементов
            query.setMaxResults(membersCountForOnePage);//число отображаемых элементов
            query.setCacheMode(CacheMode.IGNORE); // данные yне кешируются
            listMembers = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массив
            transaction.commit();
            session.close();
            logger.debug("Успешно выполнен запрос для получения списка участников МЭДО");
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При открытии сессии для поиска участника МЭДО в БД по наимаенованию организации и email произошла программная ошибка");
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
        return listMembers;
    }

    /**
     * Возвращает число строк при поиске по названию и GUID организации
     */
    long getCountListSearchByNameAndGuidOrg(String nameOrg, String guidOrg) {
        long count = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "select count(idMembers) from Members as m where lower(m.nameOrg) like :org and lower(m.guid) like :guid";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            query.setParameter("org", "%" + nameOrg + "%");
            query.setParameter("guid", "%" + guidOrg + "%");
            count = (long) query.uniqueResult();
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При выполнении запроса к БД для вычисления числа записей при поиске орагнизации по её наименованию возникла программная ошибка");
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
     * Выполняет поиск по названию и GUID организации
     */
    List<Members> getListSearchByNameAndGuid(String nameOrg, String guidOrg, int skip, int membersCountForOnePage) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "from Members as m where lower(m.nameOrg) like :org and lower(m.guid) like :guid order by idMembers asc";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql, Members.class);//создаю массив объектов с клссом Users и созданным запросом
            query.setParameter("org", "%" + nameOrg + "%");
            query.setParameter("guid", "%" + guidOrg + "%");
            query.setFirstResult(skip);//число пропущенных элементов
            query.setMaxResults(membersCountForOnePage);//число отображаемых элементов
            query.setCacheMode(CacheMode.IGNORE); // данные yне кешируются
            listMembers = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массив
            transaction.commit();
            session.close();
            logger.debug("Успешно выполнен запрос для получения списка участников МЭДО");
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При открытии сессии для поиска участника МЭДО в БД по наимаенованию организации произошла программная ошибка");
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
        return listMembers;
    }

    /**
     * Возвращает число строк при поиске по GUID и email организации
     */
    long getCountListSearchByEmailAndGuidOrg(String emailOrg, String guidOrg) {
        long count = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "select count(idMembers) from Members as m where upper(m.addr) like :email and lower(m.guid) like :guid";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            query.setParameter("email", "%" + emailOrg + "%");
            query.setParameter("guid", "%" + guidOrg + "%");
            count = (long) query.uniqueResult();
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При выполнении запроса к БД для вычисления числа записей при поиске орагнизации по её наименованию и GUID возникла программная ошибка");
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
     * Выполняет поиск по названию и GUID организации
     */
    List<Members> getListSearchByEmailAndGuid(String emailOrg, String guidOrg, int skip, int membersCountForOnePage) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "from Members as m where upper(m.addr) like :email and lower(m.guid) like :guid order by idMembers asc";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql, Members.class);//создаю массив объектов с клссом Users и созданным запросом
            query.setParameter("email", "%" + emailOrg + "%");
            query.setParameter("guid", "%" + guidOrg + "%");
            query.setFirstResult(skip);//число пропущенных элементов
            query.setMaxResults(membersCountForOnePage);//число отображаемых элементов
            query.setCacheMode(CacheMode.IGNORE); // данные yне кешируются
            listMembers = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массив
            transaction.commit();
            session.close();
            logger.debug("Успешно выполнен запрос для получения списка участников МЭДО");
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При открытии сессии для поиска участника МЭДО в БД по наимаенованию организации произошла программная ошибка");
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
        return listMembers;
    }

    /**
     * Возвращает число строк при поиске по GUID и email организации
     */
    long getCountListSearchByNameAndEmailAndGuidOrg(String nameOrg, String emailOrg, String guidOrg) {
        long count = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "select count(idMembers) from Members as m where lower(m.nameOrg) like :org and upper(m.addr) like :email and lower(m.guid) like :guid";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            query.setParameter("org", "%" + nameOrg + "%");
            query.setParameter("email", "%" + emailOrg + "%");
            query.setParameter("guid", "%" + guidOrg + "%");
            count = (long) query.uniqueResult();
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При выполнении запроса к БД для вычисления числа записей при поиске орагнизации по её наименованию, email и GUID возникла программная ошибка");
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
     * Выполняет поиск по названию и GUID организации
     */
    List<Members> getListSearchByNameAndEmailAndGuid(String nameOrg, String emailOrg, String guidOrg, int skip, int membersCountForOnePage) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "from Members as m where lower(m.nameOrg) like :org and upper(m.addr) like :email and lower(m.guid) like :guid order by idMembers asc";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql, Members.class);//создаю массив объектов с клссом Users и созданным запросом
            query.setParameter("org", "%" + nameOrg + "%");
            query.setParameter("email", "%" + emailOrg + "%");
            query.setParameter("guid", "%" + guidOrg + "%");
            query.setFirstResult(skip);//число пропущенных элементов
            query.setMaxResults(membersCountForOnePage);//число отображаемых элементов
            query.setCacheMode(CacheMode.IGNORE); // данные yне кешируются
            listMembers = query.list();//т.к. объект query уничтожается после выполнения транзакции, присваиваем его массив
            transaction.commit();
            session.close();
            logger.debug("Успешно выполнен запрос для получения списка участников МЭДО");
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При открытии сессии для поиска участника МЭДО в БД по наимаенованию, email и GUID организации произошла программная ошибка");
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
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            Members members = new Members();
            members.setNameOrg(nameOrg);
            members.setAddr(email);
            members.setGuid(guid);
            session.persist(members);
            transaction.commit();
            session.close();
            logger.debug("Успешно выполнен запрос для добавления нового участника МЭДО");
            return true;
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При добавлении нового участника МЭДО произошла программная ошибка");
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
            return false;
        }
    }

    /**
     * Обновление участника МЭДО
     */
    int updateMember(Short idMember, String nameOrg, String email, String guid) {
        int resultUpdate = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
//            String hql = "update Members set nameOrg=:n, addr=:a, guid=:g where idMembers=" + idMember;//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            String hql = "update Members set nameOrg=:name, addr=:addr, guid=:guid where idMembers=:idMember";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setParameter("idMember", idMember);
            query.setParameter("name", nameOrg);
            query.setParameter("addr", email);
            query.setParameter("guid", guid);
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
    int deleteMember(Short idMember) {
        int resultDelete = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "delete Members where idMembers=:idMember";//sql запрос, наименование таблиц и полей соответствует наименованию объектов в классе Users
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setParameter("idMember", idMember);
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            resultDelete = query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При открытии сессии для выполнения запроса для удаления участника МЭДО возникла программная ошибка");
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
            logBuilder.setLength(0);
            logBuilder.append("При выполнения запроса к БД для проверки наличия добавляемого пользователем участника МЭДО в системе возникла программная ошибка");
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
     * проверка сущестование других записей в БД с такими же данными при
     * обновлении текущей записи
     */
    long getCountByEmailOrGuidAndNotEqualsId(Short idMember, String email, String guid) {
        long count = 0;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();//запускаю транзакцию
            String hql = "select count(idMembers) from Members where (not(idMembers=:idMember)) and (addr=:addr or guid=:guid)";
            Query query = session.createQuery(hql);//создаю массив объектов с клссом Users и созданным запросом
            query.setParameter("addr", email);
            query.setParameter("guid", guid);
            query.setParameter("idMember", idMember);
            query.setCacheMode(CacheMode.IGNORE); // не добавляются и не читаются с кэша
            count = (long) query.uniqueResult();
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            logBuilder.setLength(0);
            logBuilder.append("При проверки наличия участника МЭДО для внесения изменений под пользователем участника МЭДО в системе возникла программная ошибка");
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
