package com.hwh.www.controller.chatshow;

import com.hwh.www.service.FollowService;
import com.hwh.www.service.FollowServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "JudgeTogetherServlet",urlPatterns = "/JudgeTogetherServlet")
public class JudgeTogetherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int toid = Integer.parseInt(request.getParameter("toid"));

        FollowService followService = new FollowServiceImpl();
        //返回是否互相关注
        response.getWriter().print(followService.judgeTogether(id,toid));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
