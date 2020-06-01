package com.hwh.www.controller.wenZhang.collectDz;

import com.hwh.www.service.CollectDZService;
import com.hwh.www.service.CollectDZServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetCollectServlet",urlPatterns = "/GetCollectServlet")
public class GetCollectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CollectDZService collectDZService = new CollectDZServiceImpl();
        request.getSession().setAttribute("userCl",collectDZService.getCollect(id));
        response.sendRedirect("collect.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
