package com.hwh.www.dao;

import com.hwh.www.po.User;
import com.hwh.www.until.DButil;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    /*遍历数据*/
    @Override
    public List<User> loadData(){
        User data = null;
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<User>();
        try{
            conn = DButil.theSqlConnection();
            statement = conn.createStatement();
            String sql = "select * from `user`";
            rs = statement.executeQuery(sql);
            data = null;
            while (rs.next()){
                data = new User();
                data.setId(rs.getInt("id"));
                data.setEmail(rs.getString("email"));
                data.setUname(rs.getString("uname"));
                data.setPassword(rs.getString("password"));
                data.setPower(rs.getString("power"));
                data.setSub(rs.getInt("sub"));
                data.setFan(rs.getInt("fan"));
                data.setArtical(rs.getInt("artical"));
                data.setLove(rs.getInt("love"));
                data.setImage(rs.getBlob("image"));
                data.setBan(rs.getString("ban"));
                userList.add(data);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,statement,conn);
        }
        return userList;
    }

    /*根据获得喜欢排序*/
    @Override
    public List<User> getLove(){
        User data = null;
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<User>();
        try{
            conn = DButil.theSqlConnection();
            statement = conn.createStatement();
            String sql = "select * from `user` order by love DESC limit 0,5";
            rs = statement.executeQuery(sql);
            data = null;
            while (rs.next()){
                data = new User();
                data.setId(rs.getInt("id"));
                data.setEmail(rs.getString("email"));
                data.setUname(rs.getString("uname"));
                data.setPassword(rs.getString("password"));
                data.setPower(rs.getString("power"));
                data.setSub(rs.getInt("sub"));
                data.setFan(rs.getInt("fan"));
                data.setArtical(rs.getInt("artical"));
                data.setLove(rs.getInt("love"));
                data.setImage(rs.getBlob("image"));
                data.setBan(rs.getString("ban"));
                userList.add(data);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,statement,conn);
        }
        return userList;
    }

    /*插入*/
    @Override
    public void insert(User user){
        User data = null;
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "insert into `user`(email,uname,password,power,sub,fan,artical,love,image) values (?,?,?,?,?,?,?,?,?)";
            psql = conn.prepareStatement(sql);
            psql.setString(1,user.getEmail());
            psql.setString(2,user.getUname());
            psql.setString(3,user.getPassword());
            psql.setString(4,user.getPower());
            psql.setInt(5,user.getSub());
            psql.setInt(6,user.getFan());
            psql.setInt(7,user.getArtical());
            psql.setInt(8,user.getLove());
            psql.setBlob(9,user.getImage());
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
    }

    /*查询*/
    @Override
    public User findById(int id) {
        User data = null;
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try {
            conn = DButil.theSqlConnection();
            psql = conn.prepareStatement("SELECT * FROM `user` where id = ?");
            psql.setInt(1, id);
            rs = psql.executeQuery();
            while (rs.next()) {
                data = new User();
                data.setId(rs.getInt("id"));
                data.setEmail(rs.getString("email"));
                data.setUname(rs.getString("uname"));
                data.setPassword(rs.getString("password"));
                data.setPower(rs.getString("power"));
                data.setSub(rs.getInt("sub"));
                data.setFan(rs.getInt("fan"));
                data.setArtical(rs.getInt("artical"));
                data.setLove(rs.getInt("love"));
                data.setImage(rs.getBlob("image"));
                data.setBan(rs.getString("ban"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return data;
    }


    /*修改密码*/
    @Override
    public void changePassword(String email,String password){
        User data = null;
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "update `user` set password=? where email=? ";
            psql = conn.prepareStatement(sql);
            psql.setString(1,password);
            psql.setString(2,email);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
    }

    /*更新*/
    @Override
    public void update(User user){
        User data = null;
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "update `user` set email=?,uname=?,password=?,power=?,sub=?,fan=?,artical=?,love=?,image=?,ban=? where id=? ";
            psql = conn.prepareStatement(sql);
            psql.setString(1,user.getEmail());
            psql.setString(2,user.getUname());
            psql.setString(3,user.getPassword());
            psql.setString(4,user.getPower());
            psql.setInt(5,user.getSub());
            psql.setInt(6,user.getFan());
            psql.setInt(7,user.getArtical());
            psql.setInt(8,user.getLove());
            psql.setBlob(9,user.getImage());
            psql.setString(10,user.getBan());
            psql.setInt(11,user.getId());
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
    }

    /*上传图片*/
    @Override
    public void updatePhoto(InputStream input,int id){
        User data = null;
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "update `user` set image=? where id=? ";
            psql = conn.prepareStatement(sql);
            psql.setBinaryStream(1,input,input.available());
            psql.setInt(2,id);
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
    }

}
