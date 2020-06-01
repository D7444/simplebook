package com.hwh.www.service;

import com.hwh.www.po.User;

import java.util.List;

public interface FollowService {
    /*判断是否关注*/
    public boolean judgeSub(int sub,int besub);

    /*关注*/
    public String sub(int sub,int besub);

    /*取消关注*/
    public void cancelSub(int sub,int besub);

    /*获取关注列表*/
    public List<User>getSub(int sub);

    /*获取粉丝列表*/
    public List<User> getFan(int besub);

    /*判断是否是互相关注*/
    public boolean judgeTogether(int id,int toid);
}
