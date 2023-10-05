<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--
Таблица со списком пакетов
-->
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.js"></script>
        <link href="css/bootstrap.min.css" rel="stylesheet"/>
        <link href="css/custom.css" rel="stylesheet"/>
        <script src="js/archive/jscript-archive.js"></script>
        <!--script src="js/archive/ajax-archive.js"></script-->
        <title>Архив МЭДО</title>
    </head>
    <body class="container-fluid">
        <jsp:include page="templates/header.jsp"/>
        <form action="" method="get">
            <!--Вкладки-->
            <div class="row mt-2 ms-1 me-1 rounded">
                <div class="col-xxl-12 col-xl-12 col-lg-12 col-md-11">
                    <nav>
                        <div class="nav nav-tabs" id="nav-tab" role="tablist">
                            <button class="nav-link active fw-bold" id="nav-docs-tab" data-bs-toggle="tab" data-bs-target="#nav-docs" type="button" role="tab" aria-controls="nav-docs" aria-selected="true">
                                <img src="icon/document.png"/> Документы
                                <c:if test="${not empty listTypePkg}">
                                    <select class="form-select form-control" id="doc-in-out" name="docInOut">
                                        <c:forEach var="listTypePkg" items="${listTypePkg}">
                                            <option value="${listTypePkg.idTypePkg}">${listTypePkg.nameTypePkg}</option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                            </button>
                            <button class="nav-link fw-bold" id="nav-notifs-tab" data-bs-toggle="tab" data-bs-target="#nav-notifs" type="button" role="tab" aria-controls="nav-notifs" aria-selected="false">
                                <img src="icon/notification.png"/> Уведомления
                                <c:if test="${not empty listTypePkg}">
                                    <select class="form-select form-control">
                                        <c:forEach var="listTypePkg" items="${listTypePkg}">
                                            <option value="${listTypePkg.idTypePkg}">${listTypePkg.nameTypePkg}</option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                            </button>
                            <button class="nav-link fw-bold" id="nav-receipts-tab" data-bs-toggle="tab" data-bs-target="#nav-receipts" type="button" role="tab" aria-controls="nav-receipts" aria-selected="false">
                                <img src="icon/receipt.png"/> Квитанции
                                <c:if test="${not empty listTypePkg}">
                                    <select class="form-select form-control">
                                        <c:forEach var="listTypePkg" items="${listTypePkg}">
                                            <option value="${listTypePkg.idTypePkg}">${listTypePkg.nameTypePkg}</option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                            </button>
                        </div>
                    </nav>

                </div>
            </div>
            <!--
            
            Таблицы с файлами
            
            -->
            <div class="row custom-tables ms-1 me-1 rounded">
                <div class="col-12 table-responsive">
                    <div class="tab-content" id="nav-tabContent">
                        <!--Документы-->
                        <div class="tab-pane fade show active" id="nav-docs" role="tabpanel" aria-labelledby="nav-docs-tab" tabindex="0">
                            <!--form-->
                            <table class="table table-striped table-hover fs-6 table-bordered">
                                <thead>
                                    <tr class="text-center">
                                        <th scope="col">#</th>
                                        <th scope="col">
                                            <label for="format" class="form-label">Формат</label>
                                            <div class="input-group">
                                                <button class="btn btn-custom btn-custom-sort">
                                                    <!--Иконка сортировки-->
                                                    <img src=""/>
                                                </button>
                                                <c:if test="${not empty listSchemaXml}">
                                                    <select class="form-select form-control">
                                                        <c:forEach var="listSchemaXml" items="${listSchemaXml}">
                                                            <option value="${listSchemaXml.idSchema}">${listSchemaXml.nameSchema}</option>
                                                        </c:forEach>
                                                    </select>
                                                </c:if>
                                            </div>
                                        </th>
                                        <th scope="col">											
                                            <div>
                                                <label for="number_doc_doc" class="form-label">№ документа</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button">
                                                        <!--Иконка сортировки-->
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="number_doc_doc">
                                                </div>
                                            </div>
                                        </th>
                                        <th scope="col">
                                            <div>
                                                <label for="date_reg_doc_doc" class="form-label">Дата регистрации</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button" id="button-addon1">
                                                        <!--Иконка сортировки-->
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="date_reg_doc_doc">
                                                </div>
                                            </div>
                                        </th>
                                        <th scope="col">
                                            <div>
                                                <label for="sender_doc_doc" class="form-label">Отправитель</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button" id="button-addon1">
                                                        <!--Иконка сортировки-->
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="sender_doc_doc">
                                                </div>
                                            </div>
                                        </th>
                                        <th scope="col">
                                            <div>
                                                <label for="recipient_doc_doc" class="form-label">Получатель</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button" id="button-addon1">
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="recipient_doc_doc">
                                                </div>
                                            </div>
                                        </th>
                                        <th scope="col">
                                            <div>
                                                <label for="time_and_date_insert_doc" class="form-label">Дата и время записи</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button" id="button-addon1">
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="time_and_date_insert_doc">
                                                </div>
                                            </div>
                                        </th>
                                        <th scope="col">
                                            <div class="form-check">
                                                <label for="dsp" class="form-check-label">ДСП</label>
                                                <!--div class="input-group"-->
                                                <input type="checkBox" id="dsp" class="form-check-input" value=""/>
                                                <!--/div-->
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
                                                <button class="btn btn-custom ps-5 pe-5 fs-6 fw-lighter" type="submit">Найти</button>
                                            </div>
                                        </th>
                                    </tr>
                                </thead>
                                <!--если получен не пустой массив-->
                                <c:if test="${not empty listDocs}">
                                    <tbody>
                                        <!--вывожу массив циклом-->
                                        <c:forEach var="listDocs" items="${listDocs}">
                                            <tr>
                                                <th scope="row">${listDocs.idDoc}</th>
                                                <td>${listDocs.schemaXml.nameSchema}</td>
                                                <!--если документы входящие, вывожу внешние реквизиты документа-->
                                                <c:if test="${listDocs.idInOut eq 1}">
                                                    <td>${listDocs.exNum}</td>
                                                    <td><fmt:formatDate value="${listDocs.exDate}" pattern="dd.MM.yyyy"/></td>
                                                </c:if>
                                                <!--если документы исходящие, вывожу внешние реквизиты документа-->
                                                <c:if test="${listDocs.idInOut eq 2}">
                                                    <td>${listDocs.inNum}</td>
                                                    <td><fmt:formatDate value="${listDocs.inDate}" pattern="dd.MM.yyyy"/></td>
                                                </c:if>
                                                <td>Мордовия</td>
                                                <td>Сахалин</td>
                                                <td>10.12.1962 10:00:15</td>
                                                <td class="d-flex justify-content-center">
                                                    <div class="form-check">
                                                        <input type="checkbox" class="form-check-input" checked="false" disabled value=""/>
                                                    </div>
                                                </td>
                                                <td>
                                                    <a href="#" class="btn pt-0 pb-0">Показать</a>
                                                </td>
                                                <td>
                                                    <a href="#" class="btn pt-0 pb-0">Показать</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <!--tr>
                                            <th scope="row">2</th>
                                            <td>2.7</td>
                                            <td>26</td>
                                            <td>30.12.2001</td>
                                            <td>Администрация</td>
                                            <td>Камчатка</td>
                                            <td>27.06.2005 08:22:15</td>
                                            <td class="d-flex justify-content-center">
                                                <div class="form-check">
                                                    <input type="checkbox" class="form-check-input" checked="false" disabled value=""/>
                                                </div>
                                            </td>
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
                                            <td class="d-flex justify-content-center">
                                                <div class="form-check">
                                                    <input type="checkbox" class="form-check-input" checked="false" disabled value=""/>
                                                </div>
                                            </td>
                                            <td>
                                                <a href="#" class="btn pt-0 pb-0">Показать</a>
                                            </td>
                                            <td>
                                                <a href="#" class="btn pt-0 pb-0">Показать</a>
                                            </td>
                                        </tr-->
                                    </tbody>
                                </c:if>
                            </table>
                            <!--/form-->
                        </div>
                        <div class="tab-pane fade" id="nav-notifs" role="tabpanel" aria-labelledby="nav-notifs-tab" tabindex="1">Эй, здорова</div>
                        <div class="tab-pane fade" id="nav-receipts" role="tabpanel" aria-labelledby="nav-receipts-tab" tabindex="2">Здорова, заебал</div>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
