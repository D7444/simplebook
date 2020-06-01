package com.hwh.www.controller.message.message;

import com.hwh.www.service.MessageService;
import com.hwh.www.service.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddMessageServlet",urlPatterns = "/AddMessageServlet")
public class AddMessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int toid = Integer.parseInt(request.getParameter("toid"));
        //增加会话
        MessageService messageService = new MessageServiceImpl();
        messageService.addSession(id,toid,"");
        //跳转窗口
        response.sendRedirect("MessageServlet?id="+id);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
