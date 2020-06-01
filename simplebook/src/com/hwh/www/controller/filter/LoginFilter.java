package com.hwh.www.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter",
        urlPatterns = {"/CollectDZServlet","/CollectServlet","/FollowControlServlet",
                "/UploadPlServlet","/FollowControlServlet","/addStartServlet","/AddNoticeServlet","/AddMessageServlet"})
public class LoginFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        if(request.getSession().getAttribute("ownUser") != null){
            chain.doFilter(req, resp);
        }
        else {
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendRedirect("login.jsp");
        }


    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

}
