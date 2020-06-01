package com.hwh.www.service;

import com.hwh.www.dao.ChatDao;
import com.hwh.www.dao.ChatDaoImpl;
import com.hwh.www.po.Chat;
import com.hwh.www.until.TimeUntil;

import java.util.List;

public class ChatServiceImpl implements ChatService {

    //获取聊天记录
    @Override
    public List<Chat> getChat(int id,int toid){
        ChatDao chatDao = new ChatDaoImpl();
        return chatDao.getChat(id,toid);
    }

    //增加聊天记录
    @Override
    public void addChat(int id,int toid,String content){
        ChatDao chatDao = new ChatDaoImpl();
        chatDao.addChat(id,toid,content, TimeUntil.getNowTime());
    }

}
