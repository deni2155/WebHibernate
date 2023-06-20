<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.js"></script>
        <script src="js/jscript.js"></script>
        <link href="css/bootstrap.min.css" rel="stylesheet"/>
        <link href="css/custom.css" rel="stylesheet"/>
        <title>Архив МЭДО</title>
    </head>
    <body class="container-fluid">
        <jsp:include page="templates/header.jsp"/>
        <!--Вкладки-->
        <div class="row mt-2 ms-1 me-1 rounded">
            <div class="col-xxl-12 col-xl-12 col-lg-12 col-md-11">
                <nav>
                    <div class="nav nav-tabs" id="nav-tab" role="tablist">
                        <button class="nav-link active fw-bold" id="nav-docs-tab" data-bs-toggle="tab" data-bs-target="#nav-docs" type="button" role="tab" aria-controls="nav-docs" aria-selected="true">
                            <img src="icon/document.png"/> Документы
                            <select class="form-select form-control" id="format">
                                <option value="1">Входящие</option>
                                <option value="2">Исходящие</option>
                            </select>
                        </button>
                        <button class="nav-link fw-bold" id="nav-notifs-tab" data-bs-toggle="tab" data-bs-target="#nav-notifs" type="button" role="tab" aria-controls="nav-notifs" aria-selected="false">
                            <img src="icon/notification.png"/> Уведомления
                            <select class="form-select form-control" id="format">
                                <option value="1">Входящие</option>
                                <option value="2">Исходящие</option>
                            </select>
                        </button>
                        <button class="nav-link fw-bold" id="nav-receipts-tab" data-bs-toggle="tab" data-bs-target="#nav-receipts" type="button" role="tab" aria-controls="nav-receipts" aria-selected="false">
                            <img src="icon/receipt.png"/> Квитанции
                            <select class="form-select form-control" id="format">
                                <option value="1">Входящие</option>
                                <option value="2">Исходящие</option>
                            </select>
                        </button>
                    </div>
                </nav>

            </div>
        </div>
        <!--Таблицы с файлами-->
        <div class="row custom-tables ms-1 me-1 rounded">
            <div class="col-12 table-responsive">
                <div class="tab-content" id="nav-tabContent">
                    <!--Документы-->
                    <div class="tab-pane fade show active" id="nav-docs" role="tabpanel" aria-labelledby="nav-docs-tab" tabindex="0">
                        <form>
                            <table class="table table-striped table-hover fs-6 table-bordered">
                                <thead>
                                    <tr class="text-center">
                                        <th scope="col">#</th>
                                        <th scope="col">
                                            <label for="format" class="form-label">Формат</label>
                                            <div class="input-group">
                                                <button class="btn btn-outline-secondary btn-custom">
                                                    <img src="icon/sort.png">
                                                </button>
                                                <select class="form-select form-control" id="format">
                                                    <option selected>One</option>
                                                    <option value="1">One</option>
                                                    <option value="2">Two</option>
                                                    <option value="3">Three</option>
                                                </select>
                                            </div>
                                            <!--div>
                                                    <label for="format" class="form-label">Формат</label>
                                                    <select class="form-select form-control" id="format">
                                                            <option selected>One</option>
                                                            <option value="1">One</option>
                                                            <option value="2">Two</option>
                                                            <option value="3">Three</option>
                                                    </select>
                                            </div-->
                                        </th>
                                        <th scope="col">											
                                            <div>
                                                <label for="number_doc_doc" class="form-label">№ документа</label>
                                                <div class="input-group">
                                                    <button class="btn btn-outline-secondary btn-custom" type="button">
                                                        <img src="icon/sort.png">
                                                    </button>
                                                    <input type="text" class="form-control" id="number_doc_doc">
                                                </div>
                                            </div>
                                        </th>
                                        <th scope="col">
                                            <div>
                                                <label for="date_reg_doc_doc" class="form-label">Дата регистрации</label>
                                                <div class="input-group">
                                                    <button class="btn btn-outline-secondary btn-custom" type="button" id="button-addon1">
                                                        <img src="icon/sort.png">
                                                    </button>
                                                    <input type="text" class="form-control" id="date_reg_doc_doc">
                                                </div>
                                            </div>
                                        </th>
                                        <th scope="col">
                                            <div>
                                                <label for="sender_doc_doc" class="form-label">Отправитель</label>
                                                <div class="input-group">
                                                    <button class="btn btn-outline-secondary btn-custom" type="button" id="button-addon1">
                                                        <img src="icon/sort.png">
                                                    </button>
                                                    <input type="text" class="form-control" id="sender_doc_doc">
                                                </div>
                                            </div>
                                        </th>
                                        <th scope="col">
                                            <div>
                                                <label for="recipient_doc_doc" class="form-label">Получатель</label>
                                                <div class="input-group">
                                                    <button class="btn btn-outline-secondary btn-custom" type="button" id="button-addon1">
                                                        <img src="icon/sort.png">
                                                    </button>
                                                    <input type="text" class="form-control" id="recipient_doc_doc">
                                                </div>
                                            </div>
                                        </th>
                                        <th scope="col">
                                            <div>
                                                <label for="time_and_date_insert_doc" class="form-label">Дата и время записи</label>
                                                <div class="input-group">
                                                    <button class="btn btn-outline-secondary btn-custom" type="button" id="button-addon1">
                                                        <img src="icon/sort.png">
                                                    </button>
                                                    <input type="text" class="form-control" id="time_and_date_insert_doc">
                                                </div>
                                            </div>
                                        </th>
                                        <th scope="col">
                                            <div>
                                                <label class="form-label">Уведомление</label>
                                            </div>
                                        </th>
                                        <th scope="col">
                                            <div>
                                                <label class="form-label">Квитанция</label>
                                            </div>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th scope="col" colspan="9">
                                            <div class="col-12 text-center">
                                                <buton class="btn btn-outline-secondary btn-custom ps-5 pe-5 fs-6 fw-lighter">Найти</buton>
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
                                        <td>Мордовия</td>
                                        <td>Сахалин</td>
                                        <td>10.12.1962 10:00:15</td>
                                        <td>
                                            <a href="#" class="btn pt-0 pb-0">Показать</a>
                                        </td>
                                        <td>
                                            <a href="#" class="btn pt-0 pb-0">Показать</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">2</th>
                                        <td>2.7</td>
                                        <td>26</td>
                                        <td>30.12.2001</td>
                                        <td>Администрация</td>
                                        <td>Камчатка</td>
                                        <td>27.06.2005 08:22:15</td>
                                        <td>
                                            <a href="#" class="btn pt-0 pb-0">Показать</a>
                                        </td>
                                        <td>
                                            <a href="#" class="btn pt-0 pb-0">Показать</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">3</th>
                                        <td>2.7.1</td>
                                        <td>37</td>
                                        <td>17.03.2023</td>
                                        <td>Царь</td>
                                        <td>Барнаул</td>
                                        <td>21.10.2004 04:00:15</td>
                                        <td>
                                            <a href="#" class="btn pt-0 pb-0">Показать</a>
                                        </td>
                                        <td>
                                            <a href="#" class="btn pt-0 pb-0">Показать</a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>
                    <div class="tab-pane fade" id="nav-notifs" role="tabpanel" aria-labelledby="nav-notifs-tab" tabindex="1">Эй, здорова</div>
                    <div class="tab-pane fade" id="nav-receipts" role="tabpanel" aria-labelledby="nav-receipts-tab" tabindex="2">Здорова, заебал</div>
                </div>
            </div>
        </div>
    </body>
</html>
