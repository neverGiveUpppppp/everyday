package com.prac.MVC.jdbcTemplate.d.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TemplateMember {

    public static Connection conn = null;

    public TemplateMember(){}

    /*
    <Template class 구성>
    * getConnection
    * transaction
    * close objects
    *
    * */

    public static Connection getConnect(){
        if(conn == null){
            try{
            Properties prop = new Properties();
            prop.load(new FileReader("src/main/java/com/prac/MVC/jdbcTemplate/d/common/config/memberDatabase.properties"));
            Class.forName(prop.getProperty("driver"));
            conn = DriverManager.getConnection(prop.getProperty("url"),
                                                prop.getProperty("username"),
                                                prop.getProperty("password")
                        );
            }catch(FileNotFoundException e) {
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return conn;
    }



    public void commit(Connection conn){
        try{
            if(conn != null && !conn.isClosed()) {
                conn.commit();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void rollback(Connection conn) {
        try{
            if(conn != null && !conn.isClosed()){
                conn.rollback();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    public void close(ResultSet resultSet){
        try{
            if(resultSet != null && resultSet.isClosed()){
                resultSet.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void close(Statement stmt){
        try{
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void close(Connection conn){
        try{
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
