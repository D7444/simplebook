package com.hwh.www.controller.message;

import com.hwh.www.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MessageServlet",urlPatterns = "/MessageServlet")
public class MessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户ID
        int id = Integer.parseInt(request.getParameter("id"));

        HttpSession session= request.getSession();

        /*用户通知*/
        NoticeService noticeService = new NoticeServiceImpl();
        //发送通知列表
        session.setAttribute("notice",noticeService.getNotice(id));
        //收到通知列表
        session.setAttribute("benotice", noticeService.getBeNotice(id));

        /*黑名单*/
        SignService signService = new SignServiceImpl();
        session.setAttribute("blacklist",signService.getBlack(id));

        /*获取简信*/
        MessageService messageService = new MessageServiceImpl();
        //简信列表
        session.setAttribute("messagelist",messageService.getUser(id));
        //简信内容
        session.setAttribute("message",messageService.getMessage());

        /*好友*/
        FriendService friendService = new FriendServiceImpl();
        //好友自定义列表
        session.setAttribute("friendListName",friendService.getGroup(id));
        //获取个人总好友列表
        session.setAttribute("friendList",friendService.getFriend(id));


        /*跳转窗口*/
        response.sendRedirect("message.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
