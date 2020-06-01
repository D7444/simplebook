package com.hwh.www.service;

import com.hwh.www.po.Chat;

import java.util.List;

public interface ChatService {
    //获取聊天记录
    public List<Chat> getChat(int id,int toid);

    //增加聊天记录
    public void addChat(int id,int toid,String content);
}
