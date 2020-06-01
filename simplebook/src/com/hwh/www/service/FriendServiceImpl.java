package com.hwh.www.service;

import com.hwh.www.dao.*;
import com.hwh.www.po.Friend;
import com.hwh.www.po.Group;
import com.hwh.www.po.Notice;

import java.util.ArrayList;
import java.util.List;

public class FriendServiceImpl implements FriendService {

    /*获取自定义组*/
    @Override
    public List<Group> getGroup(int id){
        GroupDao groupDao = new GroupDaoImpl();
        return groupDao.getGroup(id);
    }

    /*增加自定义组*/
    @Override
    public void addGroup(int id,String group){
        GroupDao groupDao = new GroupDaoImpl();
        List<Group> groupList = groupDao.getGroup(id);
        //判断名称是否重复
        for(Group group1:groupList){
            if(group1.getGroup().equals(group)){
                return;
            }
        }
        //增加到数据库
        groupDao.add(id,group);
    }

    /*删除自定义组*/
    @Override
    public void deleteGroup(int groupId,int id,String group){
        GroupDao groupDao = new GroupDaoImpl();
        //删除组
        groupDao.delete(groupId);
        //将组内用户移动到默认列表
        FriendDao friendDao = new FriendDaoImpl();
        friendDao.updateMore(id,group,"默认列表");
    }

    /*更换组名*/
    @Override
    public void updateGroupName(int groupId,String newgroup){
        GroupDao groupDao = new GroupDaoImpl();
        FriendDao friendDao = new FriendDaoImpl();
        //获取原列表名和id
        Group group = groupDao.findById(groupId);
        //更新好友列表的组名
        friendDao.updateMore(group.getId(),group.getGroup(),newgroup);
        //更新组名
        groupDao.update(groupId,newgroup);
    }

    /*更换好友所在组*/
    @Override
    public void changeFriendGroup(int id,int freid,String group){
        FriendDao friendDao = new FriendDaoImpl();
        friendDao.update(id,freid,group);
    }

    /*获取好友列表*/
    @Override
    public List<Friend> getFriend(int id){
        FriendDao friendDao = new FriendDaoImpl();
        UserDao userDao = new UserDaoImpl();
        //获取原列表
        List<Friend> friendList = friendDao.getFriend(id);
        for(Friend friend:friendList){
            friend.setUname(userDao.findById(friend.getFreid()).getUname());
        }
        return friendList;
    }

    /*获取好友且互相关注列表*/
    @Override
    public List<Friend> getTogether(int id){
        FriendDao friendDao = new FriendDaoImpl();
        FollowService followService = new FollowServiceImpl();
        UserDao userDao = new UserDaoImpl();
        //空列表，用于储存
        List<Friend> newList = new ArrayList<>();
        //获取原列表
        List<Friend> friendList = friendDao.getFriend(id);
        for(Friend friend:friendList){
            if(followService.judgeTogether(id,friend.getFreid())){
                friend.setUname(userDao.findById(friend.getFreid()).getUname());
                newList.add(friend);
            }
        }
        return friendList;
    }

    /*增加好友*/
    @Override
    public void addFriend(int noticeId){
        FriendDao friendDao = new FriendDaoImpl();
        NoticeDao noticeDao = new NoticeDaoImpl();
        Notice notice = noticeDao.findId(noticeId);
        //增加请求人的好友
        friendDao.add(notice.getId(),notice.getInvite());
        //增加被请求人的好友
        friendDao.add(notice.getInvite(),notice.getId());
    }

    /*删除好友*/
    @Override
    public void deleteFriend(int id,int freid){
        FriendDao friendDao = new FriendDaoImpl();
        //双向删除好友
        friendDao.delete(id,freid);
        friendDao.delete(freid,id);
    }

    /*判断是否是好友*/
    @Override
    public boolean judgeFriend(int id,int freid){
        FriendDao friendDao = new FriendDaoImpl();
        //获取用户列表
        List<Friend> friendList = friendDao.getFriend(id);
        for(Friend friend:friendList){
            if(friend.getFreid() == freid){
                return true;
            }
        }
        return false;
    }
}
