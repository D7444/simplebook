package com.hwh.www.dao;

import com.hwh.www.po.Black;
import com.hwh.www.until.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlackDaoImpl implements BlackDao {
    /*获取用户黑名单*/
    @Override
    public List<Black> getBlack(int id){
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        List<Black> blackList = new ArrayList<>();
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from black where id=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            rs = psql.executeQuery();
            Black black = null;
            while (rs.next()){
                black = new Black();
                black.setBlackId(rs.getInt("blackId"));
                black.setId(rs.getInt("id"));
                black.setBeid(rs.getInt("beid"));
                blackList.add(black);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return blackList;
    }

    /*增加*/
    @Override
    public void add(int id,int beid){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "insert into black(id,beid) values (?,?)";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            psql.setInt(2,beid);
            psql.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*删除1*/
    @Override
    public void delete(int blackId){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "delete from black where blackId=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,blackId);
            psql.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*删除2*/
    @Override
    public void delete(int id,int beid){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "delete from black where id=? and beid=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            psql.setInt(2,beid);
            psql.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

}
