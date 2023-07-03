package com.kindcat.archivemedo.signin.sessions;

import javax.servlet.http.HttpSession;

public interface CurrentSessionImpl {

    public void setSession(HttpSession session);

    public HttpSession getSession();

    public boolean isExistsSession();

    public void setIdUser(int id);

    public int getIdUser();

    public void setFNameUser(String fName);

    public String getFNameUser();

    public void setLoginUser(String login);

    public String getLoginUser();
}
