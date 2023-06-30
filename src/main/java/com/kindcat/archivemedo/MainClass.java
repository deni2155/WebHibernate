package com.kindcat.archivemedo;

import com.kindcat.archivemedo.db.dao.ImplDao;
import com.kindcat.archivemedo.db.dao.SuperDao;
import com.kindcat.archivemedo.db.utils.SessionFactoryUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.hibernate.Session;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");//Кодировка отправляемых данных
        //
        //Подключаю логирование
        //
        Logger logger = Logger.getLogger(MainClass.class);
ImplDao userDao=new SuperDao();
logger.debug(userDao.findUserById(1).getFullName());
//
//
//
//Рабоче hibernate
//
//
//
//
//        try{
//            int idUser=0;
//            //создаю сессию пользователя
//            UserSeesionImpl userSession=new UserSession();
//            UserBeansImpl userBeans=new UserBeans();
//            ImplDao userDao=new SuperDao();
//            logger.debug("Создана новая сессия");
//            //получаю логин пользователя
//            userBeans.setUsername(request.getParameter("username"));
//            //ищу идентификатор УЗ по логину в БД
//            idUser=userDao.findUserByLogin(userBeans.getUsername());
//            //если переменная равна 0, то пользователь не найден в таблице
//            if(idUser>0){
//                //получаю пароль, введённый пользователем
//                userBeans.setPassword(request.getParameter("password"));
//                //пароль указан верно
//                if(BCrypt.checkpw(userBeans.getPassword(), userDao.findUserById(idUser).getHash())){
//                    //получаю полное имя пользователя
//                    userBeans.setFname(userDao.findUserById(idUser).getFullName());
//                    userSession.setSession(request.getSession(true));//создаю новую сессию
//                    //отпраданные в сессию
//                    userSession.getSession().setAttribute("idUser", idUser);
//                    userSession.getSession().setAttribute("username", userBeans.getFname());
//                    getServletContext().getRequestDispatcher("/pages/archive.jsp").forward(request, response);
//                //пароль указан не верно
//                }else{
//                    request.setAttribute("message","Пароль указан не верно");
//                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
//                    logger.info(String.format("%s%s%s", "Неудачная попытка авторизации пользователя.\n\t\tПароль пользователя \"",userBeans.getUsername(),"\" указан не верно"));
//                }
//            }
//            //если пользователь не найден
//            else{
//                request.setAttribute("message",String.format("%s%s%s","Пользователь ", userBeans.getUsername(), " не зарегистрирован"));
//                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
//                logger.info(String.format("%s%s%s", "Неудачная попытка авторизации пользователя.\n\t\tПользователь с логином \"",userBeans.getUsername(),"\" не найден в БД"));
//            }
//        }catch( IOException | ServletException ex){
//            logger.fatal("При авторизации произошла программная ошибка",ex);
//        }
//
//
//
//Старое, тестирование
//
//
//
//
            //
            //проверка логина и пароля
            //
            //логин и пароль указан верно
            //if(request.getParameter("username").equals("dreamer") && request.getParameter("pwd").equals("123qwe$$")){


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
    }

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
