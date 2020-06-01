package com.hwh.www.controller.follow;

import com.hwh.www.service.FollowService;
import com.hwh.www.service.FollowServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FollowServlet",urlPatterns = "/FollowServlet")
public class FollowServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sub = Integer.parseInt(request.getParameter("sub"));
        int besub = Integer.parseInt(request.getParameter("besub"));
        FollowService followService = new FollowServiceImpl();
        if(sub!=besub) {
            followService = new FollowServiceImpl();
            response.getWriter().print(followService.judgeSub(sub, besub));
        }else{
            response.getWriter().print("");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
