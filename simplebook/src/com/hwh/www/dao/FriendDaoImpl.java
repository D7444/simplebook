package com.hwh.www.dao;

import com.hwh.www.po.Friend;
import com.hwh.www.until.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendDaoImpl implements FriendDao {
    /*获取好友列表*/
    @Override
    public List<Friend> getFriend(int id){
        List<Friend> friendList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from friend where id=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            rs = psql.executeQuery();
            Friend friend = null;
            while (rs.next()){
                friend = new Friend();
                friend.setId(rs.getInt("id"));
                friend.setFreid(rs.getInt("freid"));
                friend.setGroup(rs.getString("group"));
                friendList.add(friend);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return friendList;
    }

    /*增加好友*/
    @Override
    public void add(int id,int freid){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "insert into friend(id,freid,`group`) values (?,?,?)";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            psql.setInt(2,freid);
            psql.setString(3,"默认列表");
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*删除好友*/
    @Override
    public void delete(int id,int freid){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "delete from friend where id=? and freid=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            psql.setInt(2,freid);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
   }

    /*更新*/
    @Override
    public void update(int id,int freid,String group){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "update friend set `group`=? where id=? and freid=?";
            psql = conn.prepareStatement(sql);
            psql.setString(1,group);
            psql.setInt(2,id);
            psql.setInt(3,freid);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*更新*/
    @Override
    public void updateMore(int id,String group,String newgroup){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "update friend set `group`=? where id=? and `group`=?";
            psql = conn.prepareStatement(sql);
            psql.setString(1,newgroup);
            psql.setInt(2,id);
            psql.setString(3,group);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }
}
