package com.hwh.www.until;

import java.sql.*;

public class DButil {
    //数据库地址
    private static final String URL = "jdbc:mysql://localhost:3306/simbook?serverTimezone=GMT%2B8&useSSL=false";
    //用户名
    private static final String NAME = "root";
    //密码
    private static final String PASSWORD = "admin";
    public static Connection conn = null;

    /*连接数据库*/
    public static Connection theSqlConnection(){
        // 1.加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("未能成功加载驱动程序，请检查是否导入驱动程序！");
        }
        //2.连接数据库
        try {
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("获取数据库连接失败！");
        }
        return conn;
    }

    /*关闭资源*/
    public static void close(Statement statement,Connection conn){
        try {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /*关闭资源*/
    public static void closeConnection(ResultSet rs, Statement statement,Connection conn){
        try {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                    conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
