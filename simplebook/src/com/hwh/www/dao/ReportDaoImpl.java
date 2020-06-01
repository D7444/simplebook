package com.hwh.www.dao;

import com.hwh.www.po.Report;
import com.hwh.www.until.DButil;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDaoImpl implements ReportDao {
    /*遍历*/
    @Override
    public List<Report> getReport(){
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Report> reportList = new ArrayList<>();
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from report order by `time` DESC";
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            Report report = null;
            while (rs.next()){
                report = new Report();
                report.setReportId(rs.getInt("reportId"));
                report.setBeid(rs.getInt("beid"));
                report.setId(rs.getInt("id"));
                report.setType(rs.getString("type"));
                report.setContent(rs.getString("content"));
                report.setBack(rs.getString("back"));
                report.setTime(rs.getString("time"));
                reportList.add(report);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,statement,conn);
        }
        return reportList;
    }

    /*增加举报*/
    @Override
    public void add(int id,int beid,String type,String content,String time){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "insert into report(id,beid,content,`time`,`type`) values (?,?,?,?,?)";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            psql.setInt(2,beid);
            psql.setString(3,content);
            psql.setString(4,time);
            psql.setString(5,type);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*更新举报*/
    @Override
    public void update(int reportId,String back){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "update report set back=? where reportId=?";
            psql = conn.prepareStatement(sql);
            psql.setString(1,back);
            psql.setInt(2,reportId);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }
}
