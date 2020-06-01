package com.hwh.www.controller.message.notice;

import com.hwh.www.service.NoticeService;
import com.hwh.www.service.NoticeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteNoticeServlet",urlPatterns = "/DeleteNoticeServlet")
public class DeleteNoticeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int noticeId = Integer.parseInt(request.getParameter("noticeId"));
        NoticeService noticeService = new NoticeServiceImpl();
        noticeService.deleteNotice(noticeId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
