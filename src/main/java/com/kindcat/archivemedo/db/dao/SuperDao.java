package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.Members;
import com.kindcat.archivemedo.db.models.Users;
import java.util.List;

/**
 * Главный класс пакета dao
 */
public class SuperDao implements ImplDao {

    /**
     * Ссылка на класс для работы с моделю данных таблицы user
     */
    private final UsersDao userDao = new UsersDao();
    /**
     * Ссылка на класс для работы с моделью данных таблицы с участниками МЭДО
     */
    private final MembersDao membersDao = new MembersDao();

    /**
     * Конструктор класса
     */
    public SuperDao() {
    }

    /**
     * @param id - процедура получает идентификатор УЗ пользователя
     * @return idUser возвращает информацию о пользователю
     */
    @Override
    public Users findUserById(int id) {
        return userDao.findById(id);
    }

    /**
     * @param login - процедура получает логин пользователя
     * @return login возвращает информацию о пользователю
     */
    @Override
    public int findUserInLoginByLogin(String login) {
        return userDao.findInLoginById(login);
    }

    /**
     * @return список участников МЭДО из БД
     */
    @Override
    public List<Members> getListMembers() {
        return membersDao.getMembersFindAll();
    }

    /**
     * Поиск участника МЭДО по идентифкатору
     *
     * @param id идентификатор участника
     * @return - информация об участнике
     */
    @Override
    public Members findMemberById(int id) {
        return membersDao.findById(id);
    }

    /**
     * Добавление участника МЭДО
     *
     * @param nameOrg - наименование организации
     * @param email
     * @param guid - идентификатор участника
     * @return успешно добавлен участник или нет
     */
    @Override
    public boolean addNewMember(String nameOrg, String email, String guid) {
        return membersDao.addMember(nameOrg, email, guid);
    }

    /**
     * Изменение существующего участника МЭДО
     *
     * @param idMember - идентификатор записи в БД
     * @param nameOrg - наименование организации
     * @param email - email организации
     * @param guid организации
     * @return число изменённых записей
     */
    @Override
    public int updateOldMember(int idMember, String nameOrg, String email, String guid) {
        return membersDao.updateMember(idMember, nameOrg, email, guid);
    }

    /**
     * Проверка существования записи в БД при добавлении нового участника МЭДО
     *
     * @param email-адреса участника
     * @param guid-идентификатор участника
     * @return количество записей в БД
     */
    @Override
    public long getCountMembersByEmailOrGuid(String email, String guid) {
        return membersDao.getCountByEmailOrGuid(email, guid);
    }

    /**
     * проверка сущестование других записей в БД с такими же данными при
     * обновлении текущей записи
     * @param idMember
     * @param email
     * @param guid
     * @return количество записей в БД
     */
    @Override
    public long getCountMembersByEmailOrGuidAndNotEqualsId(int idMember, String email, String guid) {
        return membersDao.getCountByEmailOrGuidAndNotEqualsId(idMember, email, guid);
    }

//    /**
//     * Проверка существования записи в БД по email при изменении участника МЭДО
//     *
//     * @param email
//     * @return идентификатор записи в БД
//     */
//    @Override
//    public long countEmailOrgMembers(String email) {
//        return membersDao.countEmailOrg(email);
//    }
//
//    /**
//     * Проверка существования записи в БД по GUID при изменении участника МЭДО
//     *
//     * @param guid
//     * @return идентификатор записи в БД
//     */
//    @Override
//    public long countGuidMembers(String guid) {
//        return membersDao.countGuidOrg(guid);
//    }
}
