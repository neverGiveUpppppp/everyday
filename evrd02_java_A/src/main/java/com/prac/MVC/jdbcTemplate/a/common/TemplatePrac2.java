package main.java.com.prac.MVC.jdbcTemplate.a.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TemplatePrac2 {

    public static Connection con = null;

    private TemplatePrac2(){}



    /*
        Connection : driver, 접속정보
        transaction : commit(), rollback()
        close : stmt, rset,con

     */


    public static Connection getConnectionInner(){
        if(con == null){
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

    public static Connection getConnectionOuter(){
        if(con == null){
            try {
                Properties prop = new Properties();
                prop.load((new FileReader("driver.properties")));

                Class.forName("driver");
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
            if (con != null && !con.isClosed()) {
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
            if(resultSet != null && resultSet.isClosed()){
                resultSet.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    public static Connection getConnection(){
        if(con == null) {
            try {
                Properties p = new Properties();
                p.load(new FileReader("D:\\development\\practice\\workspace_00_evrd\\evrd02_java_A\\src\\main\\java\\com\\prac\\MVC\\jdbcTemplate\\a\\resources\\database.properties"
                                    ));

                Class.forName(p.getProperty("driver"));
                con = DriverManager.getConnection(p.getProperty("url"),
                                                    p.getProperty("username"),
                                                    p.getProperty("password")
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
        }
        return con;
    }

    public static Connection connectionProp(){
        if(con == null) {
            try {
                Properties p = new Properties();
                p.load(new FileReader("database.properties"
                                    ));
                Class.forName(p.getProperty("driver"));
                con = DriverManager.getConnection(p.getProperty("url"),
                                                    p.getProperty("username"),
                                                    p.getProperty("password")
                                                );
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return con;
    }




}
