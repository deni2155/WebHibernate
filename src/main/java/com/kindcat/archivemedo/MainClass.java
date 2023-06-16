package com.kindcat.archivemedo;

import com.kindcat.archivemedo.db.dao.ImplDao;
import com.kindcat.archivemedo.db.dao.SuperDao;
import com.kindcat.archivemedo.db.services.UsersService;
import java.io.IOException;
import java.io.PrintWriter;
import static java.util.Objects.hash;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

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
        response.setCharacterEncoding("utf-8");//Кодировка отправляемых данных
        //
        //Подключаю логирование
        //
        Logger logger = Logger.getLogger(MainClass.class);
        try{
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            //если заполнены логин и пароль
            if(!username.isEmpty() && !password.isEmpty()){
                //ищу идентификатор пользователя в таблице
                ImplDao userDao=new SuperDao();
                int idUser=userDao.findUserByLogin(username);//присваиваю идентификатор пользователя переменной
                //если переменная равна 0, то пользователь не найден в таблице
                if(idUser>0){
                    if(BCrypt.checkpw(password, userDao.findUserById(idUser).getHash())){
                        request.setAttribute("idUser", userDao.findUserById(idUser).getHash());
                        getServletContext().getRequestDispatcher("/pages/archive.jsp").forward(request, response);
                    }else{
                        request.setAttribute("message","Пароль указан не верно");
                        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                        logger.info(String.format("%s%s%s", "Неудачная попытка авторизации пользователя.\n\t\tПароль пользователя \"",username,"\" указан не верно"));
                    }
                }
                //если пользователь не найден
                else{
                    request.setAttribute("message",String.format("%s%s%s","Пользователь ", username, " не зарегистрирован"));
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                    logger.info(String.format("%s%s%s", "Неудачная попытка авторизации пользователя.\n\t\tПользователь с логином \"",username,"\" не найден в БД"));
                }
            }
            //если логин или пароль не указаны
            else if(username.isEmpty() || password.isEmpty()){
                request.setAttribute("message","Заполните все поля");
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                logger.info("Неудачная попытка авторизации пользователя.\n\t\tПользователем заполнены не все поля формы авторизации");
            }
        }catch(ServletException | IOException ex){
            logger.fatal("При авторизации произошла программная ошибка",ex);
        }

            //
            //проверка логина и пароля
            //
            //логин и пароль указан верно
            //if(request.getParameter("username").equals("dreamer") && request.getParameter("pwd").equals("123qwe$$")){
                //контекст запроса
                //String username=request.getParameter("username");
                //ImplDao userService=new SuperDao();
                //request.setAttribute("username", userDao.findIdUser(1).getFullName());
                //request.setAttribute("username",userService.findUserByLogin(username));

                //контекст приложения
    //            ServletContext selvletContext = getServletContext();
    //            selvletContext.setAttribute("name", "Tom");
    //            selvletContext.setAttribute("age", 35);

                //контекст сессии
    //            HttpSession session = request.getSession();
    //            session.setAttribute("name", "Tom");
    //            session.setAttribute("age", 34);

                //getServletContext().getRequestDispatcher("/pages/archive.jsp").forward(request, response);
                //logger.info("Выполнен вход пользователем"+request.getParameter("username"));
            //логин и пароль указан не верно
            /*}else{
                request.setAttribute("info-ajax-msg-sign-in", "Неверный логин или пароль");
                //response.sendRedirect(request.getContextPath());
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                logger.info("Неудачная попытка авторизации пользователя"+request.getParameter("username"));
            }*/
//        }catch( ServletException | IOException ex){
//            logger.fatal("Во время авторизации произошла программная ошибка",ex);
//        }
        

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
