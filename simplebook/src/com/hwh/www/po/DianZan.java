package com.hwh.www.po;

public class DianZan {
    //用户id
    private int id;
    //文章id
    private int wzid;
    //一级评论id
    private int plid;
    //执行时间
    private String time;



    /*方法*/
    public void setWzid(int wzid) {
        this.wzid = wzid;
    }

    public int getWzid() {
        return wzid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public int getPlid() {
        return plid;
    }

    public void setPlid(int plid) {
        this.plid = plid;
    }
}
