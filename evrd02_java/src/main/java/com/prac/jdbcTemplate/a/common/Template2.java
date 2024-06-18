package com.prac.MVC.jdbcTemplate.a.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Queue;

public class Template2 {

    public static Connection con = null;

    public Template2(){}

    public static Connection getConnectionEx(){
        if(con == null) {
            try {
                Properties prop = new Properties();
                prop.load(new FileReader("D:\\development\\practice\\workspace_00_evrd\\evrd02_java_A\\src\\main\\java\\com\\prac\\MVC\\jdbcTemplate\\a\\resources\\database.properties"
                ));

                Class.forName(prop.getProperty("driver"));
                con = DriverManager.getConnection(prop.getProperty("url"),
                                                    prop.getProperty("username"),
                                                    prop.getProperty("password")
                                            );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }



    // transaction
    public static void commit(Connection con){
        try{
            if(con != null && !con.isClosed()) {
                con.commit();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void rollback(Connection con) {
        try{
            if(con != null && !con.isClosed()){
                con.rollback();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }



    // close
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
    public static void close(ResultSet rset){
        try{
            if(rset != null && !rset.isClosed()){
                rset.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }





}
