package com.prac.MVC.jdbcTemplate.a.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        if(con != null){
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

        if(con != null){
            try{
                Properties prop = new Properties();
                prop.load(new FileReader("database.properties"));

                Class.forName("driver");
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


    public static void commit(Connection con){
        try {
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





}
