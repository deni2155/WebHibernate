package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.Documents;
import com.kindcat.archivemedo.db.models.Members;
import com.kindcat.archivemedo.db.models.Notifs;
import com.kindcat.archivemedo.db.models.SchemaXml;
import com.kindcat.archivemedo.db.models.TypeNotif;
import com.kindcat.archivemedo.db.models.TypePkg;
import com.kindcat.archivemedo.db.models.Users;
import java.util.List;

public interface ImplDao {

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
    /**
     * @param id - процедура получает идентификатор УЗ пользователя
     * @return idUser возвращает информацию о пользователю
     */
    public Users findUserById(int id);

    /**
     * @param login - процедура получает логин пользователя
     * @return login возвращает информацию о пользователю
     */
    public int findUserInLoginByLogin(String login);

    /**
     *
     *
     *
     *
     *
     *
     * Участники МЭДО
     *
     *
     *
     *
     *
     */
    /**
     * @return общее число записей из таблицы с участниками МЭДО
     */
    public long getAllCountMembers();

    /**
     * @param skip число пропущенных записей для вывода постранично
     * @param membersCountForOnePage число записей на одной странице
     * @return список участников МЭДО из БД
     */
    public List<Members> getAllListMembers(int skip, int membersCountForOnePage);

    /**
     * Поиск участника по организации
     *
     * @param nameOrg наименование организации
     * @return число записей при поиске по названию организации
     */
    public long getCountListSearchByNameOrgMembers(String nameOrg);

    /**
     * @param nameOrg название организации
     * @param skip число пропущенных записей
     * @param membersCountForOnePage число записей на одной странице
     * @return список найденных организаций по наименованию оргнаизации
     */
    public List<Members> getListSearchByNameOrgMembers(String nameOrg, int skip, int membersCountForOnePage);

    /**
     * @param email email участника для поиска в БД
     * @return число найденных по email участника записей
     */
    public long getCountListSearchByEmailOrgMembers(String email);

    /**
     * @param email email участника для поиска
     * @param skip число пропущенных записей
     * @param membersCountForOnePage число записей на одной странице
     * @return список найденных записей по email организации
     */
    public List<Members> getListSearchByEmailOrgMembers(String email, int skip, int membersCountForOnePage);

    /**
     * Получение числа записей в БД по GUID организации
     *
     * @param guidOrg guid организации
     * @return число записей, найденных по email
     */
    public long getCountListSearchByGuidOrgMembers(String guidOrg);

    /**
     * Поиск по GUID
     *
     * @param guidOrg GUID организации
     * @param skip число пропущенных записей
     * @param membersCountForOnePage число записей на одной странице
     * @return список найденных записей, найденных по GUID
     */
    public List<Members> getListSearchByGuidOrgMembers(String guidOrg, int skip, int membersCountForOnePage);

    /**
     * @param guidOrg GUID организации
     * @param emailOrg email организации
     * @return число строк при поиске участника по названию организации и email
     * орагнизации
     */
    public long getCountListSearchByNameAndEmailOrgMembers(String guidOrg, String emailOrg);

    /**
     * Список участников МЭДО при поиске по названию организации и email
     *
     * @param nameOrg название организации
     * @param emailOrg email организации
     * @param skip число пропущенных записей
     * @param membersCountForOnePage число записей на одной странице
     * @return список участников МЭДО найденных по названию и email
     */
    public List<Members> getListSearchByNameAndEmailOrgMembers(String nameOrg, String emailOrg, int skip, int membersCountForOnePage);

    /**
     * Получение числа строк при поиске участников МЭДО по названию организации
     * и GUID
     *
     * @param nameOrg название организации
     * @param guidOrg GUID организации
     * @return число строк при поиске участников МЭДО по названию организации и
     * GUID
     */
    public long getCountListSearchByNameAndGuidOrgMembers(String nameOrg, String guidOrg);

    /**
     * Получение списка участников МЭДО при поиске по наименованию и GUID
     *
     * @param nameOrg название организации
     * @param guidOrg GUID организации
     * @param skip число пропущенных записей
     * @param membersCountForOnePage общее число записей в БД
     * @return список участников МЭДО найденных по названию и GUID
     */
    public List<Members> getListSearchByNameAndGuidOrgMembers(String nameOrg, String guidOrg, int skip, int membersCountForOnePage);

    /**
     * Возвращает число записей в БД при поиске по email и GUID организации
     *
     * @param emailOrg email участника
     * @param guidOrg GUID участника
     * @return число записей в таблице при поиске по email и GUID организации
     */
    public long getCountListSearchByEmailAndGuidOrgMembers(String emailOrg, String guidOrg);

