package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.Members;
import com.kindcat.archivemedo.db.models.SchemaXml;
import com.kindcat.archivemedo.db.models.TypePkg;
import com.kindcat.archivemedo.db.models.Users;
import java.util.List;

/**
 * Главный класс пакета dao
 *
 * @version 1.0.1.33s
 */
public class SuperDao implements ImplDao {

    /**
     * Ссылка на класс для работы с моделю данных таблицы user
     */
    private final UsersDao userDao;
    /**
     * Ссылка на класс для работы с моделью данных таблицы с участниками МЭДО
     */
    private final MembersDao membersDao;
    /**
     * ссылка на класс для работы с типом пакетов (входящий или исходящий)
     */
    private TypePkgDao typePkgDao;
    /**
     * ссылка на класс для работы со списком xml-схем
     */
    private SchemaXmlDao schemaXmlDao;

    /**
     * Конструктор класса
     */
    public SuperDao() {
        userDao = new UsersDao();
        membersDao = new MembersDao();
        typePkgDao = new TypePkgDao();
        typePkgDao = new TypePkgDao();
        schemaXmlDao = new SchemaXmlDao();
    }

    /*
*
*
*
*
*Пользователи
*
*
*
     */
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

    /*
*
*
*
*
*Участники МЭДО
*
*
*
     */
    /**
     * @return общее число записей из таблицы с участниками МЭДО
     */
    @Override
    public long getAllCountMembers() {
        return membersDao.getAllCountMembers();
    }

    /**
     * @param skip - число пропущенных записей для вывода постранично
     * @param countMembers - число записей на одной странице
     * @return список участников МЭДО из БД
     */
    @Override
    public List<Members> getAllListMembers(int skip, int countMembers) {
        return membersDao.getAllList(skip, countMembers);
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
     * Удаление участника МЭДО
     *
     * @param idMember - идентификатор участника МЭДО в БД
     * @return количество удаленных записей
     */
    @Override
    public int deleteOldMember(int idMember) {
        return membersDao.deleteMember(idMember);
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
     *
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
    /**
     *
     *
     *
     * Работа с типом пакетов (входящий или исходящий)
     *
     *
     *
     */
    /**
     * @return список типов пакетов (входящий или исходящий)
     */
    @Override
    public List<TypePkg> getAllListTypePkg() {
        return typePkgDao.getAllList();
    }

    /**
     *
     *
     *
     * Список схем xml-файлов
     *
     *
     *
     */
    /**
     * @return список xml-схем
     */
    @Override
    public List<SchemaXml> getAllListSchemaXml() {
        return schemaXmlDao.getAllList();
    }
}
