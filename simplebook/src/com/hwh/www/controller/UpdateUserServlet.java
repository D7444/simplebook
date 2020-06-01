package com.hwh.www.controller;

import com.hwh.www.po.User;
import com.hwh.www.service.SignService;
import com.hwh.www.service.SignServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UpdateUserServlet",urlPatterns = "/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取用户id和图片路径
        int id = Integer.parseInt(request.getParameter("id"));
        String path = request.getParameter("file");
        String email = request.getParameter("email");
        String uname = request.getParameter("uname");
        System.out.println("测试"+email+path);
        SignService signService = new SignServiceImpl();
        //报错信息
        String msg = null;
        request.getSession().setAttribute("userUpdateMsg","");
        if(path.equals("")){
            //更新用户信息
            msg = signService.updateUser(id,uname,email);
            if(msg.equals("修改成功")) {
                //修改现有用户信息
                User ownUser = (User) request.getSession().getAttribute("ownUser");
                ownUser.setUname(uname);
                ownUser.setEmail(email);
                request.getSession().setAttribute("ownUser", ownUser);
            }
            else {
                request.getSession().setAttribute("userUpdateMsg",msg);
            }
        }
        else {
            //更新头像
            signService.updateImage(id,path);
        }
        response.sendRedirect("option.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
