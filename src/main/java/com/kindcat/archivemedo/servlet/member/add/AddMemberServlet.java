package com.kindcat.archivemedo.servlet.member.add;

import com.kindcat.archivemedo.beans.SuperBean;
import com.kindcat.archivemedo.beans.SuperBeanImpl;
import com.kindcat.archivemedo.members.SuperMemberProcess;
import com.kindcat.archivemedo.members.SuperMemberProcessImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author dreamer
 * @version 1.0.0.1 класс для добавления участников МЭДО
 */
@WebServlet(name = "AddMemberServlet", urlPatterns = {"/addMemberServlet"})
public class AddMemberServlet extends HttpServlet {

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        StringBuilder stringBuilder = new StringBuilder();
//        HttpSession session = request.getSession(false);//получаю текущую сессию
//        response.setContentType("text/html;charset=UTF-8");
        Logger logger = Logger.getLogger(AddMemberServlet.class);
        SuperMemberProcessImpl memberProcess = new SuperMemberProcess();

        HttpSession session = request.getSession(false);
        SuperBeanImpl beans = new SuperBean();
        /*
Если получены атрибуты для добавления нового участника МЭДО
         */
        if (request.getParameter("nameOrgAddMember") != null && request.getParameter("emailAddMemberList") != null && request.getParameter("guidAddMember") != null) {
            beans.setBeanNameOrg(request.getParameter("nameOrgAddMember"));
            beans.setBeanEmailOrg(request.getParameter("emailAddMemberList"));
            beans.setBeanGuidOrg(request.getParameter("guidAddMember"));
            memberProcess.addMemberProcess(session.getAttribute("login").toString(), beans.getBeanNameOrg(), beans.getBeanEmailOrg(), beans.getBeanGuidOrg());
logger.debug("Прошёл");
        }else{
logger.debug("Не прошёл");
}
        /*
        ImplDao superDao = new SuperDao();
        if (request.getParameter("nameOrgMemberListGuides") != null && request.getParameter("emailMemberListGuides") != null && request.getParameter("guidMemberListGuides") != null) {
            String orgName = request.getParameter("nameOrgAddMember");
            String orgEmail = request.getParameter("emailAddMemberList");
            String orgGuid = request.getParameter("guidAddMember");
            //соответствует ли полученная от пользователя информация регулярным вырабжения строки
            boolean verificationRegx = true;
            //если хотя бы одна строка не соответствует регулярному выражения, то проверка не выполнится
            if (!orgName.matches("^[а-яА-Я\\s()]+$")) {
                logger.debug("Не прошла валидация имени организации");
                verificationRegx = false;
            }
            if (!orgEmail.matches("^[A-Z~_\\s]+$")) {
                logger.debug("Не прошла валидация email организации");
                verificationRegx = false;
            }
            if (!orgGuid.matches("^[a-z0-9-]+$")) {
                logger.debug("Не прошла валидация GUID организации");
                verificationRegx = false;
            }
            //если значение переменных соответствует регулярным выражениям
            if (verificationRegx == true) {
                if (superDao.addNewMember(orgName, orgEmail, orgGuid)) {
                    logger.debug("Добавлен новый участника МЭДО с идентификатором "+orgGuid);
                }
                //значение переменных не соответствует регулярным выражениям
            } else {
                logger.info("Пользователь пытался добавить данные участника МЭДО, не соответствующие формату");
            }
        } else {
//            stringBuilder.append("Пользователь отправил запрос на добавление нового участника МЭДО с пустыми поля");
//            stringBuilder.append(session.getAttribute("login"));
//            stringBuilder.append(" отправил запрос на добавление нового участника МЭДО с пустыми поля");
//            logger.warn(" отправил запрос на добавление");
//            String messageForLog = stringBuilder.toString();
            logger.info("Пользователь отправил запрос на добавление нового участника МЭДО с пустыми поля");
        }
//        request.setAttribute("listMembers", superDao.getListMembers());
//        if (superDao.getListMembers().isEmpty()) {
//            logger.info("Получен пустой массив со списком участников МЭДО");
//        }
        request.getRequestDispatcher("linkListMembersServlet").forward(request, response);
         */
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
