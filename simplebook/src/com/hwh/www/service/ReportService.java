package com.hwh.www.service;

import com.hwh.www.po.Report;

import java.util.List;

public interface ReportService {
    //获取全部举报
    public List<Report> getReport();

    //增加举报
    public void addReport(int id,int beid,String type,String content);

    //处理举报
    public void dealReport(int reportId);
}
