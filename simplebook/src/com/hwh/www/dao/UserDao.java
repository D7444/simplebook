package com.hwh.www.dao;

import com.hwh.www.po.User;

import java.io.InputStream;
import java.util.List;

public interface UserDao {
    //遍历
    public List<User> loadData();

    /*根据获得喜欢排序*/
    public List<User> getLove();

    //插入（注册账号）
    public void insert(User user);

    //查找
    public User findById(int id);

    //修改密码
    public void changePassword(String email,String password);

    //更新
    public void update(User user);

    //上传图片
    public void updatePhoto(InputStream input, int id);

}
