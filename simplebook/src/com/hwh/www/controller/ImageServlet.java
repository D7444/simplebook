package com.hwh.www.controller;

import com.hwh.www.dao.UserDao;
import com.hwh.www.dao.UserDaoImpl;
import com.hwh.www.until.ImageUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.sql.Blob;

@WebServlet(name = "ImageServlet",urlPatterns = "/ImageServlet")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserDao userDao = new UserDaoImpl();
        //获取二进制照片
        Blob blob = userDao.findById(id).getImage();
        //获取servlet流
        ServletOutputStream output = response.getOutputStream();
        try {
            //转化成二进制流
            InputStream input = blob.getBinaryStream();
            //添加照片或覆盖照片
            int len = 0;
            byte[] buff = new byte[1024];
            while ((len = input.read(buff)) != -1) {
                output.write(buff, 0, len);
            }
            output.close();
            input.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
