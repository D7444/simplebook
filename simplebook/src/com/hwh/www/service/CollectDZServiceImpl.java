package com.hwh.www.service;

import com.hwh.www.dao.*;
import com.hwh.www.po.*;
import com.hwh.www.until.TimeUntil;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hx25
 */
public class CollectDZServiceImpl implements CollectDZService {
    /*判断是否点赞*/
    @Override
    public boolean judgeDz(int id,int wzid,int plid){
        DianZanDao dianZanDao = new DianZanDaoImpl();
        //获取文章或一级评论点赞列表
        List<DianZan> dianZanList = dianZanDao.getDianZan(wzid,plid);
        for(DianZan dianZan:dianZanList){
            if(dianZan.getId()==id){
                return true;
            }
        }
        return false;
    }

    /*判断是否收藏*/
    @Override
    public boolean judgeCollect(int id,int wzid){
        CollectDao collectDao = new CollectDaoImpl();
        List<Collect> collectList = collectDao.getCollect(id);
        for(Collect collect:collectList){
            if(collect.getWzid()==wzid){
                return true;
            }
        }
        return false;
    }

    /*点赞操作*/
    @Override
    public int DZcontrol(int wzid,int id,int plid,int beid){
        DianZanDao dianZanDao = new DianZanDaoImpl();
        //返回0，取消点赞
        if(judgeDz(id, wzid, plid)){
            dianZanDao.delete(id,wzid,plid);
            //文章点赞
            if(plid==0){
                WenZhangDao wenZhangDao = new WenZhangDaoImol();
                WenZhang wenZhang = wenZhangDao.findById(wzid);
                wenZhang.setDianzan(wenZhang.getDianzan()-1);
                wenZhangDao.update(wenZhang);
            }
            //评论点赞
            else{
                PingLunDao pingLunDao = new PingLunDaoImpl();
                PingLun pingLun = pingLunDao.findId(plid);
                pingLun.setDianzan(pingLun.getDianzan()-1);
                pingLunDao.update(pingLun);
            }
            //用户喜欢减少(beid---被点赞的用户)
            UserDao userDao = new UserDaoImpl();
            User user = userDao.findById(beid);
            user.setLove(user.getLove()-1);
            userDao.update(user);
            return 0;
        }
        //返回1，点赞
        else {
            dianZanDao.add(id,wzid,plid, TimeUntil.getNowTime());
            if(plid==0){
                WenZhangDao wenZhangDao = new WenZhangDaoImol();
                WenZhang wenZhang = wenZhangDao.findById(wzid);
                wenZhang.setDianzan(wenZhang.getDianzan()+1);
                wenZhangDao.update(wenZhang);
            }
            else{
                PingLunDao pingLunDao = new PingLunDaoImpl();
                PingLun pingLun = pingLunDao.findId(plid);
                pingLun.setDianzan(pingLun.getDianzan()+1);
                pingLunDao.update(pingLun);
            }
            //用户喜欢增加(beid---被点赞的用户)
            UserDao userDao = new UserDaoImpl();
            User user = userDao.findById(beid);
            user.setLove(user.getLove()+1);
            userDao.update(user);
            return 1;
        }
    }

    /*增加收藏*/
    @Override
    public void addCollect(int id,int wzid){
        CollectDao collectDao = new CollectDaoImpl();
        //增加收藏记录
        collectDao.add(id,wzid, TimeUntil.getNowTime());
        //增加文章收藏数量
        WenZhangDao wenZhangDao = new WenZhangDaoImol();
        WenZhang wenZhang = wenZhangDao.findById(wzid);
        wenZhang.setShoucang(wenZhang.getShoucang()+1);
        wenZhangDao.update(wenZhang);
    }

    /*移除收藏*/
    @Override
    public void deleteCollect(int id,int wzid){
        CollectDao collectDao = new CollectDaoImpl();
        //删除收藏记录
        collectDao.delete(id,wzid);
        //减少文章收藏数量
        WenZhangDao wenZhangDao = new WenZhangDaoImol();
        WenZhang wenZhang = wenZhangDao.findById(wzid);
        wenZhang.setShoucang(wenZhang.getShoucang()-1);
        wenZhangDao.update(wenZhang);
    }

    /*获取收藏列表*/
    @Override
    public List<WenZhang> getCollect(int id){
        CollectDao collectDao = new CollectDaoImpl();
        WenZhangDao wenZhangDao = new WenZhangDaoImol();
        //获取收藏文章id
        List<Collect> collectList = collectDao.getCollect(id);
        //空列表储存收藏
        List<WenZhang> wenZhangList = new ArrayList<>();
        //转化收藏列表
        for(Collect collect:collectList){
            wenZhangList.add(wenZhangDao.findById(collect.getWzid()));
        }
        //获取用户昵称
        UserDao userDao = new UserDaoImpl();
        for(WenZhang wenZhang:wenZhangList){
            wenZhang.setUname(userDao.findById(wenZhang.getId()).getUname());
        }
        return wenZhangList;
    }
}
