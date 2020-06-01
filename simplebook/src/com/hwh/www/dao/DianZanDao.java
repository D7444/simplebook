package com.hwh.www.dao;

import com.hwh.www.po.DianZan;

import java.util.List;

public interface DianZanDao {
    /*获取某人的点赞*/
    public List<DianZan> getUserDz(int id);

    /*查询*/
    public List<DianZan> getDianZan(int wzid,int plid);

    /*增加*/
    public void add(int id,int wzid, int plid,String time);

    /*减少*/
    public void delete(int id,int wzid, int plid);

    /*删除文章相关点赞*/
    public void deleteWzDz(int wzid);
}
