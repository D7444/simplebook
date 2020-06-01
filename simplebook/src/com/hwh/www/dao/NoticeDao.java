package com.hwh.www.dao;

import com.hwh.www.po.Notice;

import java.util.List;

public interface NoticeDao {
    /*获取发起的信息*/
    public List<Notice> getData(int id);

    /*获取邀请的信息*/
    public List<Notice> getBeData(int invite);

    /*增加信息*/
    public void add(int id,int invite,String content,String time);

    /*删除信息*/
    public void delete(int noticeId);

    /*更新信息*/
    public void update(int noticeId,String choice);

    /*寻找*/
    public Notice findId(int noticeId);
}
