package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.Members;
import com.kindcat.archivemedo.db.models.SchemaXml;
import com.kindcat.archivemedo.db.models.TypePkg;
import com.kindcat.archivemedo.db.models.Users;
import java.util.List;

public interface ImplDao {

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
    public Users findUserById(int id);

    public int findUserInLoginByLogin(String login);

    /*
*
*
*
*
*
*
*Участники МЭДО
*
*
*
*
*
     */
    public long getAllCountMembers();

    public List<Members> getAllListMembers(int skip, int countMembers);

    public Members findMemberById(int id);

    public boolean addNewMember(String nameOrg, String email, String guid);

    public int updateOldMember(int idMember, String nameOrg, String email, String guid);

    public long getCountMembersByEmailOrGuid(String email, String guid);

    public long getCountMembersByEmailOrGuidAndNotEqualsId(int idMember, String email, String guid);

    public int deleteOldMember(int idMember);
//
//    public long countEmailOrgMembers(String email);
//
//    public long countGuidMembers(String guid);

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
    public List<TypePkg> getAllListTypePkg();
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
    public List<SchemaXml> getAllListSchemaXml();
}
