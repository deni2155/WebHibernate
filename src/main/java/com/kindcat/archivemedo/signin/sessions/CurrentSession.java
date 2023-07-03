package com.kindcat.archivemedo.signin.sessions;

import javax.servlet.http.HttpSession;

/**
 * Класс для работы с сессиями
 */
public class CurrentSession implements CurrentSessionImpl {

    //сессия пользователя
    private HttpSession session;

    /**
     * @param session - получаю объект сессии
     */
    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    /**
     * @return session - объект сессии
     */
    @Override
    public HttpSession getSession() {
        return session;
    }

    /**
     * @return наличие или отсутствие сессии
     */
    @Override
    public boolean isExistsSession() {
        //сессия существует
        if (session != null) {
            //проверяю наличие атрибутов в сессии
            return session.getAttribute("idUser") != null;
            //сессия не существует
        } else {
            return false;
        }
    }

    /**
     * @param id - идентификатор пользователя
     */
    @Override
    public void setIdUser(int id) {
        session.setAttribute("idUser", id);
    }

    /**
     * @return идентификатор пользователя
     */
    @Override
    public int getIdUser() {
        return (int) session.getAttribute("idUser");
    }

    /**
     * @param fName - полное имя пользоваетля
     */
    @Override
    public void setFNameUser(String fName) {
        session.setAttribute("fName", fName);
    }

    /**
     * @return полное имя пользователя
     */
    @Override
    public String getFNameUser() {
        return (String) session.getAttribute("fName");
    }

    /**
     * @param login
     */
    @Override
    public void setLoginUser(String login) {
        session.setAttribute("login", login);
    }

    /**
     * @return логин пользователя
     */
    @Override
    public String getLoginUser() {
        return (String) session.getAttribute("login");
    }
}
