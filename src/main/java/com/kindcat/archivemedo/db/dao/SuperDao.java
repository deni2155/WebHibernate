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
}
