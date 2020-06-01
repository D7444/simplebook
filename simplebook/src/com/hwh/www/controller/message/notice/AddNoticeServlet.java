package com.hwh.www.controller.message.notice;

import com.hwh.www.service.NoticeService;
import com.hwh.www.service.NoticeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddNoticeServlet",urlPatterns = "/AddNoticeServlet")
public class AddNoticeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int invite  = Integer.parseInt(request.getParameter("invite"));
        String content = request.getParameter("content");
        int flag  = Integer.parseInt(request.getParameter("flag"));
        NoticeService noticeService = new NoticeServiceImpl();
        noticeService.addNotice(id,invite,content);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
