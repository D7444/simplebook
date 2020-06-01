package com.hwh.www.dao;

import com.hwh.www.po.Message;
import com.hwh.www.until.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl implements MessageDao{
    /*寻找用户简信列表*/
    @Override
    public List<Message> getMessage(int messageId){
        List<Message> groupList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from message where messageId=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,messageId);
            rs = psql.executeQuery();
            Message group = null;
            while (rs.next()){
                group = new Message();
                group.setMessageId(rs.getInt("messageId"));
                group.setId(rs.getInt("id"));
                group.setToid(rs.getInt("toid"));
                group.setContent(rs.getString("content"));
                group.setTime(rs.getString("time"));
                groupList.add(group);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return groupList;
    }

    /*获取全部简信记录*/
    @Override
    public List<Message> getAll(){
        List<Message> groupList = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from message";
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            Message group = null;
            while (rs.next()){
                group = new Message();
                group.setMessageId(rs.getInt("messageId"));
                group.setId(rs.getInt("id"));
                group.setToid(rs.getInt("toid"));
                group.setContent(rs.getString("content"));
                group.setTime(rs.getString("time"));
                groupList.add(group);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,statement,conn);
        }
        return groupList;
    }

    /*获取简信记录*/
    @Override
    public List<Message> getUser(){
        List<Message> groupList = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from headmessage order by `time` DESC";
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            Message group = null;
            while (rs.next()){
                group = new Message();
                group.setMessageId(rs.getInt("messageId"));
                group.setId(rs.getInt("id"));
                group.setToid(rs.getInt("toid"));
                group.setContent(rs.getString("content"));
                group.setTime(rs.getString("time"));
                groupList.add(group);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,statement,conn);
        }
        return groupList;
    }

    /*增加简信*/
    @Override
    public void addMessage(int messageId,int id,int toid,String content,String time){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "insert into message(messageId,id,toid,content,`time`) values (?,?,?,?,?)";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,messageId);
            psql.setInt(2,id);
            psql.setInt(3,toid);
            psql.setString(4,content);
            psql.setString(5,time);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*增加对话状态*/
    @Override
    public void addSession(int id,int toid,String content,String time){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "insert into headmessage(id,toid,content,`time`) values (?,?,?,?)";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            psql.setInt(2,toid);
            psql.setString(3,content);
            psql.setString(4,time);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*更新最新状态*/
    @Override
    public void updateMessage(int messageId,String content,String time){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "update headmessage set content=?,`time`=? where messageId=?";
            psql = conn.prepareStatement(sql);
            psql.setString(1,content);
            psql.setString(2,time);
            psql.setInt(3,messageId);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*删除*/
    @Override
    public void delete(int messageId){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            //删除对话
            String sql = "delete from headmessage where messageId=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,messageId);
            psql.execute();
            //删除记录
            sql = "delete from message where messageId=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,messageId);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

}
