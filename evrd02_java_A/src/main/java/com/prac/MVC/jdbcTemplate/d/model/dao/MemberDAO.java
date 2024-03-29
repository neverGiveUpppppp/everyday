package com.prac.MVC.jdbcTemplate.d.model.dao;

import com.prac.MVC.jdbcTemplate.vo.MemberJSPTable;
import jdk.nashorn.internal.runtime.ECMAException;

import javax.xml.transform.Result;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static com.prac.MVC.jdbcTemplate.d.common.TemplateMember.close;

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
        }finally {
            close(rs);
            close(stmt);
        }
        return list;
    }




    public MemberJSPTable getMemberId(Connection con, String mId ){

        MemberJSPTable member = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = properties.getProperty("getMemberId");

        try{
            pstmt = con.prepareStatement(query);
//            pstmt.setString(1, mId); // error
            pstmt.setString(1, "%" + mId + "%");
            // WHERE USER_ID LIKE %?%로 하면 에러남
            // 위치홀더 값 삽입 시, 알아서 싱글쿼테이션을 채워서 쿼리를 보낸다
            // query += "'%" + id + "%'";
            rset = pstmt.executeQuery();

            member = new MemberJSPTable();
            if(rset.next()){
                String userId = rset.getString("USER_ID");
                String userPwd = rset.getString("USER_PWD");
                String userName = rset.getString("USER_NAME");
                String nickname = rset.getString("NICKNAME");
                String phone = rset.getString("PHONE");
                String email = rset.getString("EMAIL");
                String address = rset.getString("ADDRESS");
                String interest = rset.getString("INTEREST");
                Date enrollDate = rset.getDate("ENROLL_DATE");
                member = new MemberJSPTable(userId,userPwd,userName,nickname,phone,email,address,interest,enrollDate);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(rset);
            close(pstmt);
        }
        return member;
    }


    public MemberJSPTable getMemberNickname(Connection con, String mNickname){
        MemberJSPTable member = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = properties.getProperty("getMemberNickname");

        try{
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,mNickname);
            rs = pstmt.executeQuery();

            member = new MemberJSPTable();
            if(rs.next()){
                String userId = rs.getString("USER_ID");
                String userPwd = rs.getString("USER_PWD");
                String userName = rs.getString("USER_NAME");
                String phone = rs.getString("PHONE");
                String email = rs.getString("EMAIL");
                String address = rs.getString("ADDRESS");
                String interest = rs.getString("INTEREST");
                Date enrollDate = rs.getDate("ENROLL_DATE");
                member = new MemberJSPTable(userId,userPwd,userName,mNickname,phone,email,address,interest,enrollDate);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return member;
    }
    public MemberJSPTable getMemberPhone(Connection con, String mPhone){
        MemberJSPTable member = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = properties.getProperty("getMemberPhone");


        try{
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, mPhone);
            rs = pstmt.executeQuery();

            member = new MemberJSPTable();
            if(rs.next()){
                String userId = rs.getString("USER_ID");
                String userPwd = rs.getString("USER_PWD");
                String userName = rs.getString("USER_NAME");
                String mNickname = rs.getString("NICKNAME");
                String email = rs.getString("EMAIL");
                String address = rs.getString("ADDRESS");
                String interest = rs.getString("INTEREST");
                Date enrollDate = rs.getDate("ENROLL_DATE");
                member = new MemberJSPTable(userId,userPwd,userName,mNickname,mPhone,email,address,interest,enrollDate);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return member;
    }
    public MemberJSPTable getMemberAdres(Connection conn, String mAdres){
        MemberJSPTable member = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = properties.getProperty("getMemberAdres");

        try{
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,mAdres);
            rs = pstmt.executeQuery();

            member = new MemberJSPTable();
            while(rs.next()){
                String userId = rs.getString("USER_ID");
                String userPwd = rs.getString("USER_PWD");
                String userName = rs.getString("USER_NAME");
                String mNickname = rs.getString("NICKNAME");
                String phone = rs.getString("ADDRESS");
                String email = rs.getString("EMAIL");
                String interest = rs.getString("INTEREST");
                Date enrollDate = rs.getDate("ENROLL_DATE");
                member = new MemberJSPTable(userId,userPwd,userName,mNickname,phone,email,mAdres,interest,enrollDate);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return member;
    }



    public int postMember(Connection con, MemberJSPTable memberVo){
        PreparedStatement pstmt = null;
        String query = properties.getProperty("postMember");
        int postReslt = 0;
        try{
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,memberVo.getUserId());
            pstmt.setString(2,memberVo.getUserPwd());
            pstmt.setString(3,memberVo.getUserName());
            pstmt.setString(4,memberVo.getNickname());
            pstmt.setString(5,memberVo.getPhone());
            pstmt.setString(6,memberVo.getEmail());
            pstmt.setString(7,memberVo.getAddress());
            pstmt.setString(8,memberVo.getInterest());
            postReslt = pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            close(pstmt);
        }
        return postReslt;
    }






    public int checkId(Connection conn, String checkId){
        PreparedStatement pstmt = null;
        int result = 0;
        String query = properties.getProperty("checkId"); // select 객체가 아닌 count로 int로 받아오기

        try{
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,checkId);
            result = pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return result;
    }

    public int putMemberPwd(Connection conn, String checkId, int menuNum, String putContext){

        PreparedStatement pstmt = null;
        int result = 0;
        String query = properties.getProperty("putMember"+menuNum); // DML문 select 객체가 아닌 count로 int로 받아오기

        try{
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,putContext);
            pstmt.setString(2,checkId);
            result = pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(pstmt);
        }
        return result;
    }
    public int putMemberName(Connection conn, String checkId, int menuNum, String putContext){

        PreparedStatement pstmt = null;
        int result = 0;
        String query = properties.getProperty("putMember"+menuNum);

        try{
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,putContext);
            pstmt.setString(2,checkId);
            result = pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(pstmt);
        }
        return result;
    }
    public int putMemberNick(Connection conn, String checkId, int menuNum, String putContext) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = properties.getProperty("putMember"+menuNum);

        try{
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, putContext);
            pstmt.setString(2,checkId);
            result = pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(pstmt);
        }
        return result;
    }
    public int putMemberEmail(Connection conn, String checkId, int menuNum, String putContext) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = properties.getProperty("putMember"+menuNum);

        try{
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,putContext);
            pstmt.setString(2,checkId);
            result = pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            close(pstmt);
        }
        return result;
    }



    public int putMemberAdres(Connection conn, String checkId, int menuNum, String putContext){
        PreparedStatement pstmt = null;
        int result = 0;
        String query = properties.getProperty("putMember"+menuNum);

        try{
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, putContext);
            pstmt.setString(2,checkId);
            result = pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            close(pstmt);
        }
        return result;
    }

    public int deleteMember(Connection connection, String checkId){
        PreparedStatement pstmt = null;
        int result = 0;
        String query = properties.getProperty("deleteMember");
        try{
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1,checkId);
            result = pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return result;
    }






}
