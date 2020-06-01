package com.hwh.www.controller;

import com.hwh.www.service.SearchService;
import com.hwh.www.service.SearchServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SearchServlet",urlPatterns = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取搜索关键词
        String search = request.getParameter("search");
        HttpSession session = request.getSession();
        //获取匹配搜索的文章和用户
        SearchService searchService = new SearchServiceImpl();
        session.setAttribute("userSearch", searchService.findUser(search));
        session.setAttribute("wzSearch", searchService.findWz(search));
        //跳转窗口
        response.sendRedirect("search.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
