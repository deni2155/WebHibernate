<%@page import="com.kindcat.archivemedo.logger.LoggerForJsp"%>
<%@page import="com.kindcat.archivemedo.logger.LoggerForJspImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--

Меню в шапке страницы

-->
<c:url var="homeUrl" value="/" />
<div class="row custom-row-header fw-bold">
    <div class="col-1 text-start">
        <div class="icon-home">
            <a href="linkHomeServlet" class="btn custom-link-hover" title="Главная страница">
                <!--иконка домой-->
                <img src=""/>
            </a>
        </div>
    </div>
    <div class="col-xxl-9 col-xl-8 col-lg-8 col-md-7 col-sm-6 col-xs-12 text-center">
        ${fName}
    </div>
    <div class="col-xxl-2 col-xl-3 col-lg-3 col-md-4 col-sm-5 col-xs-12 text-end">
        <div class="dropdown-contact">
            <a href="#" class="dropbtn-contact" title="Контакты участников">
                <!--иконка контакты-->
                <img src=""/>
            </a>
            <div class="dropdown-content-contact text-start">
                <div class="list-show-submenu-contact">
                    <a href="#">Редактирование контактов</a>
                    <div class="dropdown-content-sublevel-contact">
                        <a href="#">Добавить</a>
                        <a href="#">Изменить</a>
                        <a href="#">Удалить</a>
                    </div>
                </div>
                <a href="pages/contacts.html">Показать контакты</a>
            </div>
        </div>
        <div class="dropdown-setting">
            <a href="#" class="dropbtn-setting" title="Конфигурация">
                <!--Иконка настройки-->
                <img src=""/>
            </a>
            <div class="dropdown-content-setting text-start">
                <div class="list-show-submenu-setting">
                    <a href="linkListMembersServlet">Справочник участников</a>
                    <div class="dropdown-content-sublevel-setting">
                        <a href="#">Скачать</a>
                        <!--a href="#" data-bs-toggle="modal" data-bs-target="#modal-win-download-listguides">Загрузить</a-->
                        <a href="linkDownloadGuidesServlet">Загрузить</a>
                    </div>
                </div>
                <a href="#">Настройки</a>
            </div>
        </div>
        <div class="icon-exit">
            <a href="#exit" class="btn custom-link-hover" title="Выход">
                <!--иконка выход-->
                <img src=""/>
            </a>
        </div>
    </div>
</div>