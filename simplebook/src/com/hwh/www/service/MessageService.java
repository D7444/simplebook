package com.hwh.www.service;

import com.hwh.www.po.Message;

import java.util.List;

public interface MessageService {
    //读取简信列表
    public List<Message> getUser(int id);

    //读取简信记录
    public List<Message> getMessage();

    //增加简信记录
    public String sendMessage(int messageId,int id,int toid,String content);

    /*增加简信会话*/
    public void addSession(int id,int toid,String content);

    /*删除简信会话*/
    public void deleteSession(int messageId);
}
