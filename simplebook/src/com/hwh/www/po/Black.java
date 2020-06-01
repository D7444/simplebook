package com.hwh.www.po;

public class Black {
    //黑名单主键
    private int blackId;
    //用户id
    private int id;
    //加入黑名单用户
    private int beid;

    /*表外数据*/
    //黑名单用户名
    private String uname;

    /*方法*/

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getBeid() {
        return beid;
    }

    public void setBeid(int beid) {
        this.beid = beid;
    }

    public int getBlackId() {
        return blackId;
    }

    public void setBlackId(int blackId) {
        this.blackId = blackId;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUname() {
        return uname;
    }
}
