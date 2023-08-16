<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link href="css/bootstrap.min.css" rel="stylesheet"/>
        <link href="css/custom.css" rel="stylesheet"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.js"></script>
        <script src="js/jscript.js"></script>
        <title>Результат загрузки участников МЭДО</title>
    </head>
    <body class="container-fluid">
        <jsp:include page="../templates/header.jsp"/>
        <!--
        
        Форма для загрузки файла со списком участников МЭДО
        
        -->
        <div class="row pt-2">
            <div class="col-3 custom-background-content text-center mx-auto w-25 ps-4 pe-4">
                <div class="h3 pt-3">
                    Информация о загрузке файла со списком участников МЭДО
                </div>
                <div class="pt-2">
                    <p class="fst-italic pt-2">${message}</p>
                </div>
                <div class="pt-2">
                    <a href="linkDownloadMembersesServlet" class="form-control btn btn-custom mt-3 mb-3">Загрузка файла</a>
                </div>
                <div class="pt-2">
                    <div class="pb-4">
                        <a href="linkHomeServlet" class="btn btn-secondary col-5">На главную</a>
                        <a href="linkListMembersServlet" class="btn btn-secondary col-5">Участники МЭДО</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>