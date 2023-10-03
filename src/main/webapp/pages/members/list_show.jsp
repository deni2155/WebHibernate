<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--
Страница со спчастников МЭДО
-->
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/member/ajax-member.js"></script>
        <script src="js/member/jscript-member.js"></script>
        <link href="css/bootstrap.min.css" rel="stylesheet"/>
        <link href="css/custom.css" rel="stylesheet"/>
        <title>Участники МЭДО</title>
    </head>
    <body class="container-fluid">
        <jsp:include page="../templates/header.jsp"/>
        <!--

        Таблица

        -->
        <div class="row custom-tables mt-4 ms-1 me-1 rounded">
            <!--
            пагинация
            -->
            <div class="pt-3">
                <jsp:include page="../templates/pagination.jsp"/>
            </div>
            <div class="col-12 table-responsive">
                <!--Документы-->
                <div class="tab-pane fade show active" id="nav-docs" role="tabpanel" aria-labelledby="nav-docs-tab" tabindex="0">
                    <!--form-->
                    <table class="table table-striped table-hover align-middle fs-6">
                        <thead>
                            <tr class="text-center">
                                <th scope="col">
                                    <button class="btn btn-custom btn-custom-add-row" data-bs-toggle="modal" data-bs-target="#add-medo-participant">
                                        <!--Иконка добавления участника МЭДО-->
                                        <img src="" title="Добавить запись"/>
                                    </button>
                                </th>
                                <th scope="col">
                                    <label for="format" class="form-label font-custom-table-header">Имя пользователя</label>
                                    <div class="input-group">
                                        <button class="btn btn-custom btn-custom-sort">
                                            <!--Иконка сортировки-->
                                            <img src=""/>
                                        </button>
                                        <input type="text" class="form-control form-control-sm">
                                    </div>
                                </th>
                                <th scope="col">											
                                    <div>
                                        <label for="number_doc_doc" class="form-label font-custom-table-header">Адресат</label>
                                        <div class="input-group">
                                            <button class="btn btn-custom btn-custom-sort" type="button">
                                                <!--Иконка сортировки-->
                                                <img src=""/>
                                            </button>
                                            <input type="text" class="form-control form-control-sm">
                                        </div>
                                    </div>
                                </th>
                                <th scope="col">											
                                    <div>
                                        <label for="number_doc_doc" class="form-label font-custom-table-header">GUID</label>
                                        <div class="input-group">
                                            <button class="btn btn-custom btn-custom-sort" type="button">
                                                <!--Иконка сортировки-->
                                                <img src=""/>
                                            </button>
                                            <input type="text" class="form-control form-control-sm" id="number_doc_doc">
                                        </div>
                                    </div>
                                </th>
                                <th scope="col" class="ms-0 me-0 text-center">											
                                    <div class="input-group">
                                        <button class="btn btn-custom btn-custom-search" type="button">
                                            <!--Иконка поиска-->
                                            <img src=""/>
                                        </button>
                                    </div>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--если получен не пустой массив-->
                            <!--массив получен из PaginationMembersServlet-->
                            <c:if test="${not empty listMembers}">
                                <c:forEach var="listMembers" items="${listMembers}">
                                    <tr>
                                        <th scope="row" class="text-center font-custom-table-content">${listMembers.idMembers}</th>
                                        <td class="font-custom-table-content">${listMembers.nameOrg}</td>
                                        <td class="font-custom-table-content">${listMembers.addr}</td>
                                        <td class="font-custom-table-content">${listMembers.guid}</td>
                                        <td>
                                            <a href="#" class="btn-custom-update-row text-decoration-none text-reset href-window-for-update-member" data-bs-toggle="modal" data-bs-target="#update-medo-participant" data-update-id-value="${listMembers.idMembers}" data-update-name-org="${listMembers.nameOrg}" data-update-email-org="${listMembers.addr}" data-update-guid-org="${listMembers.guid}">    
                                                <img src="" title="Редактировать"/>
                                            </a>
                                            <a href="#" class="btn-custom-delete-row text-decoration-none text-reset href-window-for-delete-member" data-bs-toggle="modal" data-bs-target="#delete-medo-participant" data-delete-id-value="${listMembers.idMembers}" data-delete-name-org="${listMembers.nameOrg}">
                                                <img src="" title="Удалить"/>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <!--Если массив пустой-->
                            <c:if test="${empty listMembers}">
                                <tr>
                                    <th scope="row" class="text-center font-custom-table-content"></th>
                                    <td class="font-custom-table-content">Записи не найдены</td>
                                    <td class="font-custom-table-content">Записи не найдены</td>
                                    <td class="font-custom-table-content">Записи не найдены</td>
                                    <td></td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                    <!--/form-->
                </div>
            </div>

            <!--
            пагинация
            -->
            <jsp:include page="../templates/pagination.jsp"/>


            <!--
            модальное окно для добавления нового участника МЭДО
            -->
            <jsp:include page="../templates/members/winmodal/add_member.jsp"/>
            <!--
            Модальное окно для редактирования участника МЭДО
            -->
            <jsp:include page="../templates/members/winmodal/update_member.jsp"/>
            <!--
            Модальное окно для подтверждения удаления участника МЭДО
            -->
            <jsp:include page="../templates/members/winmodal/delete_member.jsp"/>
    </body>
</html>
