package com.hwh.www.po;

public class Friend {
    //个人id
    private int id;
    //好友id
    private int freid;
    //分组
    private String group;

    /*数据转化*/
    //好友名称
    private String uname;

    /*方法*/

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUname() {
        return uname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFreid() {
        return freid;
    }

    public void setFreid(int freid) {
        this.freid = freid;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
