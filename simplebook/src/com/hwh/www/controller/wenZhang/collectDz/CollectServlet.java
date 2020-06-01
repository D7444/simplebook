package com.hwh.www.controller.wenZhang.collectDz;

import com.hwh.www.service.CollectDZService;
import com.hwh.www.service.CollectDZServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CollectServlet",urlPatterns = "/CollectServlet")
public class CollectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int wzid = Integer.parseInt(request.getParameter("wzid"));
        CollectDZService collectDZService = new CollectDZServiceImpl();
        if(collectDZService.judgeCollect(id,wzid)){
            //已经收藏，删除
            collectDZService.deleteCollect(id,wzid);
            response.getWriter().print(0);
        }
        else {
            //未收藏，增加
            collectDZService.addCollect(id,wzid);
            response.getWriter().print(1);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
