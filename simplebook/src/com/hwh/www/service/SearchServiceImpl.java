package com.hwh.www.service;

import com.hwh.www.dao.UserDao;
import com.hwh.www.dao.UserDaoImpl;
import com.hwh.www.dao.WenZhangDao;
import com.hwh.www.dao.WenZhangDaoImol;
import com.hwh.www.po.User;
import com.hwh.www.po.WenZhang;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SearchServiceImpl implements SearchService {
    /*搜索文章*/
    @Override
    public List<WenZhang> findWz(String str){
        WenZhangDao wenZhangDao = new WenZhangDaoImol();
        //建立空表储存寻找结果
        List<WenZhang> newList = new ArrayList<>();
        //获取所有文章
        List<WenZhang> wenZhangList = wenZhangDao.loadData();
        //设置关键字
        Pattern pattern = Pattern.compile(str);
        //检索所有文章标题和内容
        for(WenZhang wenZhang:wenZhangList){
            if(pattern.matcher(wenZhang.getTitle()).find()){
                newList.add(wenZhang);
                continue;
            }
            if(pattern.matcher(wenZhang.getContent()).find()){
                newList.add(wenZhang);
                continue;
            }
        }
        return newList;
    }

    /*搜索用户*/
    @Override
    public List<User> findUser(String str){
        UserDao userDao = new UserDaoImpl();
        //建立空表储存寻找结果
        List<User> newList = new ArrayList<>();
        //获取所有用户
        List<User> userList = userDao.loadData();
        //设置关键字
        Pattern pattern = Pattern.compile(str);
        //检索所有用户昵称
        for(User user:userList){
            if(pattern.matcher(user.getUname()).find()){
                newList.add(user);
            }
        }
        return newList;
    }

}
