package com.hwh.www.dao;

import com.hwh.www.po.Friend;

import java.util.List;

public interface FriendDao {
    /*获取好友列表*/
    public List<Friend> getFriend(int id);

    /*增加好友*/
    public void add(int id,int freid);

    /*删除好友*/
    public void delete(int id,int freid);

    /*更新*/
    public void update(int id,int freid,String group);

    /*多行更新*/
    public void updateMore(int id,String group,String newgroup);
}
