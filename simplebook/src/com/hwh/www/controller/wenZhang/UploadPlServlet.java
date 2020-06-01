package com.hwh.www.controller.wenZhang;

import com.hwh.www.service.WenZhangService;
import com.hwh.www.service.WenZhangServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UploadPlServlet",urlPatterns = "/UploadPlServlet")
public class UploadPlServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int wzid = Integer.parseInt(request.getParameter("wzid"));
        int id = Integer.parseInt(request.getParameter("id"));
        int fatherid = Integer.parseInt(request.getParameter("fatherid"));
        String content = request.getParameter("content");
        WenZhangService wenZhangService = new WenZhangServiceImpl();
        wenZhangService.addPl(wzid,id,fatherid,content);
        response.sendRedirect("FindWenZhangServlet?wzid="+wzid);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
