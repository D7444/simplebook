package com.hwh.www.po;

public class PingLun {
    /*对应数据库*/
    //评论id
    private int plid;
    //对应文章id
    private int wzid;
    //用户id
    private int id;
    //点赞数量
    private int dianzan;
    //父评论id
    private int fatherid;
    //内容
    private String content;
    //时间
    private String time;

    /*额外数据*/
    private String uname;

    /*方法*/

    public void setDianzan(int dianzan) {
        this.dianzan = dianzan;
    }

    public int getDianzan() {
        return dianzan;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

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

    public int getFatherid() {
        return fatherid;
    }

    public void setFatherid(int fatherid) {
        this.fatherid = fatherid;
    }

    public void setWzid(int wzid) {
        this.wzid = wzid;
    }

    public int getWzid() {
        return wzid;
    }

    public void setPlid(int plid) {
        this.plid = plid;
    }

    public int getPlid() {
        return plid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUname() {
        return uname;
    }
}
