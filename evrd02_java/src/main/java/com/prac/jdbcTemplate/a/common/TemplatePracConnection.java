package com.prac.MVC.jdbcTemplate.a.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TemplatePracConnection {

    public static Connection con = null;

    private TemplatePracConnection(){}


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

    public static Connection getConnection3(){

        if (con != null) {
            try{
                Class.forName("driver");
                con = DriverManager.getConnection("jdbc:oracle:thin@127.0.0.1:1521:xe","JSPServelt","qrwe");
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return con;
    }


    public static Connection getConnection4(){
        if(con != null){

            try {
                Properties prop = new Properties();
                prop.load(new FileReader("datebase.properties"));

                Class.forName(prop.getProperty("driver"));
                con = DriverManager.getConnection(prop.getProperty("url"),
                        prop.getProperty("username"),
                        prop.getProperty("password"));
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return con;
    }

    public static Connection getConnection5(){
        if(con != null){
            try{
                Class.forName("oracle.jdbc.driver.Oracledriver");
                con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JSPServlet","qrwe");

            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return con;
    }


    public static Connection getConnection6(){
        if(con != null) {

            try {
                Properties p = new Properties();
                p.load(new FileReader("database.properties"));

                Class.forName(p.getProperty("driver"));
                con = DriverManager.getConnection(p.getProperty("url"),
                                                p.getProperty("account"),
                                                p.getProperty("pw"));

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return con;
    }

    public static Connection getConenction7(){
        if(con != null){
            try{
                Properties pro = new Properties();
                pro.load(new FileReader("dbConfig.properties"));

                Class.forName(pro.getProperty("driver"));
                con = DriverManager.getConnection(pro.getProperty("url"),
                                                   pro.getProperty("account"),
                                                   pro.getProperty("pw"));
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return con;
    }


    public static Connection getConnection8(){
        if(con != null){
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","JSPServlet","qrwe");

            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return con;
    }

    public static Connection getConnection9(){
        if(con != null){
            try{
                Properties prob = new Properties();
                prob.load(new FileReader("resources/database.properties"));

                Class.forName(prob.getProperty("driver"));
                con = DriverManager.getConnection(
                                prob.getProperty("url"),
                                prob.getProperty("username"),
                                prob.getProperty("password")
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
            if(con != null){
                con.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void stmt(Statement stmt){
        try{
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    public static void close2(Connection con){
        try{
            if(con != null && !con.isClosed()){
                con.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void close3(Connection conn) {
        try {
            if(conn != null && !conn.isClosed()) { // conn.isClosed()은 SQLException 필요함
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close4(Connection con){
        try{
            if(con != null && !con.isClosed()){
                con.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void close5(Statement stmt){
        try{
            if(stmt != null && !stmt.isClosed()){
                con.close();
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


