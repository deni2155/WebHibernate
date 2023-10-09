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

        <!--
пагинация
        -->
        <div class="pt-3">
            <jsp:include page="templates/pagination.jsp"/>
        </div>

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
                                            Дата и время записи
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
                                                <td>
                                                    <div class="text-wrap" style="width: 18rem;">${listDocs.senders.membersSenders.nameOrg}</div>
                                                </td>
                                                <td>
                                                    <div class="text-wrap" style="width: 18rem;">${listDocs.recipients.membersRecipients.nameOrg}</div>
                                                </td>
                                                <td>
                                                    <c:if test="${not empty listDocs.whenCreate}">
                                                        ${listDocs.whenCreate.format( DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))}
                                                    </c:if>
                                                </td>
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
                            <!--/form-->
                        </div>
                        <div class="tab-pane fade" id="nav-notifs" role="tabpanel" aria-labelledby="nav-notifs-tab" tabindex="1">Эй, здорова</div>
                        <div class="tab-pane fade" id="nav-receipts" role="tabpanel" aria-labelledby="nav-receipts-tab" tabindex="2">Здорова, заебал</div>
                    </div>
                </div>
            </div>
        </form>
                <!--
пагинация
        -->
        <div class="pt-3">
            <jsp:include page="templates/pagination.jsp"/>
        </div>
    </body>
</html>
