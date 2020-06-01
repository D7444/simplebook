package com.hwh.www.controller.wenZhang.hot;

import com.hwh.www.service.WenZhangService;
import com.hwh.www.service.WenZhangServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SetHotServlet",urlPatterns = "/SetHotServlet")
public class SetHotServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int wzid = Integer.parseInt(request.getParameter("wzid"));
        WenZhangService wenZhangService = new WenZhangServiceImpl();
        wenZhangService.setHot(wzid);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
