package com.hwh.www.controller.blackList;

import com.hwh.www.service.SignService;
import com.hwh.www.service.SignServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteBlackServlet",urlPatterns = "/DeleteBlackServlet")
public class DeleteBlackServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SignService signService = new SignServiceImpl();
        if(request.getParameter("blackId") != null) {
            int blackId = Integer.parseInt(request.getParameter("blackId"));
            //移出黑名单
            signService.deleteBlack(blackId);
        }else {
            int id = Integer.parseInt(request.getParameter("id"));
            int beid = Integer.parseInt(request.getParameter("beid"));
            //移除黑名单
            signService.deleteBlack(id,beid);
        }



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
