<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- 
Страница авторизации
-->
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/signin/ajax-signin.js"></script>
        <link href="css/bootstrap.min.css" rel="stylesheet"/>
        <link href="css/custom.css" rel="stylesheet"/>
        <title>Авторизация архив МЭДО</title>
    </head>
    <body class="container">
        <div class="row text-center d-flex align-items-center justify-content-center vh-100">
            <div class="col-3">
                <form id="form-sign-in" class="custom-row-signin-form pb-2 pt-4 pe-4 ps-4 fw-bold">
                    <label>Архив МЭДО</label>
                    <div id="message-for-signin" class="text-danger h6"></div>
                    <div class="form-group">
                        <div class="m-2 input-group">
                            <div id="div-login" class="rounded-start">
                                <img src="icon/login.png" class="rounded-start p-1"/>
                            </div>
                            <input type="text" class="form-control" name="login" placeholder="логин"/>
                        </div>
                        <div class="m-2 input-group">
                            <div id="div-password" class="rounded-start">
                                <img src="icon/password.png" class="rounded-start p-1"/>
                            </div>
                            <input type="password" class="form-control" name="password" placeholder="пароль"/>
                        </div>
                    </div>
                    <div class="pb-4">
                        <input type="submit" class="btn btn-custom" id="sending-form-singin" value="Войти""/>
                    </div>
                </form>
                <!--form class="custom-row-signin-form pb-2 pt-4 pe-4 ps-4 fw-bold" method="post" action="">
                    <label>Архив МЭДО</label>
                    <div class="text-danger h6">{message}</div>
                    <div class="form-group">
                        <div class="m-2 input-group">
                            <div id="div-login" class="rounded-start">
                                <img src="icon/login.png" class="rounded-start p-1"/>
                            </div>
                            <input type="text" class="form-control" name="login" placeholder="логин"/>
                        </div>
                        <div class="m-2 input-group">
                            <div id="div-password" class="rounded-start">
                                <img src="icon/password.png" class="rounded-start p-1"/>
                            </div>
                            <input type="password" class="form-control" name="password" placeholder="пароль"/>
                        </div>
                    </div>
                    <div class="pb-4">
                        <input class="btn btn-custom" id="sending-form-singin" type="submit" value="Войти""/>
                    </div>
                </form-->
            </div>
        </div>
    </body>
</html>