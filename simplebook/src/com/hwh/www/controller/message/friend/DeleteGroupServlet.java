package com.hwh.www.controller.message.friend;

import com.hwh.www.service.FriendService;
import com.hwh.www.service.FriendServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteGroupServlet",urlPatterns = "/DeleteGroupServlet")
public class DeleteGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        int id = Integer.parseInt(request.getParameter("id"));
        String group = request.getParameter("group");
        FriendService friendService = new FriendServiceImpl();
        friendService.deleteGroup(groupId,id,group);
        response.sendRedirect("MessageServlet?id="+id);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
