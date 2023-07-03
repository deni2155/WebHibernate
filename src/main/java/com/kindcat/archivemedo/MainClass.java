package com.kindcat.archivemedo;

import com.kindcat.archivemedo.db.dao.ImplDao;
import com.kindcat.archivemedo.db.dao.SuperDao;
import com.kindcat.archivemedo.signin.sessions.CurrentSession;
import com.kindcat.archivemedo.signin.sessions.CurrentSessionImpl;
import java.io.IOException;
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
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");//Кодировка отправляемых данных
        //
        //Подключаю логирование
        //
        Logger logger = Logger.getLogger(MainClass.class);

        int idUser = 0;
        try {
            CurrentSessionImpl currentSession = new CurrentSession();
            currentSession.setSession(request.getSession(false));//получаю из запроса информацию о текущей сессии
            ImplDao userDao = new SuperDao();
            //
            //Существующая сессия ищется в JSP index
            //
            //если сессия не существует
            if (!currentSession.isExistsSession()) {
                currentSession.setSession(request.getSession());//создаю новую сессию
                String login = request.getParameter("login");
                idUser = userDao.findUserInLoginByLogin(login);//ищу пользователя по логину в БД
                if (idUser > 0) {
                    logger.debug("При авторизации в БД успешно найдена запись пользователя с логином \"" + login + "\"");
                    //если пароль указан верно
                    if (BCrypt.checkpw(request.getParameter("password"), userDao.findUserById(idUser).getHash())) {
                        logger.info("Выполнена успешная авторизация пользователя \"" + login + "\"");
                        //информацию из запроса записываем в сессию
                        currentSession.setIdUser(idUser);
                        currentSession.setLoginUser(login);
                        currentSession.setFNameUser(request.getParameter("fName"));
                        request.getRequestDispatcher("/pages/archive.jsp").forward(request, response);
                    } else {
                        logger.info("Пользователем \"" + login + "\" указан не верный пароль");
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    }
                }
            }
        } catch (IOException | ServletException ex) {
            logger.error("Произошла программная ошибка при авторизации пользователя \"" + request.getParameter("login"));
        }

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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
