package com.prac.MVC.jdbcTemplate.b.model.dao;

import com.prac.MVC.jdbcTemplate.b.model.vo.MemberVO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static com.prac.MVC.jdbcTemplate.b.common.TemplateMember.close;

public class MemberDAO {

    private Properties prop = null;

    public MemberDAO(){
        prop = new Properties();
        try {
            String filePath = "src/main/java/com/prac/MVC/jdbcTemplate/b/config/memberMapper.properties";
            prop.load(new FileReader(filePath));

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public int insertMember(Connection con, MemberVO member){
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            pstmt = con.prepareStatement(prop.getProperty("insertMember"));
            pstmt.setString(1,member.getUserId());
            pstmt.setString(2,member.getUserPwd());
            pstmt.setString(3,member.getUserName());
            pstmt.setString(4,member.getNickname());
            pstmt.setString(5,member.getPhone());
            pstmt.setString(6,member.getEmail());
            pstmt.setString(7,member.getAddress());
            pstmt.setString(8,member.getInterest());

            result = pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(pstmt);
            close(con);
        }
        return result;
    }




    public int checkYN(Connection con, String userId){
        PreparedStatement pstmt = null;
        int result = 0;
        try{
            String query = prop.getProperty("checkYN");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,userId);
            result = pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    public int updateMember(Connection con, String userId, int updtMemPartNo, String updtMemPartInfo){
        PreparedStatement pstmt = null;
        int result = 0;

        try{
            String query = prop.getProperty("updateMember");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,userId);

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(pstmt);
            close(con);
        }
        return result;
    }









}
