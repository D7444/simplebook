package com.hwh.www.controller.report;

import com.hwh.www.service.ReportService;
import com.hwh.www.service.ReportServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddReportServlet",urlPatterns = "/AddReportServlet")
public class AddReportServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int beid = Integer.parseInt(request.getParameter("beid"));
        String type = request.getParameter("type");
        String content = request.getParameter("content");
        System.out.println(id+"|"+beid+"|"+content);
        ReportService reportService = new ReportServiceImpl();
        //添加到数据库
        reportService.addReport(id,beid,type,content);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
