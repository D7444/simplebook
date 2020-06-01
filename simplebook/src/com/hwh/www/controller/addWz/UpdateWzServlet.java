package com.hwh.www.controller.addWz;

import com.hwh.www.service.WenZhangService;
import com.hwh.www.service.WenZhangServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateWzServlet",urlPatterns = "/UpdateWzServlet")
public class UpdateWzServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int wzid = Integer.parseInt(request.getParameter("wzid"));
        String title = request.getParameter("title");
        String breif = request.getParameter("breif");
        String content = request.getParameter("content");
        //上传更新的内容
        WenZhangService wenZhangService = new WenZhangServiceImpl();
        wenZhangService.updateWz(wzid,title,breif,content);
        //跳转到更新后的文章
        response.sendRedirect("FindWenZhangServlet?wzid="+wzid);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
