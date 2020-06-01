package com.hwh.www.service;

import com.hwh.www.po.WenZhang;

import java.util.List;

public interface CollectDZService {
    /*判断是否点赞*/
    public boolean judgeDz(int id,int wzid,int plid);

    /*判断是否收藏*/
    public boolean judgeCollect(int id,int wzid);

    /*点赞操作*/
    public int DZcontrol(int wzid,int id,int plid,int beid);

    /*增加收藏*/
    public void addCollect(int id,int wzid);

    /*移除收藏*/
    public void deleteCollect(int id,int wzid);

    /*获取收藏*/
    public List<WenZhang> getCollect(int id);

}
