package com.hwh.www.dao;

import com.hwh.www.po.Chat;

import java.util.List;

public interface ChatDao {
    /*寻找用户简信列表*/
    public List<Chat> getChat(int id,int toid);

    /*获取全部聊天记录*/
    public List<Chat> getAll();

    /*增加聊天信息*/
    public void addChat(int id,int toid,String content,String time);
}
