<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--
Пагинация
-->
<div class="col-12">
    <div class="d-flex justify-content-center">
        <ul class="pagination">
            <!--
            
            Кнопка предыдущая страница
            
            -->
            <!--Если пользователь находится не на первой странице-->
            <c:if test="${currentPageDoc > 1}">
                <!--выводим ссылку на Предыдущая страницу-->
                <li class="page-item">
                    <a class="page-link" href="?docPage=${currentPageDoc-1}">Предыдущая</a>
                </li>
            </c:if>
            <!--Если пользователь находится на первой странице или ввёл номер страницы меньше 1-->
            <c:if test="${currentPageDoc eq 1 || currentPageDoc < 1}">
                <!--отключаем кнопку-->
                <li class="page-item disabled">
                    <span class="page-link">Предыдущая</span>
                </li>
            </c:if>
            <!--
            
            номера страниц
            
            -->
            <!--если текущая страница равна последней и общее чисо страниц больше 2-->
            <c:if test="${currentPageDoc eq pageCountDoc && pageCountDoc >2}">
                <li class="page-item">
                    <!--добавляем кнопку первой страницы, т.к. по иному она не будет отображаться-->
                    <a class="page-link" href="?docPage=${currentPageDoc-2}">${currentPageDoc-2}</a>
                </li>
            </c:if>
            <!--если текущая страница -1 не отрицательная, не ноль и не 1-->
            <c:if test="${currentPageDoc-1>=1}">
                <!--выводим ранее пройденные страницы пользователем-->
                <li class="page-item">
                    <a class="page-link" href="?docPage=${currentPageDoc-1}">${currentPageDoc-1}</a>
                </li>
            </c:if>
            <!--текущая страница, выбранная пользователем, для неё не нужны условия-->
            <li class="page-item active">
                <span class="page-link">${currentPageDoc}</span>
            </li>
            <!--Если вререди есть есть ещё страницы (т.е. если текущая страница меньше общего числа страниц)-->
            <c:if test="${currentPageDoc+1 <= pageCountDoc}">
                <!--выводим ссылку на следующу страницу-->
                <li class="page-item">
                    <a class="page-link" href="?docPage=${currentPageDoc+1}">${currentPageDoc+1}</a>
                </li>
            </c:if>
            <!--Если пользователь на первой странице и страниц больше двух-->
            <c:if test="${currentPageDoc eq 1 && pageCountDoc >2}">
                <li class="page-item">
                    <!--добавляем третью кнопку для перехода по страницам-->
                    <a class="page-link" href="?docPage=${currentPageDoc+2}">${currentPageDoc+2}</a>
                </li>
            </c:if>
            <!--
            
            Кнопка следующая страница
            
            -->
            <!--Если номер текущей страницы меньше обзего числа страниц-->
            <c:if test="${currentPageDoc < pageCountDoc}">
                <!--выводим ссылку на следующу страницу-->
                <li class="page-item">
                    <a class="page-link" href="?docPage=${currentPageDoc+1}">Следующая</a>
                </li>
            </c:if>
                <!--Если пользователь на последней странице или ввёл номер страницы больше общего числа страниц-->
            <c:if test="${currentPageDoc eq pageCountDoc || currentPageDoc>pageCountDoc}">
                <!--отключаем кнопку-->
                <li class="page-item disabled">
                    <span class="page-link">Следующая</span>
                </li>
            </c:if>
        </ul>
    </div>
</div>