package com.hwh.www.dao;

import com.hwh.www.po.Notice;
import com.hwh.www.until.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoticeDaoImpl implements NoticeDao {
    /*获取发起的信息*/
    @Override
    public List<Notice> getData(int id){
        List<Notice> noticeList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from notice where id=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            rs = psql.executeQuery();
            Notice notice = null;
            while (rs.next()){
                notice = new Notice();
                notice.setNoticeId(rs.getInt("noticeId"));
                notice.setId(rs.getInt("id"));
                notice.setInvite(rs.getInt("invite"));
                notice.setContent(rs.getString("content"));
                notice.setChoice(rs.getString("choice"));
                notice.setTime(rs.getString("time"));
                noticeList.add(notice);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return noticeList;
    }

    /*获取邀请的信息*/
    @Override
    public List<Notice> getBeData(int invite){
        List<Notice> noticeList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from notice where invite=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,invite);
            rs = psql.executeQuery();
            Notice notice = null;
            while (rs.next()){
                notice = new Notice();
                notice.setNoticeId(rs.getInt("noticeId"));
                notice.setId(rs.getInt("id"));
                notice.setInvite(rs.getInt("invite"));
                notice.setContent(rs.getString("content"));
                notice.setChoice(rs.getString("choice"));
                notice.setTime(rs.getString("time"));
                noticeList.add(notice);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return noticeList;
    }

    /*增加信息*/
    @Override
    public void add(int id,int invite,String content,String time) {
        List<Notice> noticeList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try {
            conn = DButil.theSqlConnection();
            String sql = "insert into notice(id,invite,content,`time`) values (?,?,?,?)";
            psql = conn.prepareStatement(sql);
            psql.setInt(1, id);
            psql.setInt(2, invite);
            psql.setString(3, content);
            psql.setString(4,time);
            psql.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeConnection(rs, psql, conn);
        }
    }

    /*删除信息*/
    @Override
    public void delete(int noticeId) {
        List<Notice> noticeList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psql = null;
        try {
            conn = DButil.theSqlConnection();
            String sql = "delete from notice where noticeId=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1, noticeId);
            psql.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(psql,conn);
        }
    }

    /*更新信息*/
    @Override
    public void update(int noticeId,String choice){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "update notice set choice=? where noticeId=?";
            psql = conn.prepareStatement(sql);
            psql.setString(1,choice);
            psql.setInt(2,noticeId);
            psql.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(psql,conn);
        }
    }

    /*寻找*/
    @Override
    public Notice findId(int noticeId){
        Notice notice = null;
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from notice where noticeId=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,noticeId);
            rs = psql.executeQuery();
            while (rs.next()){
                notice = new Notice();
                notice.setNoticeId(rs.getInt("noticeId"));
                notice.setId(rs.getInt("id"));
                notice.setInvite(rs.getInt("invite"));
                notice.setContent(rs.getString("content"));
                notice.setChoice(rs.getString("choice"));
                notice.setTime(rs.getString("time"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return notice;
    }

}
