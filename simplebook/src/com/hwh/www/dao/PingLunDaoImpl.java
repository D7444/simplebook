package com.hwh.www.dao;

import com.hwh.www.po.PingLun;
import com.hwh.www.until.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PingLunDaoImpl implements PingLunDao {


    /*输出文章id对应评论*/
    @Override
    public List<PingLun> findPl(int wzid){
        PingLun pingLun = null;
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        List<PingLun> pingLunList = new ArrayList<>();
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from pinglun where wzid=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,wzid);
            rs = psql.executeQuery();
            while (rs.next()){
                pingLun = new PingLun();
                pingLun.setWzid(rs.getInt("wzid"));
                pingLun.setPlid(rs.getInt("plid"));
                pingLun.setId(rs.getInt("id"));
                pingLun.setDianzan(rs.getInt("dianzan"));
                pingLun.setFatherid(rs.getInt("fatherid"));
                pingLun.setContent(rs.getString("content"));
                pingLun.setTime(rs.getString("time"));
                pingLunList.add(pingLun);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs, psql,conn);
        }
        return pingLunList;
    }

    /*寻找评论*/
    @Override
    public PingLun findId(int plid){
        PingLun pingLun = null;
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from pinglun where plid=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,plid);
            rs = psql.executeQuery();
            while (rs.next()){
                pingLun = new PingLun();
                pingLun.setWzid(rs.getInt("wzid"));
                pingLun.setPlid(rs.getInt("plid"));
                pingLun.setId(rs.getInt("id"));
                pingLun.setDianzan(rs.getInt("dianzan"));
                pingLun.setFatherid(rs.getInt("fatherid"));
                pingLun.setContent(rs.getString("content"));
                pingLun.setTime(rs.getString("time"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs, psql,conn);
        }
        return pingLun;
    }

    /*寻找某人的评论记录*/
    @Override
    public List<PingLun> foundPl(int id){
        List<PingLun> pingLunList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from pinglun where id=? order by `time` DESC";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            rs = psql.executeQuery();
            PingLun pingLun = null;
            while (rs.next()){
                pingLun = new PingLun();
                pingLun.setWzid(rs.getInt("wzid"));
                pingLun.setPlid(rs.getInt("plid"));
                pingLun.setId(rs.getInt("id"));
                pingLun.setDianzan(rs.getInt("dianzan"));
                pingLun.setFatherid(rs.getInt("fatherid"));
                pingLun.setContent(rs.getString("content"));
                pingLun.setTime(rs.getString("time"));
                pingLunList.add(pingLun);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs, psql,conn);
        }
        return pingLunList;
    }

    /*更新*/
    @Override
    public void update(PingLun pingLun){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "update pinglun set wzid=?,id=?,dianzan=?,fatherid=?,content=?,`time`=? where plid=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,pingLun.getWzid());
            psql.setInt(2,pingLun.getId());
            psql.setInt(3,pingLun.getDianzan());
            psql.setInt(4,pingLun.getFatherid());
            psql.setString(5,pingLun.getContent());
            psql.setString(6,pingLun.getTime());
            psql.setInt(7,pingLun.getPlid());
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*增加*/
    @Override
    public void add(PingLun pingLun){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "insert into pinglun(wzid,id,dianzan,fatherid,content,`time`) values (?,?,?,?,?,?)";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,pingLun.getWzid());
            psql.setInt(2,pingLun.getId());
            psql.setInt(3,pingLun.getDianzan());
            psql.setInt(4,pingLun.getFatherid());
            psql.setString(5,pingLun.getContent());
            psql.setString(6,pingLun.getTime());
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*删除*/
    @Override
    public void delete(int plid){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "delete from pinglun where plid=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,plid);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*删除文章的所有评论*/
    @Override
    public void deleteWzPl(int wzid){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "delete from pinglun where wzid=?";
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
