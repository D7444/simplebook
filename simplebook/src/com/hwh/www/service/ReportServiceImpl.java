package com.hwh.www.service;

import com.hwh.www.dao.ReportDao;
import com.hwh.www.dao.ReportDaoImpl;
import com.hwh.www.dao.UserDao;
import com.hwh.www.dao.UserDaoImpl;
import com.hwh.www.po.Report;
import com.hwh.www.until.TimeUntil;

import java.util.List;

public class ReportServiceImpl implements ReportService {
    //获取全部举报
    @Override
    public List<Report> getReport(){
        ReportDao reportDao = new ReportDaoImpl();
        UserDao userDao = new UserDaoImpl();
        List<Report> reportList = reportDao.getReport();
        //获取被举报人的昵称
        for(Report report:reportList){
            report.setUname(userDao.findById(report.getBeid()).getUname());
        }
        return reportList;
    }

    //增加举报
    @Override
    public void addReport(int id,int beid,String type,String content){
        ReportDao reportDao = new ReportDaoImpl();
        reportDao.add(id,beid,type,content,TimeUntil.getNowTime());
    }

    //处理举报
    @Override
    public void dealReport(int reportId){
        ReportDao reportDao = new ReportDaoImpl();
        reportDao.update(reportId,"已读");
    }
}
