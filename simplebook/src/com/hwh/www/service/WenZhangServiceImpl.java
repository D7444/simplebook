package com.hwh.www.service;

import com.hwh.www.bean.Page;
import com.hwh.www.dao.*;
import com.hwh.www.po.DianZan;
import com.hwh.www.po.PingLun;
import com.hwh.www.po.User;
import com.hwh.www.po.WenZhang;
import com.hwh.www.until.ChineseUtil;
import com.hwh.www.until.PatternUtil;
import com.hwh.www.until.TimeUntil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class WenZhangServiceImpl implements WenZhangService {

    /*获取分页数及功能*/
    @Override
    public Page dividePage(){
        Page page = new Page();
        WenZhangDao wenZhangDao = new WenZhangDaoImol();
        //获取总数据数
        page.setTotalCount(wenZhangDao.getCount());
        return page;
    }

    /*遍历文章(分页)*/
    @Override
    public List<WenZhang> getWenZhang(int start,int end){
        WenZhangDao wenZhangDao = new WenZhangDaoImol();
        List<WenZhang> wenZhangList = wenZhangDao.getPage(start, end);
        UserDao userDao = new UserDaoImpl();
        //获取用户昵称
        for(WenZhang wenZhang:wenZhangList){
            //设置昵称
            wenZhang.setUname(userDao.findById(wenZhang.getId()).getUname());
            //获取文章首张图片
            Matcher matcher = PatternUtil.divideNumber(wenZhang.getContent(),"<img.*?/>");
            if(matcher.find()) {
                wenZhang.setPhoto(matcher.group(0));
            }
        }
        return wenZhangList;
    }

    /*获取相关类型文章*/
    @Override
    public List<WenZhang> getWzType(String type){
        WenZhangDao wenZhangDao = new WenZhangDaoImol();
        return wenZhangDao.findType(type);
    }

    /*查询文章*/
    @Override
    public WenZhang findWz(int wzid){
        //获取文章信息
        WenZhangDao wenZhangDao = new WenZhangDaoImol();
        WenZhang wenZhang = wenZhangDao.findById(wzid);

        //获取文章对应用户昵称
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findById(wenZhang.getId());
        //获取文章首张图片
        Matcher matcher = PatternUtil.divideNumber(wenZhang.getContent(),"<img.*?/>");
        if(matcher.find()) {
            wenZhang.setPhoto(matcher.group(0));
        }
        wenZhang.setUname(user.getUname());
        return wenZhang;
    }

    /*查询个人多篇文章*/
    @Override
    public List<WenZhang> findMore(int id){
        WenZhangDao wenZhangDao = new WenZhangDaoImol();
        //获取文章
        List<WenZhang> wenZhangList = wenZhangDao.findMore(id);
        UserDao userDao = new UserDaoImpl();
        //获取用户昵称
        for(WenZhang wenZhang:wenZhangList){
            wenZhang.setUname(userDao.findById(wenZhang.getId()).getUname());
        }
        return wenZhangList;
    }

    /*查询文章的评论*/
    @Override
    public List<PingLun> findPl(int wzid,int plid){
        PingLunDao pingLunDao = new PingLunDaoImpl();
        UserDao userDao = new UserDaoImpl();
        //全评论
        List<PingLun> oldList = pingLunDao.findPl(wzid);
        //储存筛选后的评论
        List<PingLun> pingLunList = new ArrayList<>();
        //一级评论
        if(plid==0) {
            for (PingLun pingLun : oldList) {
                if (pingLun.getFatherid() == 0){
                    //获取名字
                    pingLun.setUname(userDao.findById(pingLun.getId()).getUname());
                    pingLunList.add(pingLun);
                }
            }
        }
        //二级评论
        else {
            for (PingLun pingLun : oldList) {
                if (pingLun.getFatherid() != 0){
                    //获取名字
                    pingLun.setUname(userDao.findById(pingLun.getId()).getUname());
                    pingLunList.add(pingLun);
                }
            }
        }
        return pingLunList;
    }

    /*增加文章*/
    @Override
    public void addWz(String title,String breif,String content,String type,int id){
        WenZhangDao wenZhangDao = new WenZhangDaoImol();
        if(breif.length()>=100){
            breif = ChineseUtil.subChString(breif,0,200);
        }
        //组装文章
        WenZhang wenZhang = new WenZhang();
        wenZhang.setType(type);
        wenZhang.setTitle(title);
        wenZhang.setBreif(breif);
        wenZhang.setContent(content);
        wenZhang.setId(id);
        wenZhang.setDianzan(0);
        wenZhang.setShoucang(0);
        wenZhang.setTime(TimeUntil.getNowTime());
        //上传数据库
        wenZhangDao.add(wenZhang);
        //个人文章+1
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findById(id);
        user.setArtical(user.getArtical()+1);
        userDao.update(user);

    }

    /*删除文章*/
    @Override
    public void deleteWz(int wzid){
        WenZhangDao wenZhangDao = new WenZhangDaoImol();
        wenZhangDao.delete(wzid);
        //删除相关评论
        PingLunDao pingLunDao = new PingLunDaoImpl();
        pingLunDao.deleteWzPl(wzid);
        //删除相关收藏
        DianZanDao dianZanDao = new DianZanDaoImpl();
        dianZanDao.deleteWzDz(wzid);
        //删除相关收藏
        CollectDao collectDao = new CollectDaoImpl();
        collectDao.deleteWzCl(wzid);
    }

    /*修改文章（更新文章）*/
    @Override
    public void updateWz(int wzid,String title,String breif,String content){
        if(breif.length()>=100){
            breif = ChineseUtil.subChString(breif,0,200);
        }
        WenZhangDao wenZhangDao = new WenZhangDaoImol();
        WenZhang wenZhang = wenZhangDao.findById(wzid);
        wenZhang.setTitle(title);
        wenZhang.setBreif(breif);
        wenZhang.setContent(content);
        wenZhang.setTime(TimeUntil.getNowTime());
        wenZhangDao.update(wenZhang);
    }

    /*增加评论*/
    @Override
    public String addPl(int wzid,int id,int fatherid,String content){
        WenZhangDao wenZhangDao = new WenZhangDaoImol();
        WenZhang wenZhang = wenZhangDao.findById(wzid);
        /*判断是否是黑名单*/
        SignService signService = new SignServiceImpl();
        if(signService.judgeBlack(wenZhang.getId(),id)){
            return "你已被该用户列入黑名单";
        }
        PingLunDao pingLunDao = new PingLunDaoImpl();
        //组装评论
        PingLun pingLun = new PingLun();
        pingLun.setWzid(wzid);
        pingLun.setId(id);
        pingLun.setFatherid(fatherid);
        pingLun.setContent(content);
        pingLun.setTime(TimeUntil.getNowTime());
        //上传数据库
        pingLunDao.add(pingLun);
        return "success";
    }

    /*删除评论*/
    @Override
    public void deletePl(int plid){
        PingLunDao pingLunDao = new PingLunDaoImpl();
        pingLunDao.delete(plid);
    }

    /*查询某人评论的文章*/
    @Override
    public List<WenZhang> foundPlWz(int id){
        WenZhangDao wenZhangDao = new WenZhangDaoImol();
        PingLunDao pingLunDao = new PingLunDaoImpl();
        //建立空表储存评论过的文章
        List<WenZhang> wenZhangList = new ArrayList<>();
        //寻找用户的评论
        List<PingLun> pingLunList = pingLunDao.foundPl(id);
        //防重复
        List<Integer> list = new ArrayList<>();
        list.add(0);
        for(PingLun pingLun:pingLunList){
            int flag=0;
            for(Integer i:list) {
                if (pingLun.getWzid() == i) {
                    flag=1;
                    break;
                }
            }
            if(flag==1){
                break;
            }
            //获取对应文章，加入列表
            wenZhangList.add(wenZhangDao.findById(pingLun.getWzid()));
            list.add(pingLun.getWzid());
        }
        return wenZhangList;
    }

    /*查询热门文章*/
    @Override
    public WenZhang findHot(){
        WenZhangDao wenZhangDao = new WenZhangDaoImol();
        return wenZhangDao.findHot("热门");
    }

    /*设置热门文章*/
    @Override
    public void setHot(int wzid){
        WenZhangDao wenZhangDao = new WenZhangDaoImol();

        //去除原热门文章
        WenZhang oldWz = wenZhangDao.findHot("热门");
        //判读是否有原热门文章
        if(oldWz != null) {
            oldWz.setHot("");
            wenZhangDao.update(oldWz);
        }

        //设置新热门文章
        WenZhang wenZhang = wenZhangDao.findById(wzid);
        wenZhang.setHot("热门");
        wenZhangDao.update(wenZhang);
    }





}
