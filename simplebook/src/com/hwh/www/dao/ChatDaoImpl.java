package com.hwh.www.dao;

import com.hwh.www.po.Chat;
import com.hwh.www.until.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatDaoImpl implements ChatDao {
    /*寻找用户聊天列表*/
    @Override
    public List<Chat> getChat(int id,int toid){
        List<Chat> groupList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from chat where (id=? and toid=?) or (toid=? and id=?)";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            psql.setInt(2,toid);
            psql.setInt(3,id);
            psql.setInt(4,toid);
            rs = psql.executeQuery();
            Chat group = null;
            while (rs.next()){
                group = new Chat();
                group.setChatId(rs.getInt("chatId"));
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

    /*获取全部聊天记录*/
    @Override
    public List<Chat> getAll(){
        List<Chat> groupList = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from chat";
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            Chat group = null;
            while (rs.next()){
                group = new Chat();
                group.setChatId(rs.getInt("chatId"));
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

    /*增加聊天信息*/
    @Override
    public void addChat(int id,int toid,String content,String time){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "insert into chat(id,toid,content,`time`) values (?,?,?,?)";
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

}
