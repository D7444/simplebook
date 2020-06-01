package com.hwh.www.controller.addWz;

import com.hwh.www.service.WenZhangService;
import com.hwh.www.service.WenZhangServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UploadWzServlet",urlPatterns = "/UploadWzServlet")
public class UploadWzServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String breif = request.getParameter("breif");
        String content = request.getParameter("content");
        String type = request.getParameter("type");
        int id = Integer.parseInt(request.getParameter("id"));
        //上传文章
        WenZhangService wenZhangService = new WenZhangServiceImpl();
        wenZhangService.addWz(title,breif,content,type,id);
        //跳转到首页，看到最新文章
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
