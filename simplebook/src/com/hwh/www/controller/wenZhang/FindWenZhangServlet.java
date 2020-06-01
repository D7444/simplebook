package com.hwh.www.controller.wenZhang;

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

@WebServlet(name = "FindWenZhangServlet",urlPatterns = "/FindWenZhangServlet")
public class FindWenZhangServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取文章id
        int wzid = Integer.parseInt(request.getParameter("wzid"));
        HttpSession session = request.getSession();
        //获取该id对应的内容
        WenZhangService wenZhangService = new WenZhangServiceImpl();
        //获取文章
        WenZhang wenZhang = wenZhangService.findWz(wzid);
        session.setAttribute("wznow", wenZhang);
        //获取相关文章
        session.setAttribute("wztype", wenZhangService.getWzType(wenZhang.getType()));
        //获取分享地址
        session.setAttribute("share",request.getRequestURL()+"?wzid="+wzid);

        /*获取评论*/
        //一级评论
        session.setAttribute("plnow1",wenZhangService.findPl(wzid,0));
        //二级评论
        session.setAttribute("plnow2",wenZhangService.findPl(wzid,1));
        //跳转页面
        response.sendRedirect("wenZhang.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
