package com.hwh.www.controller.chatshow;

import com.hwh.www.service.ChatService;
import com.hwh.www.service.ChatServiceImpl;
import com.hwh.www.service.FriendService;
import com.hwh.www.service.FriendServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ChatServlet",urlPatterns = "/ChatServlet")
public class ChatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int toid = Integer.parseInt(request.getParameter("toid"));
        HttpSession session = request.getSession();
        //获取聊天者名字
        session.setAttribute("chatName",request.getParameter("chatName"));
        System.out.println(request.getParameter("chatName"));
        session.setAttribute("chatId",toid);
        //获取聊天列表
        FriendService friendService = new FriendServiceImpl();
        session.setAttribute("friendList",friendService.getTogether(id));
        //获取聊天记录
        ChatService chatService = new ChatServiceImpl();
        session.setAttribute("chatList",chatService.getChat(id,toid));

        //跳转窗口
        response.sendRedirect("chat.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
