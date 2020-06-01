package com.hwh.www.controller.blackList;

import com.hwh.www.service.SignService;
import com.hwh.www.service.SignServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BanUserServlet",urlPatterns = "/BanUserServlet")
public class BanUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int banChoice = Integer.parseInt(request.getParameter("banChoice"));
        System.out.println(id+"|"+banChoice);
        //修改期限
        SignService signService = new SignServiceImpl();
        String time = signService.banUser(id,banChoice);
        //发回期限
        response.getWriter().print(time);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
