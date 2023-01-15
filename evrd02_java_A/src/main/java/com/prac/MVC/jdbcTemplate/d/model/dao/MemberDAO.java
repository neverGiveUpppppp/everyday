package com.prac.MVC.jdbcTemplate.d.model.dao;

import com.prac.MVC.jdbcTemplate.vo.MemberJSPTable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class MemberDAO {

    Properties properties = null;

    public MemberDAO(){
        try {
            properties = new Properties();
            properties.load(new FileReader("src/main/java/com/prac/MVC/jdbcTemplate/d/common/config/memberSQLMapper.properties"));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<MemberJSPTable> getMemberAll(Connection conn){
        ArrayList<MemberJSPTable> list = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = properties.getProperty("getMemberAll");

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            list = new ArrayList<>();

            while(rs.next()){

                String userId = rs.getString("USER_ID");
                String userPwd = rs.getString("USER_PWD");
                String userName = rs.getString("USER_NAME");
                String nickname = rs.getString("NICKNAME");
                String phone = rs.getString("PHONE");
                String email = rs.getString("EMAIL");
                String address = rs.getString("ADDRESS");
                String interest = rs.getString("INTEREST");
                MemberJSPTable m = new MemberJSPTable(userId,userPwd,userName,nickname,phone,email,address,interest);
                list.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }


}
