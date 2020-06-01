package com.hwh.www.controller.wenZhang;

import com.hwh.www.bean.Page;
import com.hwh.www.po.WenZhang;
import com.hwh.www.service.SignService;
import com.hwh.www.service.SignServiceImpl;
import com.hwh.www.service.WenZhangService;
import com.hwh.www.service.WenZhangServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "WenZhangServlet",urlPatterns = "/WenZhangServlet")
public class WenZhangServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int start = Integer.parseInt(request.getParameter("start"));

        WenZhangService wenZhangService = new WenZhangServiceImpl();
        HttpSession session = request.getSession();


        //获取文章页数
        Page page = wenZhangService.dividePage();
        page.setCurrentPage(start);
        session.setAttribute("pageTotal", page);

        //遍历文章(分页文章)
        session.setAttribute("wenZhang",wenZhangService.getWenZhang(page.getCurrentPage(),page.getEveryCount()));


        //热门文章
        session.setAttribute("wzHot",wenZhangService.findHot());
        //推荐作者
        SignService signService = new SignServiceImpl();
        session.setAttribute("recUser",signService.getLove());

        response.sendRedirect("index.jsp?a=true");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
