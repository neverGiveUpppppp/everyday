package com.prac.MVC.jdbcTemplate.a.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Template3 {

    public static Connection conn = null;

    public Template3(){}

    public static Connection getConnection(){

        try{
            if(conn == null){
                Properties prop = new Properties();
                prop.load(new FileReader("path.properties"));
                Class.forName(prop.getProperty("driver"));
                conn = DriverManager.getConnection(prop.getProperty("url"),
                            prop.getProperty("username"),
                            prop.getProperty("passsword")
                        );

            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnect() {
        try{
            Properties prob = new Properties();
            prob.load(new FileReader(".properties"));

            Class.forName(prob.getProperty("Driver"));
            conn = DriverManager.getConnection(prob.getProperty("url"));
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

}
