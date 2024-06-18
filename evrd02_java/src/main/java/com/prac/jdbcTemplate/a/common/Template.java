package com.prac.MVC.jdbcTemplate.a.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Template {

    /*
        Connection 연결
        transaction commit
        transaction rollback
        close Connection
              stmt
              ResultSet
     */

    public static Connection con = null;

    public static Connection getConnectionInner(){
        if(con == null){
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JSPServlet","qrwe");
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return con;
    }


    public static Connection getConnectionOuter(){

        if(con == null){
            try{
                Properties prop = new Properties();
                String filePath = "src/main/java/com/prac/MVC/jdbcTemplate/a/resources/database.properties";
                prop.load(new FileReader(filePath));

                Class.forName(prop.getProperty("driver"));
                con = DriverManager.getConnection(prop.getProperty("url"),
                                                  prop.getProperty("username"),
                                                  prop.getProperty("password")
                                                );

            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        return con;
    }


    public static void commit(Connection conn) {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void rollback(Connection conn) {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void close(Connection con){
        try{
            if(con != null && !con.isClosed()){
                con.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void close(ResultSet resultSet){
        try{
            if(resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void close(Statement stmt){
        try{
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


}
