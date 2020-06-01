package com.hwh.www.dao;

import com.hwh.www.po.Report;

import java.util.List;

public interface ReportDao {
    /*遍历*/
    public List<Report> getReport();

    /*增加举报*/
    public void add(int id,int beid,String type,String content,String time);

    /*更新举报*/
    public void update(int reportId,String back);
}
