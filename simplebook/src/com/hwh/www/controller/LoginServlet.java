package com.hwh.www.controller;

import com.hwh.www.service.SignService;
import com.hwh.www.service.SignServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name="LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        SignService signService = new SignServiceImpl();

        response.setCharacterEncoding("utf-8");
        //判断验证码是否正确，不区分大小写
        if((code.toLowerCase()).equals(String.valueOf(session.getAttribute("pidcode")).toLowerCase())){
            //判断账号密码,返回信息
            response.getWriter().print(signService.loginjudge(email,password,session));
        }else {
            response.getWriter().print("验证码错误");
        }


    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
