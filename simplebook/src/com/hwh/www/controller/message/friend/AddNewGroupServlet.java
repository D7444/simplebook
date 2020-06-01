package com.hwh.www.controller.message.friend;

import com.hwh.www.service.FriendService;
import com.hwh.www.service.FriendServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddNewGroupServlet",urlPatterns = "/AddNewGroupServlet")
public class AddNewGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FriendService friendService = new FriendServiceImpl();
        int id = Integer.parseInt(request.getParameter("id"));
        String group = request.getParameter("group");
        //增加新的组
        friendService.addGroup(id,group);
        //跳转窗口
        response.sendRedirect("MessageServlet?id="+id);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
