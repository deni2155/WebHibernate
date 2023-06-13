package com.kindcat.archivemedo.db.services;

import com.kindcat.archivemedo.db.dao.UsersDao;
import com.kindcat.archivemedo.db.models.Users;

public class UsersService {
    private final UsersDao userDao=new UsersDao();

    public UsersService(){}

    public Users findId(int id){
        return userDao.findById(id);
    }

    public int findLogin(String login){
        return userDao.findByLogin(login);
    }
}
