package com.hwh.www.po;

import java.sql.Blob;

public class User {
    //用户id
    private int id;
    //用户邮箱
    private String email;
    //用户昵称
    private String uname;
    //用户密码
    private String password;
    //用户权限
    private String power;
    //订阅
    private int sub;
    //粉丝
    private int fan;
    //文章
    private int artical;
    //获得喜欢
    private int love;
    //头像
    private Blob image;
    //封禁
    private String ban;

    //方法类

    //昵称
    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUname() {
        return uname;
    }
    //id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //订阅
    public int getSub() {
        return sub;
    }

    public void setSub(int sub) {
        this.sub = sub;
    }
    //权限
    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
    //文章
    public int getArtical() {
        return artical;
    }

    public void setArtical(int artical) {
        this.artical = artical;
    }
    //粉丝
    public int getFan() {
        return fan;
    }

    public void setFan(int fan) {
        this.fan = fan;
    }
    //获得喜欢
    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }
    //邮箱
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //密码
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //头像

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    //封禁

    public void setBan(String ban) {
        this.ban = ban;
    }

    public String getBan() {
        return ban;
    }
}
