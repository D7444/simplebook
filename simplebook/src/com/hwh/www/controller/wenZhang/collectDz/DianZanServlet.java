package com.hwh.www.controller.wenZhang.collectDz;

import com.hwh.www.service.CollectDZService;
import com.hwh.www.service.CollectDZServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hx25
 */
@WebServlet(name = "DianZanServlet",urlPatterns = "/DianZanServlet")
public class DianZanServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int wzid = Integer.parseInt(request.getParameter("wzid"));
        int plid = Integer.parseInt(request.getParameter("plid"));
        int beid = Integer.parseInt(request.getParameter("beid"));
        CollectDZService collectDZService = new CollectDZServiceImpl();
        int msg = collectDZService.DZcontrol(wzid,id,plid,beid);
        response.getWriter().print(msg);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
