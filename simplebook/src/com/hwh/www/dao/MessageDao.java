package com.hwh.www.dao;

import com.hwh.www.po.Message;

import java.util.List;

public interface MessageDao {
    /*获取简信用户列表*/
    public List<Message> getUser();

    /*获取全部简信记录*/
    public List<Message> getAll();

    /*获取简信记录*///废弃的方法，不会ajax异步加载div
    public List<Message> getMessage(int MessageId);

    /*增加简信*/
    public void addMessage(int messageId,int id,int toid,String content,String time);

    /*增加对话*/
    public void addSession(int id,int toid,String content,String time);

    /*更新最新状态*/
    public void updateMessage(int messageId,String content,String time);

    /*删除*/
    public void delete(int messageId);

}
