package com.hwh.www.service;

import com.hwh.www.bean.Page;
import com.hwh.www.po.PingLun;
import com.hwh.www.po.WenZhang;

import java.util.List;

public interface WenZhangService {
    /*获取分页数及功能*/
    public Page dividePage();

    //遍历文章(分页)
    public List<WenZhang> getWenZhang(int start,int end);

    //获取相关类型文章
    public List<WenZhang> getWzType(String type);

    //查询文章
    public WenZhang findWz(int id);

    //查询个人多篇文章
    public List<WenZhang> findMore(int id);

    //查询文章的评论
    public List<PingLun> findPl(int wzid,int plid);

    //增加文章
    public void addWz(String title,String breif,String content,String type,int id);

    //删除文章
    public void deleteWz(int wzid);

    //更新文章
    public void updateWz(int wzid,String title,String breif,String content);

    //增加评论
    public String addPl(int wzid,int id,int fatherid,String content);

    //删除评论
    public void deletePl(int plid);

    //查询某人评论的文章
    public List<WenZhang> foundPlWz(int id);

    /*查询热门文章*/
    public WenZhang findHot();

    /*设置热门文章*/
    public void setHot(int wzid);


}
