package com.kindcat.archivemedo.db.dao;

import com.kindcat.archivemedo.db.models.Users;

public interface ImplDao {
    public Users findUserById(int id);
    public int findUserInLoginByLogin(String login);
}
