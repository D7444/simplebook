package com.hwh.www.controller.message.friend;

import com.hwh.www.service.FriendService;
import com.hwh.www.service.FriendServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangeFriendGroupServlet",urlPatterns = "/ChangeFriendGroupServlet")
public class ChangeFriendGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FriendService friendService = new FriendServiceImpl();
        int id = Integer.parseInt(request.getParameter("id"));
        int freid = Integer.parseInt(request.getParameter("freid"));
        String group = request.getParameter("group");
        //更换组
        friendService.changeFriendGroup(id,freid,group);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
