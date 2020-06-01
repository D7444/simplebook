package com.hwh.www.controller.follow;

import com.hwh.www.service.FollowService;
import com.hwh.www.service.FollowServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FollowControlServlet",urlPatterns = "/FollowControlServlet")
public class FollowControlServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int sub = Integer.parseInt(request.getParameter("sub"));
        int besub = Integer.parseInt(request.getParameter("besub"));
        String flag = request.getParameter("flag");
        FollowService followService = new FollowServiceImpl();
        if(flag.equals("add")){
            response.getWriter().print(followService.sub(sub,besub));
        }
        else {
            followService.cancelSub(sub,besub);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
