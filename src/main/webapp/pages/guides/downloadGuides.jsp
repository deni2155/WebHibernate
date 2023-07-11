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
        <title>Загрузка участников МЭДО из файла</title>
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
                    <form action="uploadListGuidesServlet" method="post" enctype="multipart/form-data">
                        <!--div class="file-upload-guides"-->
                            <label class="btn btn-primary text-center fw-bolder">
                                <input type="file" id="file-input-guides" name="file" accept=".xlsx,.csv"/>
                                Выберите файл
                            </label>
                            <input class="form-control mt-3" type="text" placeholder="Файл не выбран" id="file-name-guides" disabled readonly/>
                            <!--div id="file-name-guides h5">aervaervaer</div-->
                            <!--div id="file-name-guides1"></div-->
                            <p class="fst-italic pt-2">Выполняется загрузка файлов в формате excel и csv</p>
                            <input class="form-control btn-custom mt-3 mb-3" type="submit" value="Загрузить файл"/>
                        <!--/div-->
                    </form>
                    <div class="pb-4">
                        <button class="btn btn-secondary col-5">На главную</button>
                        <button class="btn btn-secondary col-5">Участники МЭДО</button>
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
