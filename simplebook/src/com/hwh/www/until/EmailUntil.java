package com.hwh.www.until;

import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;
import javax.activation.*;

public class EmailUntil {
    //邮箱信息
    private static String myEmailAccount = "hwh744@163.com";
    private static String myEmailPassword = "OUXSRNFWBEJTNOQG";
    private static String myEmailSMTPHost = "smtp.163.com";

    /*发送邮件*/
    public static void sendEmail(String email,String content,String title) throws Exception{
        // 1 . 用于连接邮件服务器的参数配置
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        properties.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        properties.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        // 2 . 根据参数配置，创建会话对象
        Session session = Session.getDefaultInstance(properties);
//        session.setDebug(true);         //设置为DEBUG模式，可以查看详细的发送log

        // 3 . 创建一封邮件
        MimeMessage message = creatEmail(session,email,content,title);

        // 4 . 根据Session 获取邮件传输对象
        Transport transport = session.getTransport();

        // 5 . 连接邮件服务器
        transport.connect(myEmailAccount,myEmailPassword);

        // 6 . 发送邮件
        transport.sendMessage(message,message.getAllRecipients());

        // 7 . 关闭连接
        transport.close();

    }

    /*创建邮件*/
    private static MimeMessage creatEmail(Session session,String email,String content,String title) throws Exception{
        // 1 . 创建邮件对象
        MimeMessage message = new MimeMessage(session);

        // 2 . 发件人
        message.setFrom(new InternetAddress(myEmailAccount,"简书-客服","UTF-8"));

        // 3.收件人
        message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(email,"简书-验证码","UTF-8"));

        // 4 . 邮件主题
        message.setSubject(title,"UTF-8");

        // 5 . 邮件正文
        message.setContent(content,"text/html;charset=UTF-8");

        // 6 . 设置显示的发件时间
        message.setSentDate(new Date());

        //7.保存前面的设置
        message.saveChanges();

        return message;
    }

    public static void main(String args[]){
        try {
            sendEmail("2560772882@qq.com","简书-验证码","简书-验证码");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
