package com.kindcat.archivemedo.input.sessions;

import javax.servlet.http.HttpSession;

/**
* Интерфейс для работы класса с сессией пользователя
*/
public interface UserSeesionImpl {
    public HttpSession getSession();
    public void setSession(HttpSession session);
    public boolean existsSession();
}
