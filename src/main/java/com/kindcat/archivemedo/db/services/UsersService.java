package com.kindcat.archivemedo.db.services;

//import com.kindcat.archivemedo.db.dao.SuperDao;
import com.kindcat.archivemedo.db.dao.ImplDao;
import com.kindcat.archivemedo.db.dao.SuperDao;
import com.kindcat.archivemedo.db.models.Users;

public class UsersService {
    private final ImplDao userDao;

    public UsersService(){
        userDao = new SuperDao();
    }

    public Users findId(int id){
        return userDao.findUserById(id);
    }

    public int findLogin(String login){
        return userDao.findUserByLogin(login);
    }
}
