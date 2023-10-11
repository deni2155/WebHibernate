<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
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
                                    <select class="form-select form-control" id="notif-in-out" name="notifInOut">
                                        <c:forEach var="listTypePkg" items="${listTypePkg}">
                                            <option value="${listTypePkg.idTypePkg}">${listTypePkg.nameTypePkg}</option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                            </button>
                            <button class="nav-link fw-bold" id="nav-receipts-tab" data-bs-toggle="tab" data-bs-target="#nav-receipts" type="button" role="tab" aria-controls="nav-receipts" aria-selected="false">
                                <img src="icon/receipt.png"/> Квитанции
                                <c:if test="${not empty listTypePkg}">
                                    <select class="form-select form-control" name="receiptInOut">
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
                        <!--
                        --
                        --вкладка с документами
                        --
                        -->
                        <div class="tab-pane fade show active" id="nav-docs" role="tabpanel" aria-labelledby="nav-docs-tab" tabindex="0">
                            <!--
                                пагинация
                            -->
                            <div class="pt-3">
                                <jsp:include page="templates/archive/doc_pagination.jsp"/>
                            </div>
                            <!--form-->
                            <table class="table table-striped table-hover fs-6 table-bordered">
                                <thead>
                                    <tr class="text-center">
                                        <th scope="col">#</th>
                                        <!--Заголовок Формат xml документа-->
                                        <th scope="col">
                                            <label for="doc-format-xml" class="form-label">Формат</label>
                                            <div class="input-group">
                                                <button class="btn btn-custom btn-custom-sort">
                                                    <!--Иконка сортировки-->
                                                    <img src=""/>
                                                </button>
                                                <c:if test="${not empty listSchemaXml}">
                                                    <select class="form-select form-control" id="doc-format-xml">
                                                        <c:forEach var="listSchemaXml" items="${listSchemaXml}">
                                                            <option value="${listSchemaXml.idSchema}">${listSchemaXml.nameSchema}</option>
                                                        </c:forEach>
                                                    </select>
                                                </c:if>
                                            </div>
                                        </th>
                                        <!--Заголовок Номер документа-->
                                        <th scope="col">											
                                            <div>
                                                <label for="doc-number-doc" class="form-label">№ документа</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button">
                                                        <!--Иконка сортировки-->
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="doc-number-doc">
                                                </div>
                                            </div>
                                        </th>
                                        <!--Заголовок Дата регистрации документа-->
                                        <th scope="col">
                                            <div>
                                                <label for="doc-date-reg" class="form-label">Дата регистрации</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button" id="button-addon1">
                                                        <!--Иконка сортировки-->
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="doc-date-reg">
                                                </div>
                                            </div>
                                        </th>
                                        <!--Заголовок Отправитель документа-->
                                        <th scope="col">
                                            <div>
                                                <label for="doc-sender" class="form-label">Отправитель</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button" id="button-addon1">
                                                        <!--Иконка сортировки-->
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="doc-sender">
                                                </div>
                                            </div>
                                        </th>
                                        <!--Получатель документа-->
                                        <th scope="col">
                                            <div>
                                                <label for="doc-recipient" class="form-label">Получатель</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button" id="button-addon1">
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="doc-recipient">
                                                </div>
                                            </div>
                                        </th>
                                        <!--Заголовок Заголовок типа документа (дсп или нет)-->
                                        <th scope="col">
                                            <div class="form-check">
                                                <label for="dsp" class="form-check-label">ДСП</label>
                                                <!--div class="input-group"-->
                                                <input type="checkBox" id="dsp" class="form-check-input" value=""/>
                                                <!--/div-->
                                            </div>
                                        </th>
                                        <!--Заголовок дата и время записи документа-->
                                        <th scope="col">
                                            Дата и время получения
                                        </th>
                                        <!--Заголовок-->
                                        <th scope="col">
                                            <div>
                                                <label class="form-label">Уведомление</label>
                                            </div>
                                        </th>
                                        <!--Заголовок-->
                                        <th scope="col">
                                            <div>
                                                <label class="form-label">Квитанция</label>
                                            </div>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th scope="col" colspan="10">
                                            <div class="col-12 text-center">
                                                <button class="btn btn-custom ps-5 pe-5 fs-6 fw-lighter" type="submit">Найти</button>
                                            </div>
                                        </th>
                                    </tr>
                                </thead>
                                <!--
                                таблица с информацией о документе
                                -->
                                <!--если получен не пустой массив-->
                                <c:if test="${not empty listDocs}">
                                    <tbody>
                                        <!--вывожу массив циклом-->
                                        <c:forEach var="listDocs" items="${listDocs}">
                                            <tr>
                                                <!--идентификатор документа-->
                                                <th scope="row">${listDocs.idDoc}</th>
                                                <!--формат xml-документа-->
                                                <td>${listDocs.schemaXml.nameSchema}</td>
                                                <!--регистрационный номер и регистрационная дата документа-->
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
                                                <!--Отправитель документа-->
                                                <td>
                                                    <div class="text-wrap" style="width: 18rem;">${listDocs.senders.membersSenders.nameOrg}</div>
                                                </td>
                                                <!--Получталеь документа-->
                                                <td>
                                                    <div class="text-wrap" style="width: 18rem;">${listDocs.recipients.membersRecipients.nameOrg}</div>
                                                </td>
                                                <!--тип документа (дсп или нет)-->
                                                <td class="d-flex justify-content-center">
                                                    <!--{listDocs.dsp}-->
                                                    <div class="form-check">
                                                        <c:if test="${not empty listDocs.dsp}">
                                                            <c:if test="${listDocs.dsp==true}">
                                                                <input type="checkbox" class="form-check-input" checked disabled value=""/>
                                                            </c:if>
                                                            <c:if test="${listDocs.dsp==false}">
                                                                <input type="checkbox" class="form-check-input" disabled value=""/>
                                                            </c:if>
                                                        </c:if>
                                                    </div>
                                                </td>
                                                <!--Дата и время записи документа-->
                                                <td>
                                                    <c:if test="${not empty listDocs.whenCreate}">
                                                        ${listDocs.whenCreate.format( DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))}
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <a href="#" class="btn pt-0 pb-0">Показать</a>
                                                </td>
                                                <td>
                                                    <a href="#" class="btn pt-0 pb-0">Показать</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </c:if>
                            </table>
                            <!--
                                пагинация
                            -->
                            <div class="pt-3">
                                <jsp:include page="templates/archive/doc_pagination.jsp"/>
                            </div>
                        </div>
                        <!--
                        --
                        --
                        --Вкладка с уведомлениями
                        --
                        --
                        -->
                        <div class="tab-pane fade" id="nav-notifs" role="tabpanel" aria-labelledby="nav-notifs-tab" tabindex="1">
                            <!--
                                пагинация
                            -->
                            <div class="pt-3">
                                <jsp:include page="templates/archive/notif_pagination.jsp"/>
                            </div>
                            <!--form-->
                            <table class="table table-striped table-hover fs-6 table-bordered">
                                <thead>
                                    <!--Схема xml в уведомлении-->
                                    <tr class="text-center">
                                        <th scope="col">#</th>
                                        <th scope="col">
                                            <label for="notif-xml-format" class="form-label">Формат</label>
                                            <div class="input-group">
                                                <button class="btn btn-custom btn-custom-sort">
                                                    <!--Иконка сортировки-->
                                                    <img src=""/>
                                                </button>
                                                <c:if test="${not empty listSchemaXml}">
                                                    <select class="form-select form-control" id="notif-xml-format">
                                                        <c:forEach var="listSchemaXml" items="${listSchemaXml}">
                                                            <option value="${listSchemaXml.idSchema}">${listSchemaXml.nameSchema}</option>
                                                        </c:forEach>
                                                    </select>
                                                </c:if>
                                            </div>
                                        </th>
                                        <!--Тип уведомлений-->
                                        <th scope="col">
                                            <label for="notif-type" class="form-label">Тип уведомления</label>
                                            <div class="input-group">
                                                <button class="btn btn-custom btn-custom-sort">
                                                    <!--Иконка сортировки-->
                                                    <img src=""/>
                                                </button>
                                                <c:if test="${not empty listTypeNotif}">
                                                    <select class="form-select form-control" id="notif-type">
                                                        <c:forEach var="listTypeNotif" items="${listTypeNotif}">
                                                            <option value="${listTypeNotif.idTypeNotif}">${listTypeNotif.nameType}</option>
                                                        </c:forEach>
                                                    </select>
                                                </c:if>
                                            </div>
                                        </th>
                                        <!--Внутренний номер документа в уведомлении-->
                                        <th scope="col">											
                                            <div>
                                                <label for="notif-in-num" class="form-label">Внутренний № документа</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button">
                                                        <!--Иконка сортировки-->
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="notif-in-num">
                                                </div>
                                            </div>
                                        </th>
                                        <!--внутренняя дата регистрации документа в уведомлении-->
                                        <th scope="col">
                                            <div>
                                                <label for="notif-in-date-reg" class="form-label">Внутренняя дата регистрации</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button" id="button-addon1">
                                                        <!--Иконка сортировки-->
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="notif-in-date-reg">
                                                </div>
                                            </div>
                                        </th>
                                        <!--внешний номер документа в уведомлении-->
                                        <th scope="col">											
                                            <div>
                                                <label for="notif-ex-num" class="form-label">Внешний № документа</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button">
                                                        <!--Иконка сортировки-->
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="notif-ex-num">
                                                </div>
                                            </div>
                                        </th>
                                        <!--внешняя дата регистрации в уведомлении-->
                                        <th scope="col">
                                            <div>
                                                <label for="notif-ex-date-reg" class="form-label">Внешняя дата регистрации</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button" id="button-addon1">
                                                        <!--Иконка сортировки-->
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="notif-ex-date-reg">
                                                </div>
                                            </div>
                                        </th>
                                        <!--отправитель уведомлений-->
                                        <th scope="col">
                                            <div>
                                                <label for="notif-sender" class="form-label">Отправитель</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button" id="button-addon1">
                                                        <!--Иконка сортировки-->
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="notif-sender">
                                                </div>
                                            </div>
                                        </th>
                                        <!---получатель уведомления-->
                                        <th scope="col">
                                            <div>
                                                <label for="notif-recipient" class="form-label">Получатель</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button" id="button-addon1">
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="notif-recipient">
                                                </div>
                                            </div>
                                        </th>
                                        <th scope="col">
                                            Дата и время записи
                                        </th>
                                        <th scope="col">
                                            <div>
                                                <label class="form-label">Документ</label>
                                            </div>
                                        </th>
                                        <th scope="col">
                                            <div>
                                                <label class="form-label">Квитанция</label>
                                            </div>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th scope="col" colspan="12">
                                            <div class="col-12 text-center">
                                                <button class="btn btn-custom ps-5 pe-5 fs-6 fw-lighter" type="submit">Найти</button>
                                            </div>
                                        </th>
                                    </tr>
                                </thead>
                                <!--
                                таблица с информацией о документе
                                -->
                                <!--если получен не пустой массив-->
                                <c:if test="${not empty listNotifs}">
                                    <tbody>
                                        <!--вывожу массив циклом-->
                                        <c:forEach var="listNotifs" items="${listNotifs}">
                                            <tr>
                                                <!--идентификатор уведомления-->
                                                <th scope="row">${listNotifs.idNotif}</th>
                                                <!--формат xml-уведомления-->
                                                <td>${listNotifs.schemaXml.nameSchema}</td>
                                                <!--тип уведомления-->
                                                <td>${listNotifs.notifType.nameType}</td>
                                                <!--внешний номер документа в уведомлении-->
                                                <td>${listNotifs.inNum}</td>
                                                <!--внешняя дата регистрации в уведомлении-->
                                                <td><fmt:formatDate value="${listNotifs.inDate}" pattern="dd.MM.yyyy"/></td>
                                                <!--внутренний номер документа в уведомлении-->
                                                <td>${listNotifs.exNum}</td>
                                                <!--внутреняя дата регистрации в уведомлении-->
                                                <td><fmt:formatDate value="${listNotifs.exDate}" pattern="dd.MM.yyyy"/></td>
                                                <!--Отправитель уведомления-->
                                                <td>
                                                    <div class="text-wrap" style="width: 18rem;">${listNotifs.senders.membersSenders.nameOrg}</div>
                                                </td>
                                                <!--Получталеь уведомления-->
                                                <td>
                                                    <div class="text-wrap" style="width: 18rem;">${listNotifs.recipients.membersRecipients.nameOrg}</div>
                                                </td>
                                                <!--Дата и время записи уведомления-->
                                                <td>
                                                    <c:if test="${not empty listNotifs.whenCreate}">
                                                        ${listNotifs.whenCreate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))}
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </c:if>
                            </table>
                            <!--
                                пагинация
                            -->
                            <div class="pt-3">
                                <jsp:include page="templates/archive/notif_pagination.jsp"/>
                            </div>
                        </div>
                        <!--
                        --
                        --
                        --
                        --Вкладка с квитанциями
                        --
                        --
                        --
                        -->
                        <div class="tab-pane fade" id="nav-receipts" role="tabpanel" aria-labelledby="nav-receipts-tab" tabindex="2">
                            <!--
                                    пагинация
                            -->
                            <div class="pt-3">
                                <jsp:include page="templates/archive/receipt_pagination.jsp"/>
                            </div>
                            <!--form-->
                            <table class="table table-striped table-hover fs-6 table-bordered">
                                <thead>
                                    <!--Схема xml в квитанции-->
                                    <tr class="text-center">
                                        <th scope="col">#</th>
                                        <th scope="col">
                                            <label for="receipt-xml-format" class="form-label">Формат</label>
                                            <div class="input-group">
                                                <button class="btn btn-custom btn-custom-sort">
                                                    <!--Иконка сортировки-->
                                                    <img src=""/>
                                                </button>
                                                <c:if test="${not empty listSchemaXml}">
                                                    <select class="form-select form-control" id="receipt-xml-format">
                                                        <c:forEach var="listSchemaXml" items="${listSchemaXml}">
                                                            <option value="${listSchemaXml.idSchema}">${listSchemaXml.nameSchema}</option>
                                                        </c:forEach>
                                                    </select>
                                                </c:if>
                                            </div>
                                        </th>
                                        <!--UID пакета, для которого получна квитанция-->
                                        <th scope="col">											
                                            <div>
                                                <label for="receipt-for-uid" class="form-label">Квитанция получена для пакета</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button">
                                                        <!--Иконка сортировки-->
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="receipt-for-uid">
                                                </div>
                                            </div>
                                        </th>
                                        <!--Информация о доставке пакета-->
                                        <th scope="col">
                                            <div class="form-check">
                                                <label for="receipt-delivery" class="form-check-label">Доставлен</label>
                                                <!--div class="input-group"-->
                                                <input type="checkBox" class="form-check-input" value="" id="receipt-delivery"/>
                                                <!--/div-->
                                            </div>
                                        </th>
                                        <!--отправитель квитанции-->
                                        <th scope="col">
                                            <div>
                                                <label for="receipt-sender" class="form-label">Отправитель</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button" id="button-addon1">
                                                        <!--Иконка сортировки-->
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="receipt-sender">
                                                </div>
                                            </div>
                                        </th>
                                        <!---получатель квитанции-->
                                        <th scope="col">
                                            <div>
                                                <label for="receipt-recipient" class="form-label">Получатель</label>
                                                <div class="input-group">
                                                    <button class="btn btn-custom btn-custom-sort" type="button" id="button-addon1">
                                                        <img src=""/>
                                                    </button>
                                                    <input type="text" class="form-control" id="receipt-recipient">
                                                </div>
                                            </div>
                                        </th>
                                        <th scope="col">
                                            Дата и время записи
                                        </th>
                                        <th scope="col">
                                            <div>
                                                <label class="form-label">Документ</label>
                                            </div>
                                        </th>
                                        <th scope="col">
                                            <div>
                                                <label class="form-label">Уведомление</label>
                                            </div>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th scope="col" colspan="12">
                                            <div class="col-12 text-center">
                                                <button class="btn btn-custom ps-5 pe-5 fs-6 fw-lighter" type="submit">Найти</button>
                                            </div>
                                        </th>
                                    </tr>
                                </thead>
                                <!--
                                таблица с информацией о квитанциях
                                -->
                                <!--если получен не пустой массив-->
                                <c:if test="${not empty listReceipts}">
                                    <tbody>
                                        <!--вывожу массив циклом-->
                                        <c:forEach var="listReceipts" items="${listReceipts}">
                                            <tr>
                                                <!--идентификатор уведомления-->
                                                <th scope="row">${listReceipts.idReceipt}</th>
                                                <!--формат xml-уведомления-->
                                                <td>${listReceipts.schemaXml.nameSchema}</td>
                                                <!--uid пакета, для которого получена квитанция-->
                                                <td>${listReceipts.forUid}</th>
                                                    <!--доставлен али нет-->
                                                <td class="d-flex justify-content-center">
                                                    <div class="form-check">
                                                        <c:if test="${not empty listReceipts.deliv}">
                                                            <c:if test="${listReceipts.deliv==true}">
                                                                <input type="checkbox" class="form-check-input" checked disabled value=""/>
                                                            </c:if>
                                                            <c:if test="${listReceipts.deliv==false}">
                                                                <input type="checkbox" class="form-check-input" disabled value=""/>
                                                            </c:if>
                                                        </c:if>
                                                    </div>
                                                </td>
                                                <!--Отправитель Квитанции-->
                                                <td>
                                                    <div class="text-wrap" style="width: 22rem;">${listReceipts.senders.membersSenders.nameOrg}</div>
                                                </td>
                                                <!--Получатель Квитанции-->
                                                <td>
                                                    <div class="text-wrap" style="width: 22rem;">${listReceipts.recipients.membersRecipients.nameOrg}</div>
                                                </td>
                                                <!--Дата и время записи квитанции-->
                                                <td>
                                                    <c:if test="${not empty listReceipts.whenCreate}">
                                                        ${listReceipts.whenCreate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))}
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </c:if>
                            </table>
                            <!--
                                пагинация
                            -->
                            <div class="pt-3">
                                <jsp:include page="templates/archive/receipt_pagination.jsp"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
