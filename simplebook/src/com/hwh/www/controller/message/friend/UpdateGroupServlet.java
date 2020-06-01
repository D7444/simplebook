package com.hwh.www.controller.message.friend;

import com.hwh.www.service.FriendService;
import com.hwh.www.service.FriendServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateGroupServlet",urlPatterns = "/UpdateGroupServlet")
public class UpdateGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FriendService friendService = new FriendServiceImpl();
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        String newgroup = request.getParameter("newgroup");
        friendService.updateGroupName(groupId,newgroup);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
