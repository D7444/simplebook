package com.hwh.www.controller.message.message;

import com.hwh.www.service.MessageService;
import com.hwh.www.service.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SendMessageServlet",urlPatterns = "/SendMessageServlet")
public class SendMessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int messageId = Integer.parseInt(request.getParameter("messageId"));
        int id = Integer.parseInt(request.getParameter("id"));
        int toid = Integer.parseInt(request.getParameter("toid"));
        String content = request.getParameter("content");
        MessageService messageService = new MessageServiceImpl();
        String result = messageService.sendMessage(messageId,id,toid,content);
        response.getWriter().print(result);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
