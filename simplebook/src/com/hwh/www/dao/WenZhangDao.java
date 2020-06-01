package com.hwh.www.dao;

import com.hwh.www.po.WenZhang;

import java.util.List;

public interface WenZhangDao {
    //获取数据数
    public int getCount();

    //分页
    public List<WenZhang> getPage(int start,int end);

    //遍历
    public List<WenZhang> loadData();

    //查询文章
    public WenZhang findById(int wzid);

    //查询个人多篇文章
    public List<WenZhang> findMore(int id);

    /*查询相同类型文章*/
    public List<WenZhang> findType(String type);

    /*查询热门*/
    public WenZhang findHot(String hot);

    //更新
    public void update(WenZhang wenZhang);

    //增加
    public void add(WenZhang wenZhang);

    //删除
    public void delete(int wzid);
}
