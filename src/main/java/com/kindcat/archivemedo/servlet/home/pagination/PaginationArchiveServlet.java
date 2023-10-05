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
//    private final StringBuilder logBuilder;
//    private StackTraceElement[] stackTrace;
//    private String stringlog;
//    private Gson gson;

    public PaginationArchiveServlet() {
        logger = Logger.getLogger(PaginationArchiveServlet.class);
//        logBuilder = new StringBuilder();
//        gson = new Gson();
    }

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String link = "linkArchiveServlet";
        Short idTypePkg = 1;//указываю идентификатор типа пакета по умолчанию - входящие
        ImplDao dao = new SuperDao();
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
//
//
//
        //если пользователь изменил тип пакета, присваиваем полученное значение переменной
        if (request.getParameter("docInOut") != null) {
            idTypePkg = Short.parseShort(request.getParameter("docInOut"));
        }

////        
////        Если не получены параметры от пользователя
////        
//        if (request.getParameter("selectedTypePkgValue") != null) {
//            idTypePkg = Short.parseShort(request.getParameter("selectedTypePkgValue"));
//            String json = gson.toJson(dao.getAllListByTypePkg(idTypePkg));
//            logger.debug(json);
//            try (PrintWriter out = response.getWriter()) {
//                response.setContentType("application/json");
//                response.setCharacterEncoding("UTF-8");
//                out.print(json);
//                out.flush();
//            } catch (Exception ex) {
//                logBuilder.setLength(0);
//                logBuilder.append("При выборе типа документа (входящий или исходящий) произошла программная ошибка");
//                logBuilder.append("\n");
//                logBuilder.append(ex.getMessage());
//                logBuilder.append("\n");
//                stackTrace = ex.getStackTrace();
//                for (StackTraceElement element : stackTrace) {
//                    logBuilder.append(element.toString());
//                    logBuilder.append("\n");
//                }
//                stringlog = logBuilder.toString();
//                logger.error(stringlog);
//            }
//        }

        //
        //выводим список документов
        //
        request.setAttribute("listDocs", dao.getAllListByTypePkg(idTypePkg));
        if (dao.getAllListByTypePkg(idTypePkg).isEmpty()) {
            logger.warn("Получен пустой массив со списком документов");
        }
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
