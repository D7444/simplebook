package com.hwh.www.controller.report;

import com.hwh.www.service.ReportService;
import com.hwh.www.service.ReportServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReportServlet",urlPatterns = "/ReportServlet")
public class ReportServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReportService reportService = new ReportServiceImpl();
        request.getSession().setAttribute("reportlist",reportService.getReport());
        response.sendRedirect("report.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
