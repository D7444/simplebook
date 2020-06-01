package com.hwh.www.service;

import com.hwh.www.po.Notice;

import java.util.List;

public interface NoticeService {
    /*获取发起的消息*/
    public List<Notice> getNotice(int id);

    /*获取受到邀请的消息*/
    public List<Notice> getBeNotice(int invite);

    /*发起新消息*/
    public void addNotice(int id,int invite,String content);

    /*删除新消息*/
    public void deleteNotice(int noticeId);

    /*更新消息*/
    public void update(int noticeId,String choice);
}
