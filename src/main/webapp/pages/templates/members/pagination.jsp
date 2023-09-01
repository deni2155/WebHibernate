<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--
Пагинация
-->
<div class="col-12">
    <div class="d-flex justify-content-center">
        <ul class="pagination">
            <c:if test="${currentPage eq pageCount && pageCount >2}">
                <li class="page-item" aria-current="page">
                    <!--добавляем сслку для перехода текущая страница+1, что бы на первой странице не отображалось только две кнопки пагинации-->
                    <a class="page-link" href="?page=${currentPage-2}">${currentPage-2}</a>
                </li>
            </c:if>
            <!--если текущая страница -1 не отрицательная, не ноль и не 1-->
            <c:if test="${currentPage-1>=1}">
                <!--выводим ранее пройденные страницы пользователем-->
                <li class="page-item" aria-current="page">
                    <a class="page-link" href="?page=${currentPage-1}">${currentPage-1}</a>
                </li>
            </c:if>
            <!--текущая страница, выбранная пользователем, для неё не нужны условия-->
            <li class="page-item active" aria-current="page">
                <a class="page-link" href="#">${currentPage}</a>
            </li>
            <!--Если текущая страница+1 меньше либо равно общему числу страниц (т.е. следующая страница текущая страница меньше общего числа страниц)-->
            <c:if test="${currentPage+1 <= pageCount}">
                <!--выводим ссылку на следующу страницу-->
                <li class="page-item" aria-current="page">
                    <a class="page-link" href="?page=${currentPage+1}">${currentPage+1}</a>
                </li>
            </c:if>
            <!--Если пользователь на первой странице и страниц больше двух-->
            <c:if test="${currentPage eq 1 && pageCount >2}">
                <li class="page-item" aria-current="page">
                    <!--добавляем сслку для перехода текущая страница+1, что бы на первой странице не отображалось только две кнопки пагинации-->
                    <a class="page-link" href="?page=${currentPage+2}">${currentPage+2}</a>
                </li>
            </c:if>


            <!--если общее число страниц больше 1-->
            <!--c:forEach var="pageForCurrent" begin="{currentPage-1}" end="{currentPage+1}">
                <li class="page-item">
                    <a href="?page={pageForCurrent}">{pageForCurrent}</a>
                </li>
            <!--/c:forEach-->
        </ul>

        <!--ul class="pagination">
            <cforEach var="pageForCurrent" begin="1" end="{pageCount}">
                <li class="page-item">
                    <a href="?page={pageForCurrent}">{pageForCurrent}</a>
                </li>
            </cforEach>
        </ul-->


        <!--ul class="pagination">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Предыдущая</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item active" aria-current="page">
                <a class="page-link" href="#">2</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
                <a class="page-link" href="#">Следующая</a>
            </li>
        </ul-->
    </div>
</div>