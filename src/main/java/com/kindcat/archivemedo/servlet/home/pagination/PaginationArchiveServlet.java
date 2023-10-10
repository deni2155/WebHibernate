package com.kindcat.archivemedo.servlet.home.pagination;

//import com.google.gson.Gson;
import com.kindcat.archivemedo.db.dao.ImplDao;
import com.kindcat.archivemedo.db.dao.SuperDao;
import java.io.IOException;
//import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author dreamer
 * @version 1.0.3.39 Класс для отображения информации о квитанциях, уведомления,
 * документа
 */
@WebServlet(name = "PaginationArchiveServlet", urlPatterns = {"/paginationArchiveServlet"})
public class PaginationArchiveServlet extends HttpServlet {

    private final Logger logger;
    private final ImplDao dao;
//    private final StringBuilder logBuilder;
//    private StackTraceElement[] stackTrace;
//    private String stringlog;
//    private Gson gson;

    public PaginationArchiveServlet() {
        logger = Logger.getLogger(PaginationArchiveServlet.class);
        dao = new SuperDao();
    }

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String link = "linkArchiveServlet";

        //
        ///массив с записями о типе пакета (входящий или исходящий) для отображения на странице
        //
        request.setAttribute("listTypePkg", dao.getAllListTypePkg());
        if (dao.getAllListTypePkg().isEmpty()) {
            logger.warn("Получен пустой массив со списком типов пакетов (входящий\\исходящий)");
        }
        //
        //массив с записями схем xml для отображения на странице
        //
        request.setAttribute("listSchemaXml", dao.getAllListSchemaXml());
        if (dao.getAllListSchemaXml().isEmpty()) {
            logger.warn("Получен пустой массив со списком схем xml");
        }
        //
        //
        //Документы
        //
        //
        Short idDocTypePkg = 1;//указываю идентификатор типа пакета по умолчанию - входящие

        int docsCountForOnePage = 20;//число записей на одной странице
        int countDocs = 0;//общее число записей в БД
        int pageDocsCount = 1;//число страниц через деление общего числа записей в БД на число записей на одной странице
        int currentPageDocs = 1;//выбранная страинца для расчёта пагинации
        int skipEnties = 0;//число пропущенных записей
        //если пользователь изменил тип пакета, присваиваем полученное значение переменной
        if (request.getParameter("docInOut") != null) {
            idDocTypePkg = Short.parseShort(request.getParameter("docInOut"));
        }

        countDocs = Math.toIntExact(dao.getAllCountDocs(idDocTypePkg));//общее число записей в БД
        pageDocsCount = countDocs / docsCountForOnePage;//получаю число страниц через деление общего числа записей в БД на число записей на одной странице
        //если остаток от деления больше нуля при делении общего числа записей на число записей на одной странице, добавляем одну страницу
        if ((countDocs % docsCountForOnePage) > 0) {
            pageDocsCount++;
        }

        if (request.getParameter("page") != null) {
            currentPageDocs = Integer.parseInt(request.getParameter("page"));
        }

        //если от пользователя получен номер страницы, превышающий общее число страниц
        if (currentPageDocs > pageDocsCount) {
            currentPageDocs = pageDocsCount;//отправляем пользователя на последнюю страницу
        }//если от пользователя получен номер страницы меньше нуля, отпавляем на персую страницу
        if (currentPageDocs < 1) {
            currentPageDocs = 1;
        }

        skipEnties = docsCountForOnePage * (currentPageDocs - 1);//число пропущенных записей

        //
        //выводим список документов
        //
        request.setAttribute("listDocs", dao.getAllListDocsByTypePkg(idDocTypePkg, skipEnties, docsCountForOnePage));
        request.setAttribute("pageCount", pageDocsCount);//число странице для отображения
        request.setAttribute("currentPage", currentPageDocs);//текущая страница

        if (dao.getAllListDocsByTypePkg(idDocTypePkg, skipEnties, docsCountForOnePage).isEmpty()) {
            logger.warn("Получен пустой массив со списком документов");
        }
//
//
//Уведомления
//
//
        request.getRequestDispatcher(link).forward(request, response);
    }

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
