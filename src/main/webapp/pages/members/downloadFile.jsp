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
        <script src="js/jscript-member.js"></script>
        <title>Загрузка участников МЭДО</title>
    </head>
    <body class="container-fluid">
        <jsp:include page="../templates/header.jsp"/>
        <!--
        
        Форма для загрузки файла со списком участников МЭДО
        
        -->
        <div class="row pt-2">
            <div class="col-3 custom-background-content text-center mx-auto w-25 ps-4 pe-4">
                <div class="h3 pt-3">
                    Загрузка файла со списком участников МЭДО
                </div>
                <div class="pt-2">
                    <form action="uploadListMembersFilter" method="post" enctype="multipart/form-data">
                        <!--div class="file-upload-guides"-->
                        <label class="btn btn-primary text-center fw-bolder">
                            <input type="file" id="file-input-guides" name="file" accept=".csv"/>
                            Выберите файл
                        </label>
                        <div class="pt-2">
                            <p class="fst-italic pt-2 text-danger h4">${message}</p>
                        </div>
                        <input class="form-control mt-3" type="text" placeholder="Файл не выбран" id="file-name-guides" disabled readonly/>
                        <!--div id="file-name-guides h5">aervaervaer</div-->
                        <!--div id="file-name-guides1"></div-->
                        <p class="fst-italic pt-2">Список участников МЭДО можно загрузить из csv-файла</p>
                        <input class="form-control btn-custom mt-3 mb-3" type="submit" value="Загрузить файл"/>
                        <!--/div-->
                    </form>
                    <div class="pb-4">
                        <a href="linkHomeServlet" class="btn btn-secondary col-5">На главную</a>
                        <a href="linkListGuidesServlet" class="btn btn-secondary col-5">Участники МЭДО</a>
                    </div>
                </div>
            </div>
        </div>
        <!--div class="row pt-2 align-items-center">
            <div class="col-4 custom-background text-center mx-auto">
                <div class="h3">
                    Загрузка файла со списком участников МЭДО
                </div>
                <div class="pt-2"-->
        <!--form action="uploadListGuidesServlet" method="post" enctype="multipart/form-data">
            <div class="file-upload-guides">
                <label>
                    <input id="file-input-guides" type="file" name="file"/>
                    Выберите файл
                </label>
                <div id="file-name-guides">Файл не выбран</div>
            </div>
        </form-->

        <!--form id="file-upload-form" action="file-upload" method="post" enctype="multipart/form-data">
            <input name="upl" type="file" class="input-file"/>
            <button type="submit">Отправить</button>
            <button type="submit">Назад</button>
            <button type="submit">на главную</button>
        </form-->
        <!--/div>
    </div>
</div-->
    </body>
</html>
