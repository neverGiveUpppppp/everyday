package com.prac.MVC.jdbcTemplate.a.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TemplateJDBC {

    public static Connection con = null;

    private TemplateJDBC(){}


    // 내부 연결 방식
    public static Connection getConnection1(){

        if(con == null){
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MEMBER","qrwe");

            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return con;
    }


    // 외부파일 연결 방식
    public static Connection getConnection2(){

        if(con == null){
            try {
                Properties prop = new Properties();
                prop.load(new FileReader("resources/dbDriver.properties"));

                Class.forName(prop.getProperty("dbDriver"));
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return con;

    }



}
