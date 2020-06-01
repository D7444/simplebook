package com.hwh.www.dao;

import com.hwh.www.po.Collect;
import com.hwh.www.until.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollectDaoImpl implements CollectDao{
    /*获取收藏*/
    @Override
    public List<Collect> getCollect(int id){
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        List<Collect> collectList = new ArrayList<>();
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from collect where id=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            rs = psql.executeQuery();
            Collect collect = null;
            while (rs.next()){
                collect = new Collect();
                collect.setId(rs.getInt("id"));
                collect.setWzid(rs.getInt("wzid"));
                collect.setTime(rs.getString("time"));
                collectList.add(collect);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return collectList;
    }

    /*增加*/
    @Override
    public void add(int id,int wzid,String time){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "insert into collect(id,wzid,`time`) values (?,?,?)";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            psql.setInt(2,wzid);
            psql.setString(3,time);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*删除*/
    @Override
    public void delete(int id,int wzid){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "delete from collect where id=? and wzid=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            psql.setInt(2,wzid);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*删除文章相关收藏*/
    @Override
    public void deleteWzCl(int wzid){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "delete from collect where wzid=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,wzid);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }
}
