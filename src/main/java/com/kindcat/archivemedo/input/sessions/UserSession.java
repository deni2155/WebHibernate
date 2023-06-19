package com.kindcat.archivemedo.input.sessions;

import javax.servlet.http.HttpSession;

/**
* Класс для хранения данных о сессии пользователя
*/
public class UserSession implements UserSeesionImpl{
    //сессия пользователя
    private HttpSession session;
    /**
     * @return сессию пользователя
    */
    @Override
    public HttpSession getSession() {
        return session;
    }
    /**
     * @param session записывает сессию пользователя
    */
    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }
    /**
     * @return наличие или отсутствие сессии, а так же параметров сессии
    */
    @Override
    public boolean existsSession(){
        //сессия существует
        if(session!=null){
            //проверяю наличие атрибутов в сессии
            return session.getAttribute("idUser") != null;
        }else{
            return false;
        }
    }
}
