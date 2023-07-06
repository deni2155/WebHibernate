<%@page import="com.kindcat.archivemedo.logger.LoggerForJsp"%>
<%@page import="com.kindcat.archivemedo.logger.LoggerForJspImpl"%>
<%@page import="com.kindcat.archivemedo.signin.sessions.CurrentSessionImpl"%>
<%@page import="com.kindcat.archivemedo.signin.sessions.CurrentSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- 
Страница авторизации
-->
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
            if (request.getSession(false) != null) {
                CurrentSessionImpl currentSession = new CurrentSession();
                currentSession.setSession(request.getSession(false));//получаю из запроса информацию о текущей сессии
                //если текущая сессия создана
                if (currentSession.isExistsSession()) {
                    LoggerForJspImpl logger = new LoggerForJsp();
                    logger.setLoggerSignInJsp(currentSession.getLoginUser());//записываю лог авторизации
                    getServletContext().getRequestDispatcher("/pages/archive.jsp").forward(request, response);
                }
            }
        %>
        <div class="row text-center d-flex align-items-center justify-content-center vh-100">
            <div class="col-3">
                <form class="custom-row-signin-form pb-2 pt-4 pe-4 ps-4 fw-bold" action="mainClass" method="post">
                    <label>Архив МЭДО</label>
                    <div class="text-danger h6">${message}</div>
                    <div class="form-group">
                        <div class="m-2">
                            <input type="text" class="form-control" name="login" placeholder="логин"/>
                        </div>
                        <div class="m-2">
                            <input type="password" class="form-control" name="password" placeholder="пароль"/>
                        </div>
                    </div>
                    <div class="pb-4">
                        <input class="btn btn-custom" id="sending-form-singin" type="submit" value="Войти""/>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
