package com.prac.MVC.jdbcTemplate.c.model.dao;

import com.prac.MVC.jdbcTemplate.c.common.JdbcTemplate.*;
import com.prac.MVC.jdbcTemplate.c.model.vo.MemberVO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class MemberDAO{

    private Properties prop = null;
    public MemberDAO(){
        prop = new Properties();
        try {
            prop.load(new FileReader("D:\\Dropbox\\Developer\\workspace_00_evrd\\evrd02_java_A\\src\\main\\java\\com\\prac\\MVC\\jdbcTemplate\\c\\common\\config\\memberMapper.properties"
            ));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public ArrayList<MemberVO> selectMemAll(Connection conn){

        ArrayList<MemberVO> memList = null;
        Statement stmt = null;
        ResultSet rset = null;

        try{
            String query = prop.getProperty("selectMemAll");

            stmt = conn.createStatement();
            rset = stmt.executeQuery(query);

            memList = new ArrayList<>();
            while(rset.next()){
                String userId = rset.getString("USER_ID");
                String userPwd = rset.getString("USER_PWD");
                String userName = rset.getString("USER_NAME");
                String nickname = rset.getString("NICKNAME");
                String phone = rset.getString("PHONE");
                String email = rset.getString("EMAIL");
                String address = rset.getString("ADDRESS");
                String interset = rset.getString("INTEREST");

                MemberVO member = new MemberVO(userId,userPwd,userName,nickname,phone,email,address,interset);
                memList.add(member);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return memList;
    }



}
