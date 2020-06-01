package com.hwh.www.controller.wenZhang.collectDz;

import com.alibaba.fastjson.JSONObject;
import com.hwh.www.service.CollectDZService;
import com.hwh.www.service.CollectDZServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CollectDZServlet",urlPatterns = "/CollectDZServlet")
public class CollectDZServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int wzid = Integer.parseInt(request.getParameter("wzid"));
        int plid = Integer.parseInt(request.getParameter("plid"));
        JSONObject object = new JSONObject();
        CollectDZService collectDZService = new CollectDZServiceImpl();
        object.put("dianzan",collectDZService.judgeDz(id,wzid,plid));
        object.put("collect",collectDZService.judgeCollect(id,wzid));
        response.getWriter().print(object);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
