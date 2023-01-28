package com.prac.MVC.jdbcTemplate.e.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class CommonTemplate {

    public CommonTemplate(){}

    public static Connection connection = null;


    public static Connection getConnection(Connection connection){

        if(connection == null) {
            try {
                Properties properties = new Properties();
                properties.load(new FileReader(""));

                Class.forName(properties.getProperty("driver"));
                connection = DriverManager.getConnection(properties.getProperty("url"),
                                                    properties.getProperty("username"),
                                                    properties.getProperty("password")
                        );
            }catch(FileNotFoundException e) {
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }


    public static void commit(Connection connection){

        try{
            if(connection != null && !connection.isClosed()) {
                connection.commit();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void rollback(Connection connection){
        try{
            if(connection != null && !connection.isClosed()){
                connection.rollback();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }



    public static void close(ResultSet resultSet){
        try{
            if(resultSet != null && resultSet.isClosed()){
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
    public static void close(Connection connection){
        try {
            if (connection != null && !connection.isClosed()) {
                connection.isClosed();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

// 익셉션 클래스 써볼 수 있는 방법 없을까???

    // throw new ExceptionChecked();
    // Checked Exception & Unchecked Exception
    // 		Checked Exception : 예외처리가 필수
    // 		Unchecked Exception : 예외처리 선택

    // Unchecked Exception의 조상 Runtime Exception
    // Checked Exception의 조상은 Exception


}
