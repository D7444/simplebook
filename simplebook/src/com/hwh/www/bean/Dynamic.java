package com.hwh.www.bean;

import com.hwh.www.po.*;

public class Dynamic implements Comparable<Dynamic>{
    //用户id
    private int id;
    //用户昵称
    private String uname;
    //时间
    private String time;
    //关注
    private User followUser;
    //点赞（喜欢）
    private DianZan dianZan;
    //发表文章
    private WenZhang wenZhang;
    //发表评论
    private PingLun pingLun;



    /*方法*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DianZan getDianZan() {
        return dianZan;
    }

    public void setDianZan(DianZan dianZan) {
        this.dianZan = dianZan;
    }

    public void setFollowUser(User followUser) {
        this.followUser = followUser;
    }

    public User getFollowUser() {
        return followUser;
    }

    public PingLun getPingLun() {
        return pingLun;
    }

    public void setPingLun(PingLun pingLun) {
        this.pingLun = pingLun;
    }

    public void setWenZhang(WenZhang wenZhang) {
        this.wenZhang = wenZhang;
    }

    public WenZhang getWenZhang() {
        return wenZhang;
    }

    @Override
    public int compareTo(Dynamic o) {
        return o.getTime().compareTo(this.time);
    }
}
