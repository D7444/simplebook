package com.hwh.www.service;

import com.hwh.www.bean.Dynamic;
import com.hwh.www.po.Black;
import com.hwh.www.po.User;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public interface SignService {
    /*登录判断*/
    public String loginjudge(String email, String password, HttpSession session);

    /*判断是否是邮箱地址*/
    public boolean judgeEmail(String email);

    /*注册新用户*/
    public String registerUser(String uname,String email,String password);

    /*发送找回密码的验证码*/
    public String sendBackCode(String email);

    /*修改密码*/
    public void changePassword(String email,String password);

    /*查询用户*/
    public User findUser(int id);

    /*上传照片*/
    public void updateImage(int id,String path);

    /*修改信息*/
    public String updateUser(int id,String uname,String email);

    /*获取黑名单*/
    public List<Black> getBlack(int id);

    /*加入黑名单*/
    public void addBlack(int id,int beid);

    /*移出黑名单1*/
    public void deleteBlack(int blackId);

    /*移出黑名单2*/
    public void deleteBlack(int id,int beid);

    /*判断(beid)是否处于(id)黑名单*/
    public boolean judgeBlack(int id,int beid);

    /*获取喜欢的用户*/
    public List<User> getLove();

    /*封禁账号*/
    public String banUser(int id,int banChoice);

    /*取消封禁*/
    public void cancelUser(int id);

    /*获取用户动态*/
    public List<Dynamic> getDynamic(int id);
}
