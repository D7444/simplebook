package com.hwh.www.dao;

import com.hwh.www.po.PingLun;

import java.util.List;

public interface PingLunDao {
    //输出文章id对应评论
    public List<PingLun> findPl(int wzid);
    //寻找评论
    public PingLun findId(int plid);
    //寻找某人的评论
    public List<PingLun> foundPl(int id);
    //更新
    public void update(PingLun pingLun);
    //增加
    public void add(PingLun pingLun);
    //删除
    public void delete(int plid);
    /*删除文章的所有评论*/
    public void deleteWzPl(int wzid);
}
