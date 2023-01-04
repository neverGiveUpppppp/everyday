package com.prac.MVC.jdbcTemplate.a.model.dao;

import com.prac.MVC.jdbcTemplate.a.model.vo.MemberJSPTable;
import static com.prac.MVC.jdbcTemplate.a.common.Template.close;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class MemberDao {

    Properties prp = null;

    public int memberInsert(MemberJSPTable member, Connection con){

        int result = 0;
        PreparedStatement pstmt = null;

        String query = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,SYSDATE,NULL,DEFAULT)";

        try{
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, member.getUserId());
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
        }
        return result;
    }


}
