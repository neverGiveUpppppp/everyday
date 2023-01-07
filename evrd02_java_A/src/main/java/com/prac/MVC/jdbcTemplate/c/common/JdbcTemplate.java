package com.prac.MVC.jdbcTemplate.c.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcTemplate {

    public static Connection conn = null;

    public JdbcTemplate(){}

    public static Connection getConnection(){
        if(conn == null){
            try {
                Properties prop = new Properties();
                prop.load(new FileReader("D:\\Dropbox\\Developer\\workspace_00_evrd\\evrd02_java_A\\src\\main\\java\\com\\prac\\MVC\\jdbcTemplate\\c\\common\\config\\memberDatabase.properties"
                ));
                // classpath 설정
                // 클래스 리소스 리더(스프링)

                Class.forName(prop.getProperty("driver"));
                conn = DriverManager.getConnection(prop.getProperty("url"),
                                                prop.getProperty("username"),
                                                prop.getProperty("password"));
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



    public static Connection commit(Connection conn){
        try{
            if(conn != null && !conn.isClosed()){
                conn.commit();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    public static Connection rollback(Connection conn){
        try{
            if(conn != null && conn.isClosed()){
                conn.rollback();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }


}
