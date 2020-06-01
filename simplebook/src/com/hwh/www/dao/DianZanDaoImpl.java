package com.hwh.www.dao;

import com.hwh.www.po.DianZan;
import com.hwh.www.until.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DianZanDaoImpl implements DianZanDao {
    /*获取某人的点赞*/
    @Override
    public List<DianZan> getUserDz(int id){
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        List<DianZan> dianZanList = new ArrayList<>();
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from dianzan where id=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            rs = psql.executeQuery();
            DianZan dianZan = null;
            while (rs.next()){
                dianZan = new DianZan();
                dianZan.setId(rs.getInt("id"));
                dianZan.setWzid(rs.getInt("wzid"));
                dianZan.setPlid(rs.getInt("plid"));
                dianZan.setTime(rs.getString("time"));
                dianZanList.add(dianZan);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return dianZanList;
    }

    /*获取点赞数*/
    @Override
    public List<DianZan> getDianZan(int wzid,int plid){
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        List<DianZan> dianZanList = new ArrayList<>();
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from dianzan where wzid=? and plid=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,wzid);
            psql.setInt(2,plid);
            rs = psql.executeQuery();
            DianZan dianZan = null;
            while (rs.next()){
                dianZan = new DianZan();
                dianZan.setId(rs.getInt("id"));
                dianZan.setWzid(rs.getInt("wzid"));
                dianZan.setPlid(rs.getInt("plid"));
                dianZan.setTime(rs.getString("time"));
                dianZanList.add(dianZan);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return dianZanList;
    }

    /*增加*/
    @Override
    public void add(int id,int wzid, int plid,String time){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "insert into dianzan(id,wzid,plid,time) values (?,?,?,?)";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            psql.setInt(2,wzid);
            psql.setInt(3,plid);
            psql.setString(4,time);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*删除*/
    @Override
    public void delete(int id,int wzid, int plid){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "delete from dianzan where id=? and wzid=? and plid=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            psql.setInt(2,wzid);
            psql.setInt(3,plid);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*删除文章相关点赞*/
    @Override
    public void deleteWzDz(int wzid){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "delete from dianzan where wzid=?";
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
