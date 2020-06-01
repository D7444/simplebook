package com.hwh.www.dao;

import com.hwh.www.po.Collect;

import java.util.List;

public interface CollectDao {
    /*获取收藏列表*/
    public List<Collect> getCollect(int id);

    /*增加*/
    public void add(int id,int wzid,String time);

    /*删除*/
    public void delete(int id,int wzid);

    /*删除文章相关收藏*/
    public void deleteWzCl(int wzid);
}
