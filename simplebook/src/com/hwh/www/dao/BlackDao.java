package com.hwh.www.dao;

import com.hwh.www.po.Black;

import java.util.List;

public interface BlackDao {
    /*获取用户黑名单*/
    public List<Black> getBlack(int id);
    /*增加*/
    public void add(int id,int beid);
    /*删除*/
    public void delete(int blackId);
    /*删除2*/
    public void delete(int id,int toid);
}
