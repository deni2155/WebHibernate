package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.Documents;
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

    private final UsersDao userDao;//Ссылка на класс для работы с моделю данных таблицы user
    private final MembersDao membersDao;//Ссылка на класс для работы с моделью данных таблицы с участниками МЭДО
    private TypePkgDao typePkgDao;//ссылка на класс для работы с типом пакетов (входящий или исходящий)
    private final SchemaXmlDao schemaXmlDao;//ссылка на класс для работы со списком xml-схем
    private final DocumentsDao docDao;//ссылка на класс для работы со списком документов

    /**
     * Конструктор класса
     */
    public SuperDao() {
        userDao = new UsersDao();
        membersDao = new MembersDao();
        typePkgDao = new TypePkgDao();
        typePkgDao = new TypePkgDao();
        schemaXmlDao = new SchemaXmlDao();
        docDao = new DocumentsDao();
    }

    /**
     *
     *
     *
     *
     * Пользователи
     *
     *
     *
     */
    //информация о пользователе по ID
    @Override
    public Users findUserById(int id) {
        return userDao.findById(id);
    }

    //информация о пользователе по login
    @Override
    public int findUserInLoginByLogin(String login) {
        return userDao.findInLoginById(login);
    }

    /**
     *
     *
     *
     *
     * Участники МЭДО
     *
     *
     *
     */
    //общее число записей с участниками МЭДО
    @Override
    public long getAllCountMembers() {
        return membersDao.getAllCountMembers();
    }

    //список участников МЭДО из БД
    @Override
    public List<Members> getAllListMembers(int skip, int countMembers) {
        return membersDao.getAllList(skip, countMembers);
    }

    //Поиск числа записей в БД по наименованию участника
    @Override
    public long getCountListSearchByNameOrgMembers(String nameOrg) {
        return membersDao.getCountListSearchByNameOrg(nameOrg);
    }

    //Поиск записей по наименованию участника
    @Override
    public List<Members> getListSearchByNameOrgMembers(String nameOrg, int skip, int countMembers) {
        return membersDao.getListSearchByNameOrg(nameOrg, skip, countMembers);
    }

    //поиск числа записей по email участника
    @Override
    public long getCountListSearchByEmailOrgMembers(String email) {
        return membersDao.getCountListSearchByEmailOrg(email);
    }

    //список найденных записей по email организации
    @Override
    public List<Members> getListSearchByEmailOrgMembers(String email, int skip, int countMembers) {
        return membersDao.getListSearchByEmailOrg(email, skip, countMembers);
    }

    //Получение числа записей в БД по GUID организации
    @Override
    public long getCountListSearchByGuidOrgMembers(String guidOrg) {
        return membersDao.getCountListSearchByGuidOrg(guidOrg);
    }

    //Получение числа записей по GUID
    @Override
    public List<Members> getListSearchByGuidOrgMembers(String guidOrg, int skip, int countMembers) {
        return membersDao.getListSearchByGuidOrg(guidOrg, skip, countMembers);
    }

    //Получение числа записей в БД по названию и email организации
    @Override
    public long getCountListSearchByNameAndEmailOrgMembers(String nameOrg, String emailOrg) {
        return membersDao.getCountListSearchByNameAndEmailOrg(nameOrg, emailOrg);
    }

    //Получение списка участников МЭДО при поиске по наименованию и email
    @Override
    public List<Members> getListSearchByNameAndEmailOrgMembers(String nameOrg, String emailOrg, int skip, int countMembers) {
        return membersDao.getListSearchByNameAndEmailOrg(nameOrg, emailOrg, skip, countMembers);
    }

    //Получение числа записей в БД по названию и email организации
    @Override
    public long getCountListSearchByNameAndGuidOrgMembers(String nameOrg, String guidOrg) {
        return membersDao.getCountListSearchByNameAndGuidOrg(nameOrg, guidOrg);
    }

    //Получение списка участников МЭДО при поиске по наименованию и GUID
    @Override
    public List<Members> getListSearchByNameAndGuidOrgMembers(String nameOrg, String guidOrg, int skip, int countMembers) {
        return membersDao.getListSearchByNameAndGuid(nameOrg, guidOrg, skip, countMembers);
    }

    //Получение числа записей в БД по названию и email организации
    @Override
    public long getCountListSearchByEmailAndGuidOrgMembers(String emailOrg, String guidOrg) {
        return membersDao.getCountListSearchByEmailAndGuidOrg(emailOrg, guidOrg);
    }

    //Получение списка участников МЭДО при поиске по наименованию и GUID
    @Override
    public List<Members> getListSearchByEmailAndGuidOrgMembers(String emailOrg, String guidOrg, int skip, int countMembers) {
        return membersDao.getListSearchByEmailAndGuid(emailOrg, guidOrg, skip, countMembers);
    }

    //Получение числа записей в БД по названию, email и guid организации
    @Override
    public long getCountListSearchByNameAndEmailAndGuidOrgMembers(String nameOrg, String emailOrg, String guidOrg) {
        return membersDao.getCountListSearchByNameAndEmailAndGuidOrg(nameOrg, emailOrg, guidOrg);
    }

    //Получение списка участников МЭДО при поиске по наименованию и GUID
    @Override
    public List<Members> getListSearchByNameAndEmailAndGuidOrgMembers(String nameOrg,String emailOrg, String guidOrg, int skip, int countMembers) {
        return membersDao.getListSearchByNameAndEmailAndGuid(nameOrg,emailOrg, guidOrg, skip, countMembers);
    }

    //Поиск участника МЭДО по идентифкатору
    @Override
    public Members findMemberById(int id) {
        return membersDao.findById(id);
    }

    //Добавление участника МЭДО
    @Override
    public boolean addNewMember(String nameOrg, String email, String guid) {
        return membersDao.addMember(nameOrg, email, guid);
    }

    //Изменение существующего участника МЭДО
    @Override
    public int updateOldMember(int idMember, String nameOrg, String email, String guid) {
        return membersDao.updateMember(idMember, nameOrg, email, guid);
    }

    //Удаление участника МЭДО
    @Override
    public int deleteOldMember(int idMember) {
        return membersDao.deleteMember(idMember);
    }

    //Проверка существования записи в БД при добавлении нового участника МЭДО
    @Override
    public long getCountMembersByEmailOrGuid(String email, String guid) {
        return membersDao.getCountByEmailOrGuid(email, guid);
    }

    //проверка сущестование других записей в БД с такими же данными при обновлении текущей записи
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
    //возвращает список типов пакетов (входящий или исходящий)
    @Override
    public List<TypePkg> getAllListTypePkg() {
        return typePkgDao.getAllList();
    }

    /**
     * @param idTypePkg идентификатор типа пакета, выбранный пользователем
     * @return наименование типа пакета
     */
//    @Override
//    public TypePkg findByIdTypePkg(Short idTypePkg) {
//        return typePkgDao.findById(idTypePkg);
//    }
    /**
     *
     *
     *
     * Список схем xml-файлов
     *
     *
     *
     */
    //возвращает список xml-схем
    @Override
    public List<SchemaXml> getAllListSchemaXml() {
        return schemaXmlDao.getAllList();
    }

    /**
     *
     *
     *
     * Список документов
     *
     *
     *
     */
    //возвращает список документов
    @Override
    public List<Documents> getAllListByTypePkg(Short idTypePkg) {
        return docDao.getAllListByTypePkg(idTypePkg);
    }
}
