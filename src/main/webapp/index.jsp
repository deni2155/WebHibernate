<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
    	<script src="js/bootstrap.min.js"></script>
    	<link href="css/bootstrap.min.css" rel="stylesheet"/>
    	<link href="css/custom.css" rel="stylesheet"/>
        <title>Авторизация архи МЭДО</title>
    </head>
    <body class="container">
        <div class="row">
            <div class="col text-center d-flex align-items-center justify-content-center vh-100">
                <form class="custom-row-signin-form p-4 fw-bold" action="signin" method="post">
                    <label>МЭДО</label>
                    <div class="form-group">
                        <div class="m-2">
                            <input type="text" class="form-control" name="username" placeholder="логин">
                        </div>
                        <div class="m-2">
                            <input type="password" class="form-control" name="pwd" placeholder="пароль">
                        </div>
                        <div class="m-2">
                            <button type="submit" class="btn btn-custom-signin">Войти</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
