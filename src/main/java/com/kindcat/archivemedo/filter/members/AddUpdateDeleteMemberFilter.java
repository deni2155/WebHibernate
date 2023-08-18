package com.kindcat.archivemedo.filter.members;

import com.kindcat.archivemedo.beans.SuperBean;
import com.kindcat.archivemedo.beans.SuperBeanImpl;
import com.kindcat.archivemedo.members.SuperMemberProcess;
import com.kindcat.archivemedo.members.SuperMemberProcessImpl;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author dreamer
 * @version 1.0.0.23 Выполняет фильтрацию полей при добавлении, редактировании и
 * удалении участников МЭДО
 */
//@WebFilter(filterName = "linkListMembersServlet", urlPatterns = {"/linkListMembersServlet"})
public class AddUpdateDeleteMemberFilter implements Filter {

    private final Logger logger;
    private final SuperMemberProcessImpl memberProcess;

    public AddUpdateDeleteMemberFilter() {
        logger = Logger.getLogger(AddUpdateDeleteMemberFilter.class);
        memberProcess = new SuperMemberProcess();
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        logger.debug("Запущена инициализация фильтра");
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("Выполняется фильтр");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        SuperBeanImpl beans = new SuperBean();
        /*
Если получены атрибуты для добавления нового участника МЭДО
         */
        if (httpRequest.getParameter("nameOrgAddMember") != null && httpRequest.getParameter("emailAddMemberList") != null && httpRequest.getParameter("guidAddMember") != null) {
            beans.setBeanNameOrg(httpRequest.getParameter("nameOrgAddMember"));
            beans.setBeanEmailOrg(httpRequest.getParameter("emailAddMemberList"));
            beans.setBeanGuidOrg(httpRequest.getParameter("guidAddMember"));
            memberProcess.addMemberProcess(session.getAttribute("login").toString(), beans.getBeanNameOrg(), beans.getBeanEmailOrg(), beans.getBeanGuidOrg());
        }
        chain.doFilter(request, response);//пропускаем выполнение запросов для перехода по ссылкам
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
        logger.debug("Фильтр завершил работу");
    }
}
