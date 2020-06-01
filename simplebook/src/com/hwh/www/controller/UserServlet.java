package com.hwh.www.controller;

import com.hwh.www.po.WenZhang;
import com.hwh.www.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserServlet",urlPatterns = "/UserServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户id
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        //获取用户信息
        SignService signService = new SignServiceImpl();
        session.setAttribute("user",signService.findUser(id));
        //获取用户动态
        session.setAttribute("dynamic",signService.getDynamic(id));
        //获取用户文章信息
        WenZhangService wenZhangService = new WenZhangServiceImpl();
        session.setAttribute("userWz", wenZhangService.findMore(id));
        //获取用户评论的文章
        session.setAttribute("userPlWz",wenZhangService.foundPlWz(id));
        //获取关注列表，粉丝列表的详细信息
        FollowService followService = new FollowServiceImpl();
        session.setAttribute("sub",followService.getSub(id));
        session.setAttribute("fan",followService.getFan(id));

        //跳转展示
        response.sendRedirect("user.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
