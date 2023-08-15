package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.Members;
import com.kindcat.archivemedo.db.models.Users;
import java.util.List;

public interface ImplDao {

    public Users findUserById(int id);

    public int findUserInLoginByLogin(String login);

    public List<Members> getListMembers();

    public Members findMemberById(int id);

    public boolean addNewMember(String nameOrg, String email, String guid);
}
