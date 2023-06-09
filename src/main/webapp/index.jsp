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
        <title>Авторизация архи МЭДО</title>
    </head>
    <body class="container">
        <div class="row text-center d-flex align-items-center justify-content-center vh-100">
            <div class="col-3">
                <!--form class="custom-row-signin-form pb-2 pt-4 pe-4 ps-4 fw-bold">
                    <label>МЭДО</label>
                    <div id="info-ajax-msg-sign-in" class="text-danger h6"></div>
                    <div class="form-group">
                        <div class="m-2">
                            <input type="text" class="form-control" placeholder="логин">
                        </div>
                        <div class="m-2">
                            <input type="password" class="form-control" placeholder="пароль">
                        </div>
                    </div>
                </form>
                <div class="custom-row-signin-form pb-4">
                    <button class="btn btn-custom-signin" id="sending-form-singin">Войти</button>
                </div-->
                <form class="custom-row-signin-form pb-2 pt-4 pe-4 ps-4 fw-bold" action="signIn" method="post">
                    <label>МЭДО</label>
                    <div class="text-danger h6" name="info-ajax-msg-sign-in"></div>
                    <div class="form-group">
                        <div class="m-2">
                            <input type="text" class="form-control" name="username" id="username" placeholder="логин">
                        </div>
                        <div class="m-2">
                            <input type="password" class="form-control" name="pwd" id="password" placeholder="пароль">
                        </div>
                    </div>
                    <div class="custom-row-signin-form pb-4">
                        <input class="btn btn-custom-signin" id="sending-form-singin" type="submit" value="Войти">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
