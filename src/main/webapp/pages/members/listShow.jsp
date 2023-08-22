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
                                        <label for="number_doc_doc" class="form-label">Адресат</label>
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
                            <c:forEach var="listMembers" items="${listMembers}">
                                <tr>
                                    <th scope="row" class="text-center">${listMembers.idMembers}</th>
                                    <td>${listMembers.nameOrg}</td>
                                    <td>${listMembers.addr}</td>
                                    <td>${listMembers.guid}</td>
                                    <td>
                                        <!--a class="btn-custom-update-row text-decoration-none text-reset href-window-for-update-member" href="#" data-update-id-value="" data-name-org="" data-bs-toggle="modal" data-bs-target="#update-medo-participant"-->
                                        <a class="btn-custom-update-row text-decoration-none text-reset href-window-for-update-member" href="#" data-bs-toggle="modal" data-bs-target="#update-medo-participant" data-update-id-value="${listMembers.idMembers}" data-update-name-org="${listMembers.nameOrg}" data-update-email-org="${listMembers.addr}" data-update-guid-org="${listMembers.guid}">    
                                            <img src="" title="Редактировать"/>
                                        </a>
                                        <a href="#" class="btn-custom-delete-row text-decoration-none text-reset">
                                            <img src="" title="Удалить"/>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <!--/form-->
                </div>
            </div>
        </div>



        <!--
        модальное окно для добавления нового участника МЭДО
        -->
        <div class="modal fade" id="add-medo-participant" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="addMedoParticipantLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <form id="formAddMember">
                        <div class="modal-header">
                            <h5 class="modal-title">Добавление нового участника МЭДО</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                        </div>
                        <div class="modal-body">
                            <label for="nameAddOrgMemberListGuides" class="form-label">Наименование организации</label>
                            <input type="text" class="form-control valid-input-type" name="nameAddOrgMember" id="nameAddOrgMemberListGuides" placeholder=" " pattern="^[А-Яа-яЁё\s\(\)«»]+$" required/>
                            <label for="emailAddMemberListGuides" class="form-label">Адресат</label>
                            <input type="text" class="form-control valid-input-type" name="emailAddMemberList" id="emailAddMemberListGuides" placeholder=" " pattern="[A-Z~-_]+" required/>
                            <label for="guidAddMemberListGuides" class="form-label">Уникальный идентификатор участника</label>
                            <input type="text" class="form-control valid-input-type" name="guidAddMember" id="guidAddMemberListGuides" placeholder=" " pattern="[a-z0-9\-]+" required/>
                        </div>
                        <!--Сообщение после добавления участника МЭДО-->
                        <div id="message-for-add-proccess" class="text-center pb-3"></div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                            <button type="submit" class="btn btn-custom">Сохранить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!--
       Модальное окно для редактирования участника МЭДО
        -->
        <div class="modal fade" id="update-medo-participant" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="updateMedoParticipantLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <form id="formAddMember">
                        <div class="modal-header">
                            <h5 class="modal-title">Изменение участника МЭДО</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                        </div>
                        <div class="modal-body">
                            <input type="hidden" class="id-member-update-id-value" id="selector-for-member-id-update"/>
                            <label for="nameUpdateOrgMemberListGuides" class="form-label">Наименование организации</label>
                            <input type="text" class="form-control valid-input-type name-update-org-member-value" name="nameUpdateOrgMember" id="selector-for-member-name-update" placeholder=" " pattern="^[А-Яа-яЁё\s\(\)«»]+$" required/>
                            <label for="emailUpdateMemberListGuides" class="form-label">Адресат</label>
                            <input type="text" class="form-control valid-input-type email-org-update-value" name="emailUpdateMemberList" id="selector-for-member-email-update" placeholder=" " pattern="[A-Z~-_]+" required/>
                            <label for="guidUpdateMemberListGuides" class="form-label">Уникальный идентификатор участника</label>
                            <input type="text" class="form-control valid-input-type" name="guidUpdateMember" id="selector-for-member-guid-update" placeholder=" " pattern="[a-z0-9\-]+" required/>
                        </div>
                        <!--Сообщение после добавления участника МЭДО-->
                        <div id="message-for-add-proccess" class="text-center pb-3"></div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                            <button type="submit" class="btn btn-custom">Сохранить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--div class="modal fade" id="update-medo-participant" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="update-participant-label" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <form id="formUpdateMember">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title ps-2 pe-2" id="update-participant-header-label">Изменение участника МЭДО</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                        </div>
                        <div class="modal-body">
                            <input type="hidden" class="id-member-update-id-value" id="selector-for-member-id-update"/>
                            <label for="namUpdateOrgMemberListGuides" class="form-label">Наименование организации</label>
                            <input type="text" class="form-control valid-input-type name-org-member-value" id="selector-for-member-name-update" name="namUpdateOrgMemberListGuides" id="nameUpdateOrgMemberListGuides" placeholder=" " pattern="^[А-Яа-яЁё\s\(\)«»]+$" required/>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                            <button type="button" class="btn btn-primary">Oк</button>
                        </div>
                    </div>
                </form>
            </div>
        </div-->

        <!--
        Модальное окно для подтверждения удаления участника МЭДО
        -->
        <div class="modal fade" id="deleteParticipant" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="deleteParticipantLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteParticipantLabel">Удаление участника МЭДО</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                    </div>
                    <div class="modal-body">
                        Вы действительно хотите удалить участника МЭДО
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                        <button type="button" class="btn btn-primary">Oк</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
