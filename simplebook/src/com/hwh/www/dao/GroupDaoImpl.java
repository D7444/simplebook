package com.hwh.www.dao;

import com.hwh.www.po.Group;
import com.hwh.www.until.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl implements GroupDao {
    /*获取用户自定义列表*/
    @Override
    public List<Group> getGroup(int id){
        List<Group> groupList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from grouplist where id=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            rs = psql.executeQuery();
            Group group = null;
            while (rs.next()){
                group = new Group();
                group.setGroupId(rs.getInt("groupId"));
                group.setId(id);
                group.setGroup(rs.getString("group"));
                groupList.add(group);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return groupList;
    }

    /*寻找*/
    @Override
    public Group findById(int groupId){
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        Group group = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "select * from grouplist where groupId=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,groupId);
            rs = psql.executeQuery();

            while (rs.next()){
                group = new Group();
                group.setGroupId(rs.getInt("groupId"));
                group.setId(rs.getInt("id"));
                group.setGroup(rs.getString("group"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return group;
    }

    /*增加自定义列表*/
    @Override
    public void add(int id,String group){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "insert into grouplist(id,`group`) values (?,?)";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            psql.setString(2,group);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*删除自定义列表*/
    @Override
    public void delete(int groupId){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "delete from grouplist where groupId=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,groupId);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*更新列表*/
    @Override
    public void update(int groupId,String group){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "update grouplist set `group`=? where groupId=?";
            psql = conn.prepareStatement(sql);
            psql.setString(1,group);
            psql.setInt(2,groupId);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

}
