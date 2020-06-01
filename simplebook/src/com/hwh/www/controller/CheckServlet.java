package com.hwh.www.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "CheckServlet",urlPatterns = "/CheckServlet")
public class CheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*生成图片验证码*/
        //创建图片缓冲区
        BufferedImage image = new BufferedImage(100, 30, BufferedImage.TYPE_3BYTE_BGR);
        //缓冲区创建画布
        Graphics g = image.getGraphics();
        //设置背景颜色
        g.setColor(Color.gray);
        //创建画布矩形
        g.fillRect(0,0,200,50);
        //创建随机对象
        Random random = new Random();
        //存放随机数
        int number;
        //存放验证码
        StringBuffer buffer = new StringBuffer();
        //循环产生4个字
        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        for(int i=0;i<4;i++){
            //随机字符
            number= random.nextInt(ch.length);
            //字体
            g.setFont(new Font("", 30, 20));
            //随机颜色
            g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            //画字
            g.drawString(ch[number]+"",(i*20)+2,23);
            //保存
            buffer.append(ch[number]);
        }
        request.getSession().setAttribute("pidcode",buffer.toString());
        ImageIO.write(image,"jpg",response.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
