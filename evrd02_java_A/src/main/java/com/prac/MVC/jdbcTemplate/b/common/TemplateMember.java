package com.prac.MVC.jdbcTemplate.b.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TemplateMember {


    public static Connection con = null;
    public TemplateMember(){}

    public static Connection getConnectionInner(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","JSPServlet","qrwe");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return con;
    }

    public static Connection getConnectionOuter(){
        try{
            Properties prop = new Properties();
            String filePath = "src/main/java/com/prac/MVC/jdbcTemplate/b/config/memberDatabase.properties";
            prop.load(new FileReader(filePath));

            Class.forName(prop.getProperty("driver"));
            con = DriverManager.getConnection(prop.getProperty("url"),
                                                prop.getProperty("username"),
                                                prop.getProperty("password")
                    );
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return con;
    }



    public static void commit(Connection con){
        try{
            if(con != null && !con.isClosed()){
                con.commit();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void rollback(Connection con){
        try{
            if(con != null && !con.isClosed()){
                con.rollback();
            }
        }catch(SQLException e){
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
    public static void close(Statement stmt){
        try{
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
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




}
