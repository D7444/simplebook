package com.hwh.www.po;

public class Collect {
    //用户ID
    private int id;
    //文章ID
    private int wzid;
    //时间
    private String time;


    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWzid() {
        return wzid;
    }

    public void setWzid(int wzid) {
        this.wzid = wzid;
    }
}
