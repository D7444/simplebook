package com.hwh.www.controller.wenZhang;

import com.alibaba.fastjson.JSONObject;
import com.hwh.www.po.WenZhang;
import com.hwh.www.service.WenZhangService;
import com.hwh.www.service.WenZhangServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ChangWzServlet",urlPatterns = "/ChangWzServlet")
public class ChangWzServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //获取文章id
        int wzid = Integer.parseInt(request.getParameter("wzid"));
        HttpSession session = request.getSession();
        //获取该id对应的内容
        WenZhangService wenZhangService = new WenZhangServiceImpl();
        //获取文章
        WenZhang wenZhang = wenZhangService.findWz(wzid);
        JSONObject json = new JSONObject();
        json.put("title",wenZhang.getTitle());
        json.put("type",wenZhang.getType());
        json.put("content",wenZhang.getContent());
        response.getWriter().print(json);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
