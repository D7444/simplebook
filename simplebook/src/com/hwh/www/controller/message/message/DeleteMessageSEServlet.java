package com.hwh.www.controller.message.message;

import com.hwh.www.service.MessageService;
import com.hwh.www.service.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteMessageSEServlet" ,urlPatterns = "/DeleteMessageSEServlet")
public class DeleteMessageSEServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int messageId = Integer.parseInt(request.getParameter("messageId"));
        MessageService messageService = new MessageServiceImpl();
        messageService.deleteSession(messageId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
