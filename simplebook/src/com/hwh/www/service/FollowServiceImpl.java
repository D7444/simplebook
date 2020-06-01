package com.hwh.www.service;

import com.hwh.www.dao.FollowDao;
import com.hwh.www.dao.FollowDaoImpl;
import com.hwh.www.dao.UserDao;
import com.hwh.www.dao.UserDaoImpl;
import com.hwh.www.po.Follow;
import com.hwh.www.po.User;
import com.hwh.www.until.TimeUntil;

import java.util.ArrayList;
import java.util.List;

public class FollowServiceImpl implements FollowService {
    /*判断是否关注*/
    @Override
    public boolean judgeSub(int sub,int besub){
        FollowDao followDao = new FollowDaoImpl();
        //取得关注列表
        List<Follow> subList = followDao.findData(sub);
        //判断是否在关注列表中
        for(Follow follow:subList){
            if(follow.getBesub()==besub){
                return true;
            }
        }
        return false;
    }

    /*关注*/
    @Override
    public String sub(int sub,int besub){
        /*判断是否是黑名单*/
        SignService signService = new SignServiceImpl();
        if(signService.judgeBlack(besub,sub)){
            return "你已被该用户列入黑名单";
        }

        //整理数据
        Follow follow = new Follow();
        follow.setSub(sub);
        follow.setBesub(besub);
        follow.setTime(TimeUntil.getNowTime());
        //插入数据
        FollowDao followDao = new FollowDaoImpl();
        followDao.add(follow);
        //修改用户信息
        UserDao userDao = new UserDaoImpl();
        //发起关注的用户，关注+1
        User user1 = userDao.findById(sub);
        user1.setSub(user1.getSub()+1);
        userDao.update(user1);
        //被关注的用户，粉丝+1
        User user2 = userDao.findById(besub);
        user2.setFan(user2.getFan()+1);
        userDao.update(user2);

        return "success";
    }

    /*取消关注*/
    @Override
    public void cancelSub(int sub,int besub){
        //整理数据
        Follow follow = new Follow();
        follow.setSub(sub);
        follow.setBesub(besub);
        //插入数据
        FollowDao followDao = new FollowDaoImpl();
        followDao.delete(follow);
        //修改用户信息
        UserDao userDao = new UserDaoImpl();
        //发起关注的用户，关注-1
        User user1 = userDao.findById(sub);
        user1.setSub(user1.getSub()-1);
        userDao.update(user1);
        //被关注的用户，粉丝-1
        User user2 = userDao.findById(besub);
        user2.setFan(user2.getFan()-1);
        userDao.update(user2);
    }

    /*获取关注列表*/
    @Override
    public List<User> getSub(int sub){
        FollowDao followDao = new FollowDaoImpl();
        UserDao userDao = new UserDaoImpl();
        List<Follow> followList = followDao.findData(sub);
        List<User> userList = new ArrayList<>();
        for(Follow follow:followList){
            userList.add(userDao.findById(follow.getBesub()));
        }
        return userList;
    }

    /*获取粉丝列表*/
    @Override
    public List<User> getFan(int besub){
        FollowDao followDao = new FollowDaoImpl();
        UserDao userDao = new UserDaoImpl();
        List<Follow> followList = followDao.findBeData(besub);
        List<User> userList = new ArrayList<>();
        for(Follow follow:followList){
            userList.add(userDao.findById(follow.getSub()));
        }
        return userList;
    }

    /*判断是否是互相关注*/
    @Override
    public boolean judgeTogether(int id,int toid){
        FollowDao followDao = new FollowDaoImpl();
        //双向确认
        int flag1=0,flag2=0;
        //确认toid是否为id的关注者
        for(Follow follow:followDao.findData(id)){
            if(follow.getBesub()==toid){
                flag1=1;
                break;
            }
        }
        //确认id是否为toid的关注者
        for(Follow follow:followDao.findData(toid)){
            if(follow.getBesub()==id){
                flag2=1;
                break;
            }
        }
        //双向关注返回true，否则返回false
        if(flag1==1 && flag2==1){
            return true;
        }
        else {
            return false;
        }
    }
}
