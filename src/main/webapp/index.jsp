<%@page import="com.kindcat.archivemedo.input.sessions.UserSeesionImpl"%>
<%@page import="com.kindcat.archivemedo.input.sessions.UserSession"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
    	<script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.js"></script>
        <script src="js/jscript.js"></script>
    	<link href="css/bootstrap.min.css" rel="stylesheet"/>
    	<link href="css/custom.css" rel="stylesheet"/>
        <title>Авторизация архив МЭДО</title>
    </head>
    <body class="container">
        <%
           //получаю сессию
           UserSeesionImpl userSession=new UserSession();
           userSession.setSession(request.getSession(false));
           //если сессия существует и в сессии сохранён ID пользователя
           if(userSession.existsSession()){
                getServletContext().getRequestDispatcher("/pages/archive.jsp").forward(request, response);
            }
        %>
        <div class="row text-center d-flex align-items-center justify-content-center vh-100">
            <div class="col-3">
                <form class="custom-row-signin-form pb-2 pt-4 pe-4 ps-4 fw-bold" action="mainClass" method="post">
                    <label>Архив МЭДО</label>
                    <div class="text-danger h6">${message}</div>
                    <div class="form-group">
                        <div class="m-2">
                            <input type="text" class="form-control" name="username" placeholder="логин"/>
                        </div>
                        <div class="m-2">
                            <input type="password" class="form-control" name="password" placeholder="пароль"/>
                        </div>
                    </div>
                    <div class="pb-4">
                        <input class="btn btn-custom-signin" id="sending-form-singin" type="submit" value="Войти""/>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
