package com.hwh.www.po;

public class Report {
    //举报信息主键
    private int reportId;
    //举报人id
    private int id;
    //被举报人id
    private int beid;
    //类型
    private String type;
    //举报描述
    private String content;
    //客服反馈
    private String back;
    //时间
    private String time;

    /*数据库外变量*/
    //被举报人昵称
    private String uname;

    /*方法*/

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setBeid(int beid) {
        this.beid = beid;
    }

    public int getBeid() {
        return beid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUname() {
        return uname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
