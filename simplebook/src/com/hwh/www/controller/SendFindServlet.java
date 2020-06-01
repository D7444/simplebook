package com.hwh.www.controller;

import com.hwh.www.dao.UserDao;
import com.hwh.www.service.SignService;
import com.hwh.www.service.SignServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SendFindServlet",urlPatterns = "/SendFindServlet")
public class SendFindServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        //发送验证码
        SignService signService = new SignServiceImpl();
        String backCode = signService.sendBackCode(email);
        //返回验证码
        response.getWriter().print(backCode);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
