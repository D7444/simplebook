package com.hwh.www.dao;

import com.hwh.www.po.WenZhang;
import com.hwh.www.until.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WenZhangDaoImol implements WenZhangDao {
    /*获取数据数*/
    @Override
    public int getCount(){
        int totalCount = 0;
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "select count(*) from wenzhang";
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery(sql);
            rs.next();
            totalCount = rs.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,statement,conn);
        }
        return totalCount;
    }

    /*分页*/
    @Override
    public List<WenZhang> getPage(int start,int end) {
        WenZhang data = null;
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        List<WenZhang> wenZhangList = new ArrayList<>();
        try{
            conn = DButil.theSqlConnection();
            String sql = "SELECT * from wenzhang order by `time` DESC limit ?,?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,start);
            psql.setInt(2,end);
            rs = psql.executeQuery();
            data = null;
            while (rs.next()){
                data = new WenZhang();
                data.setWzid(rs.getInt("wzid"));
                data.setType(rs.getString("type"));
                data.setTitle(rs.getString("title"));
                data.setBreif(rs.getString("breif"));
                data.setContent(rs.getString("content"));
                data.setId(rs.getInt("id"));
                data.setDianzan(rs.getInt("dianzan"));
                data.setShoucang(rs.getInt("shoucang"));
                data.setTime(rs.getString("time"));
                data.setHot(rs.getString("hot"));
                wenZhangList.add(data);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return wenZhangList;
    }

    /*遍历*/
    @Override
    public List<WenZhang> loadData() {
        WenZhang data = null;
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        List<WenZhang> wenZhangList = new ArrayList<>();
        try{
            conn = DButil.theSqlConnection();
            statement = conn.createStatement();
            String sql = "SELECT * from wenzhang order by `time` DESC";
            rs = statement.executeQuery(sql);
            data = null;
            while (rs.next()){
                data = new WenZhang();
                data.setWzid(rs.getInt("wzid"));
                data.setType(rs.getString("type"));
                data.setTitle(rs.getString("title"));
                data.setBreif(rs.getString("breif"));
                data.setContent(rs.getString("content"));
                data.setId(rs.getInt("id"));
                data.setDianzan(rs.getInt("dianzan"));
                data.setShoucang(rs.getInt("shoucang"));
                data.setTime(rs.getString("time"));
                data.setHot(rs.getString("hot"));
                wenZhangList.add(data);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,statement,conn);
        }
        return wenZhangList;
    }

    /*查询*/
    @Override
    public WenZhang findById(int wzid){
        WenZhang data = null;
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "SELECT * from wenzhang where wzid=?";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,wzid);
            rs = psql.executeQuery();
            data = null;
            while (rs.next()){
                data = new WenZhang();
                data.setWzid(rs.getInt("wzid"));
                data.setType(rs.getString("type"));
                data.setTitle(rs.getString("title"));
                data.setBreif(rs.getString("breif"));
                data.setContent(rs.getString("content"));
                data.setId(rs.getInt("id"));
                data.setDianzan(rs.getInt("dianzan"));
                data.setShoucang(rs.getInt("shoucang"));
                data.setTime(rs.getString("time"));
                data.setHot(rs.getString("hot"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return data;
    }

    /*查询热门*/
    @Override
    public WenZhang findHot(String hot){
        WenZhang data = null;
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "SELECT * from wenzhang where hot=?";
            psql = conn.prepareStatement(sql);
            psql.setString(1,hot);
            rs = psql.executeQuery();
            while (rs.next()){
                data = new WenZhang();
                data.setWzid(rs.getInt("wzid"));
                data.setType(rs.getString("type"));
                data.setTitle(rs.getString("title"));
                data.setBreif(rs.getString("breif"));
                data.setContent(rs.getString("content"));
                data.setId(rs.getInt("id"));
                data.setDianzan(rs.getInt("dianzan"));
                data.setShoucang(rs.getInt("shoucang"));
                data.setTime(rs.getString("time"));
                data.setHot(rs.getString("hot"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return data;
    }

    /*查询类型文章*/
    @Override
    public List<WenZhang> findType(String type){
        WenZhang data = null;
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        List<WenZhang> wenZhangList = new ArrayList<>();
        try{
            conn = DButil.theSqlConnection();
            String sql = "SELECT * from wenzhang where `type`=? order by `dianzan` DESC limit 0,5";
            psql = conn.prepareStatement(sql);
            psql.setString(1,type);
            rs = psql.executeQuery();
            while (rs.next()){
                data = new WenZhang();
                data.setWzid(rs.getInt("wzid"));
                data.setType(rs.getString("type"));
                data.setTitle(rs.getString("title"));
                data.setBreif(rs.getString("breif"));
                data.setContent(rs.getString("content"));
                data.setId(rs.getInt("id"));
                data.setDianzan(rs.getInt("dianzan"));
                data.setShoucang(rs.getInt("shoucang"));
                data.setTime(rs.getString("time"));
                data.setHot(rs.getString("hot"));
                wenZhangList.add(data);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return wenZhangList;
    }

    /*查询个人多篇文章*/
    @Override
    public List<WenZhang> findMore(int id){
        WenZhang data = null;
        Connection conn = null;
        PreparedStatement psql = null;
        ResultSet rs = null;
        List<WenZhang> wenZhangList = new ArrayList<>();
        try{
            conn = DButil.theSqlConnection();
            String sql = "SELECT * from wenzhang where id=? order by `time` DESC";
            psql = conn.prepareStatement(sql);
            psql.setInt(1,id);
            rs = psql.executeQuery();
            while (rs.next()){
                data = new WenZhang();
                data.setWzid(rs.getInt("wzid"));
                data.setType(rs.getString("type"));
                data.setTitle(rs.getString("title"));
                data.setBreif(rs.getString("breif"));
                data.setContent(rs.getString("content"));
                data.setId(rs.getInt("id"));
                data.setDianzan(rs.getInt("dianzan"));
                data.setShoucang(rs.getInt("shoucang"));
                data.setTime(rs.getString("time"));
                data.setHot(rs.getString("hot"));
                wenZhangList.add(data);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(rs,psql,conn);
        }
        return wenZhangList;
    }

    /*更新*/
    @Override
    public void update(WenZhang wenZhang){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "update wenzhang set `type`=?,title=?,content=?,id=?,dianzan=?,shoucang=?,`time`=?,breif=?,hot=? where wzid=?";
            psql = conn.prepareStatement(sql);
            psql.setString(1,wenZhang.getType());
            psql.setString(2,wenZhang.getTitle());
            psql.setString(3,wenZhang.getContent());
            psql.setInt(4,wenZhang.getId());
            psql.setInt(5,wenZhang.getDianzan());
            psql.setInt(6,wenZhang.getShoucang());
            psql.setString(7,wenZhang.getTime());
            psql.setString(8,wenZhang.getBreif());
            psql.setString(9,wenZhang.getHot());
            psql.setInt(10,wenZhang.getWzid());
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*增加*/
    @Override
    public void add(WenZhang wenZhang){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "insert into wenzhang(`type`,title,content,id,dianzan,shoucang,`time`,breif) values (?,?,?,?,?,?,?,?)";
            psql = conn.prepareStatement(sql);
            psql.setString(1,wenZhang.getType());
            psql.setString(2,wenZhang.getTitle());
            psql.setString(3,wenZhang.getContent());
            psql.setInt(4,wenZhang.getId());
            psql.setInt(5,wenZhang.getDianzan());
            psql.setInt(6,wenZhang.getShoucang());
            psql.setString(7,wenZhang.getTime());
            psql.setString(8,wenZhang.getBreif());
            psql.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(psql,conn);
        }
    }

    /*删除*/
    @Override
    public void delete(int wzid){
        Connection conn = null;
        PreparedStatement psql = null;
        try{
            conn = DButil.theSqlConnection();
            String sql = "delete from wenzhang where wzid=?";
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
