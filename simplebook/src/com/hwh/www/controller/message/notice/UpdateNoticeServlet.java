package com.hwh.www.controller.message.notice;

import com.hwh.www.service.FriendService;
import com.hwh.www.service.FriendServiceImpl;
import com.hwh.www.service.NoticeService;
import com.hwh.www.service.NoticeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateNoticeServlet",urlPatterns = "/UpdateNoticeServlet")
public class UpdateNoticeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int noticeId = Integer.parseInt(request.getParameter("noticeId"));
        String choice  = request.getParameter("choice");
        //更新信息
        NoticeService noticeService = new NoticeServiceImpl();
        noticeService.update(noticeId,choice);
        if("接受".equals(choice)){
            //更新好友列表
            FriendService friendService = new FriendServiceImpl();
            friendService.addFriend(noticeId);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
