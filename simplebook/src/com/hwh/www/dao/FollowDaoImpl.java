package com.hwh.www.dao;

import com.hwh.www.po.Follow;
import com.hwh.www.until.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FollowDaoImpl implements FollowDao{
    /*查询关注*/
    @Override
    public List<Follow> findData(int sub){
        Follow follow = null;
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        List<Follow> followList = new ArrayList<>();
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from follow where sub=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,sub);
            rs = psql.executeQuery();
            while (rs.next()){
                follow = new Follow();
                follow.setSub(rs.getInt("sub"));
                follow.setBesub(rs.getInt("besub"));
                follow.setTime(rs.getString("time"));
                followList.add(follow);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs, psql,conn);
        }
        return followList;
    }

    /*查询关注*/
    @Override
    public List<Follow> findBeData(int besub){
        Follow follow = null;
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        List<Follow> followList = new ArrayList<>();
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from follow where besub=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,besub);
            rs = psql.executeQuery();
            while (rs.next()){
                follow = new Follow();
                follow.setSub(rs.getInt("sub"));
                follow.setBesub(rs.getInt("besub"));
                follow.setTime(rs.getString("time"));
                followList.add(follow);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs, psql,conn);
        }
        return followList;
    }

    /*增加*/
    @Override
    public void add(Follow follow){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "insert into follow(sub,besub,`time`) values (?,?,?)";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,follow.getSub());
            psql.setInt(2,follow.getBesub());
            psql.setString(3,follow.getTime());
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*删除*/
    @Override
    public void delete(Follow follow){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "delete from follow where sub=? and besub=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,follow.getSub());
            psql.setInt(2,follow.getBesub());
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }


    public static void main(String args[]){
    }
}
