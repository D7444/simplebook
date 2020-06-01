package com.hwh.www.controller.wenZhang;

import com.hwh.www.service.WenZhangService;
import com.hwh.www.service.WenZhangServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteWzServlet",urlPatterns = "/DeleteWzServlet")
public class DeleteWzServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取文章id
        int wzid = Integer.parseInt(request.getParameter("wzid"));
        //删除文章
        WenZhangService wenZhangService = new WenZhangServiceImpl();
        wenZhangService.deleteWz(wzid);
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