    /**
     * Возвращает сприсок участников МЭДО при поиске по email и GUID
     *
     * @param emailOrg email участника
     * @param guidOrg guid участника
     * @param skip число пропущенных записей
     * @param membersCountForOnePage общее число записей в БД
     * @return список участников МЭДО при поиске по email и guid организации
     */
    public List<Members> getListSearchByEmailAndGuidOrgMembers(String emailOrg, String guidOrg, int skip, int membersCountForOnePage);

    /**
     * Возвращает список участников МЭДО при поиске по названию, email и GUID
     *
     * @param nameOrg название организации
     * @param emailOrg email организации
     * @param guidOrg guid орпганизации
     * @return список участников МЭДО при поиске по названию, email и GUID
     */
    public long getCountListSearchByNameAndEmailAndGuidOrgMembers(String nameOrg, String emailOrg, String guidOrg);

    /**
     * Возвращает список участников МЭДО при поиске по названию, email и GUID
     *
     * @param nameOrg название организаци
     * @param emailOrg email организации
     * @param guidOrg GUID организации
     * @param skip число пропущенных записей
     * @param membersCountForOnePage общее число записей в БД
     * @return список участников МЭДО при поиске по названию, email и GUID
     */
    public List<Members> getListSearchByNameAndEmailAndGuidOrgMembers(String nameOrg, String emailOrg, String guidOrg, int skip, int membersCountForOnePage);

    /**
     * Поиск участника МЭДО по идентифкатору
     *
     * @param id идентификатор участника
     * @return информация об участнике
     */
    public Members findMemberById(int id);

    /**
     * Добавление участника МЭДО
     *
     * @param nameOrg - наименование организации
     * @param email
     * @param guid - идентификатор участника
     * @return успешно добавлен участник или нет
     */
    public boolean addNewMember(String nameOrg, String email, String guid);

    /**
     * Изменение существующего участника МЭДО
     *
     * @param idMember - идентификатор записи в БД
     * @param nameOrg - наименование организации
     * @param email - email организации
     * @param guid организации
     * @return число изменённых записей
     */
    public int updateOldMember(int idMember, String nameOrg, String email, String guid);

    /**
     * Проверка существования записи в БД при добавлении нового участника МЭДО
     *
     * @param email-адреса участника
     * @param guid-идентификатор участника
     * @return количество записей в БД
     */
    public long getCountMembersByEmailOrGuid(String email, String guid);

    /**
     * проверка сущестование других записей в БД с такими же данными при
     * обновлении текущей записи
     *
     * @param idMember
     * @param email
     * @param guid
     * @return количество записей в БД
     */
    public long getCountMembersByEmailOrGuidAndNotEqualsId(int idMember, String email, String guid);

    /**
     * Удаление участника МЭДО
     *
     * @param idMember - идентификатор участника МЭДО в БД
     * @return количество удаленных записей
     */
    public int deleteOldMember(int idMember);
//
//    public long countEmailOrgMembers(String email);
//
//    public long countGuidMembers(String guid);

    /**
     * @param idTypePkg идентификатор типа пакета, выбранный пользователем
     * @return наименование типа пакета
     */
//    public TypePkg findByIdTypePkg(Short idTypePkg);
    /**
     *
     *
     *
     * Список документов
     *
     *
     *
     */
    /**
     * Возвращает список документов
     *
     * @param skip число пропущенных записей
     * @param docsCountForOnePage общее число записей на одной странице
     * @param idTypePkg тип пакета (входящий или исходящий)
     * @return список документов
     */
    public List<Documents> getAllListDocsByTypePkg(Short idTypePkg, int skip, int docsCountForOnePage);

    /**
     * Возвращает общее число записей из таблицы с документами в зависимости от
     * типа документов
     *
     * @param idTypePkg идентификатр типа пакета (входящий или исходящий)
     * @return общее число документов в пакете
     */
    public long getAllCountDocs(Short idTypePkg);

    /**
     *
     *
     *
     *
     * Список уведомлений
     *
     *
     *
     *
     */
    /**
     * Возвращает общее число записей в таблице с уведомлениями
     *
     * @param idTypePkg тип пакета (входящий или исходящий)
     * @return общее число записей
     */
    public long getAllCountNotif(Short idTypePkg);

    /**
     * Возвращает список уведомлений в зависимости от типа пакета (входящий или
     * исходящий)
     * @param idTypePkg идентификатор типа пакета (входящий или исходящий)
     * @param skip число пропущенных записей
     * @param notifCountForOnePage число записей на одной странице
     * @return 
     */
    public List<Notifs> getAllListNotifsByTypePkg(Short idTypePkg,int skip,int notifCountForOnePage);

    /**
     *
     *
     *
     * Справочники
     *
     *
     *
     */
    /**
     * @return список типов пакетов (входящий или исходящий)
     */
    public List<TypePkg> getAllListTypePkg();

    /**
     * @return список xml-схем
     */
    public List<SchemaXml> getAllListSchemaXml();

    /**
     * @return список типов уведомлений
     */
    public List<TypeNotif> getAllListTypeNotif();
}
