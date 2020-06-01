package com.hwh.www.po;

public class Follow {
    //发起关注
    private int sub;
    //被关注
    private int besub;
    //执行时间
    private String time;

    /*方法*/

    public void setSub(int sub) {
        this.sub = sub;
    }

    public int getSub() {
        return sub;
    }

    public int getBesub() {
        return besub;
    }

    public void setBesub(int besub) {
        this.besub = besub;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
