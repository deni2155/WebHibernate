<!--
Страница со спчастников МЭДО
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <script src="../../js/bootstrap.min.js"></script>
        <script src="../..js/jquery.js"></script>
        <script src="../../js/jscript.js"></script>
        <link href="../../css/bootstrap.min.css" rel="stylesheet"/>
        <link href="../../css/custom.css" rel="stylesheet"/>
        <title>Участники МЭДО</title>
    </head>
    <body class="container-fluid">
        <jsp:include page="../templates/header.jsp"/>
        <!--

        Таблица

        -->
        <div class="row custom-tables mt-4 ms-1 me-1 rounded">
            <div class="col-12 table-responsive">
                <!--Документы-->
                <div class="tab-pane fade show active" id="nav-docs" role="tabpanel" aria-labelledby="nav-docs-tab" tabindex="0">
                    <form>
                        <table class="table table-striped table-hover fs-6 table-bordered">
                            <thead>
                                <tr class="text-center">
                                    <th scope="col">#</th>
                                    <th scope="col">
                                        <label for="format" class="form-label">Имя пользователя</label>
                                        <div class="input-group">
                                            <button class="btn btn-custom btn-custom-sort">
                                                <!--Иконка сортировки-->
                                                <img src=""/>
                                            </button>
                                            <input type="text" class="form-control">
                                        </div>
                                    </th>
                                    <th scope="col">											
                                        <div>
                                            <label for="number_doc_doc" class="form-label">E-mail</label>
                                            <div class="input-group">
                                                <button class="btn btn-custom btn-custom-sort" type="button">
                                                    <!--Иконка сортировки-->
                                                    <img src=""/>
                                                </button>
                                                <input type="text" class="form-control">
                                            </div>
                                        </div>
                                    </th>
                                    <th scope="col">											
                                        <div>
                                            <label for="number_doc_doc" class="form-label">GUID</label>
                                            <div class="input-group">
                                                <button class="btn btn-custom btn-custom-sort" type="button">
                                                    <!--Иконка сортировки-->
                                                    <img src=""/>
                                                </button>
                                                <input type="text" class="form-control" id="number_doc_doc">
                                            </div>
                                        </div>
                                    </th>
                                </tr>
                                <tr>
                                    <th scope="col" colspan="9">
                                        <div class="col-12 text-center">
                                            <button class="btn btn-custom ps-5 pe-5 fs-6 fw-lighter">Найти</button>
                                        </div>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row">1</th>
                                    <td>2.2</td>
                                    <td>25</td>
                                    <td>21.08.2022</td>
                                </tr>
                                <tr>
                                    <th scope="row">2</th>
                                    <td>2.7</td>
                                    <td>26</td>
                                    <td>30.12.2001</td>
                                </tr>
                                <tr>
                                    <th scope="row">3</th>
                                    <td>2.7.1</td>
                                    <td>37</td>
                                    <td>17.03.2023</td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <!--
        модальное окно
        -->
        <!--jsp:include page="templates/filedialog.jsp"/-->
    </body>
</html>
