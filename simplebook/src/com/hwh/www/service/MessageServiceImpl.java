package com.hwh.www.service;

import com.hwh.www.dao.*;
import com.hwh.www.po.Message;
import com.hwh.www.until.TimeUntil;

import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    //读取简信列表
    @Override
    public List<Message> getUser(int id){
        MessageDao messageDao = new MessageDaoImpl();
        UserDao userDao = new UserDaoImpl();
        //获取记录
        List<Message> messageList = messageDao.getUser();
        //新记录
        List<Message> messageArrayList = new ArrayList<>();

        //处理数据，获取双向列表
        for(Message message:messageList){
            if(message.getId()==id || message.getToid()==id){
                messageArrayList.add(message);
            }
        }

        //读取昵称
        for(Message message:messageArrayList){
            //交换id，用于显示
            if(message.getId()!=id) {
                int i = 0;
                i = message.getToid();
                message.setToid(message.getId());
                message.setId(i);
            }
            //提取简信者名字
            message.setUname(userDao.findById(message.getToid()).getUname());
        }

        return messageArrayList;
    }

    //读取简信记录
    @Override
    public List<Message> getMessage(){
        MessageDao messageDao = new MessageDaoImpl();
        //获取记录(时间升序)
        return messageDao.getAll();
    }

    //增加简信记录
    @Override
    public String sendMessage(int messageId,int id,int toid,String content){
        MessageDao messageDao = new MessageDaoImpl();
        FriendService friendService = new FriendServiceImpl();
        List<Message> messageList = messageDao.getAll();

        //判断是否超过3条简信
        int i=0;
        for(Message message:messageList){
            if(message.getId()==id && message.getToid() == toid){
                i++;
            }
            //大于3条简信
            if(i>=3){
                //判断是否是好友
                if(friendService.judgeFriend(id,toid)){
                    break;
                }else {
                    return "非好友只能发送3条简信";
                }
            }
        }

        /*判断是否是黑名单*/
        SignService signService = new SignServiceImpl();
        if(signService.judgeBlack(toid,id)){
            return "你已被该用户拉黑，无法发送简信";
        }


        //写入数据库
        String time = TimeUntil.getNowTime();
        messageDao.addMessage(messageId,id,toid,content, time);
        //更新最新信息
        messageDao.updateMessage(messageId,content,time);

        return "success";
    }

    /*增加简信会话*/
    @Override
    public void addSession(int id,int toid,String content){
        MessageDao messageDao = new MessageDaoImpl();
        /*判断是否已有会话*/
        List<Message> messageList = messageDao.getUser();
        for(Message message:messageList){
            //己方发起过
            if(message.getId()==id && message.getToid()==toid){
                return;
            }
            //对方发起过
            if(message.getId()==toid && message.getToid()==id){
                return;
            }
        }

        /*判断是否是黑名单*/
        SignService signService = new SignServiceImpl();
        if(signService.judgeBlack(toid,id)){
            return ;
        }

        //增加
        messageDao.addSession(id,toid,content,TimeUntil.getNowTime());
    }

    /*删除简信会话*/
    @Override
    public void deleteSession(int messageId){
        MessageDao messageDao = new MessageDaoImpl();
        messageDao.delete(messageId);
    }

}
