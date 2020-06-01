package com.hwh.www.controller.message.friend;

import com.hwh.www.service.FriendService;
import com.hwh.www.service.FriendServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "JudgeFriendServlet",urlPatterns = "/JudgeFriendServlet")
public class JudgeFriendServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int freid = Integer.parseInt(request.getParameter("freid"));
        FriendService friendService = new FriendServiceImpl();
        //返回是否是好友
        response.getWriter().print(friendService.judgeFriend(id,freid));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
