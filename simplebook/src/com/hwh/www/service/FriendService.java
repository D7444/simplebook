package com.hwh.www.service;

import com.hwh.www.po.Friend;
import com.hwh.www.po.Group;

import java.util.List;

public interface FriendService {
    /*获取自定义列表名称*/
    public List<Group> getGroup(int id);

    /*增加自定义列表名称*/
    public void addGroup(int id,String group);

    /*删除自定义列表名称*/
    public void deleteGroup(int groupId,int id,String group);

    /*更换组名*/
    public void updateGroupName(int groupId,String newgroup);

    /*更换好友所在组*/
    public void changeFriendGroup(int id,int freid,String group);

    /*获取好友列表*/
    public List<Friend> getFriend(int id);

    /*获取好友且互相关注列表*/
    public List<Friend> getTogether(int id);

    /*增加好友*/
    public void addFriend(int noticeId);

    /*删除好友*/
    public void deleteFriend(int id,int freid);

    /*判断是否是好友*/
    public boolean judgeFriend(int id,int freid);
}
