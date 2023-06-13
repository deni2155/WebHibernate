package com.kindcat.archivemedo;

import com.kindcat.archivemedo.db.dao.UsersDao;
import com.kindcat.archivemedo.db.services.UsersService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author dreamer
 * @version 0.0.0
 */
@WebServlet(name = "mainClass", urlPatterns = {"/mainClass"})
public class MainClass extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    //protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        //
        //Подключаю логирование
        //
        Logger logger = Logger.getLogger(MainClass.class);
        if (logger.isDebugEnabled()) {
            logger.debug("PageController.process()");
        }
        try{
            //
            //проверка логина и пароля
            //
            //логин и пароль указан верно
            //if(request.getParameter("username").equals("dreamer") && request.getParameter("pwd").equals("123qwe$$")){
                //контекст запроса
                String username=request.getParameter("username");
                UsersService userService=new UsersService();
                //request.setAttribute("username", userDao.findIdUser(1).getFullName());
                request.setAttribute("username",userService.findLogin(username));

                //контекст приложения
    //            ServletContext selvletContext = getServletContext();
    //            selvletContext.setAttribute("name", "Tom");
    //            selvletContext.setAttribute("age", 35);

                //контекст сессии
    //            HttpSession session = request.getSession();
    //            session.setAttribute("name", "Tom");
    //            session.setAttribute("age", 34);

                getServletContext().getRequestDispatcher("/pages/archive.jsp").forward(request, response);
                logger.info("Выполнен вход пользователем"+request.getParameter("username"));
            //логин и пароль указан не верно
            /*}else{
                request.setAttribute("info-ajax-msg-sign-in", "Неверный логин или пароль");
                //response.sendRedirect(request.getContextPath());
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                logger.info("Неудачная попытка авторизации пользователя"+request.getParameter("username"));
            }*/
        }catch( ServletException | IOException ex){
            logger.fatal("Во время авторизации произошла программная ошибка",ex);
        }
        

//        response.sendRedirect("");
//        response.setContentType("application/json");//Отправляем от сервера данные в JSON -формате
//        response.setCharacterEncoding("utf-8");//Кодировка отправляемых данных
//        try (PrintWriter out = response.getWriter()) {
//            JSONObject jsonEnt = new JSONObject();
//            if(request.getParameter("username").equals("dreamer") && request.getParameter("password").equals("123qwe$$"))
//            {
//                //jsonEnt.put("display","block");
//                //jsonEnt.put("serverInfo", "success");
//                //getServletContext().getRequestDispatcher("pages/archive.jsp").forward(request,response);
//                //response.sendRedirect("pages/archive.jsp");
//            }else
//            {
//                //jsonEnt.put("display","block");
//                jsonEnt.put("serverInfo", "Неверный логин или пароль"); 
//            }
//            out.print(jsonEnt.toString());
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.sendRedirect("/pages/archive.jsp");
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
         processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
