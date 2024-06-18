package com.prac.MVC.jdbcTemplate.e.model.dao;

import com.prac.MVC.jdbcTemplate.vo.MemberJSPTable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import static com.prac.MVC.jdbcTemplate.e.common.CommonTemplate.*;

public class MemberDAO {

    Properties propetis = null;
    public MemberDAO(){
        propetis = new Properties();
        String filePath = "src/main/java/com/prac/MVC/jdbcTemplate/e/config/member-sql.properties";
        try {
            propetis.load(new FileReader(filePath));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<MemberJSPTable> memSelectAll(Connection connection) {
        Statement stmt = null;
        ResultSet resltSet = null;
        ArrayList<MemberJSPTable> aList = null;

        String query = propetis.getProperty("mem_SelectAll"); // 보안용 네이밍 변경

        try{
            stmt = connection.createStatement();
            resltSet = stmt.executeQuery(query);

            aList = new ArrayList<>();
            while(resltSet.next()){
                String userId = resltSet.getString("USER_ID");
                String userPwd = resltSet.getString("USER_PWD");
                String userName = resltSet.getString("USER_NAME");
                String nickname = resltSet.getString("NICKNAME");
                String phone = resltSet.getString("PHONE");
                String email = resltSet.getString("EMAIL");
                String address = resltSet.getString("ADDRESS");
                String interest= resltSet.getString("INTEREST");
//                Date enrollDate = resltSet.getDate("ENROLL_DATE");
//                Date ModifyDate = resltSet.getDate("MODIFY_DATE");


                MemberJSPTable m = new MemberJSPTable(userId,userPwd,userName,nickname,phone,email,address,interest);
                aList.add(m);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(stmt);
        }
        return aList;
    }




    public MemberJSPTable memSelectId(Connection conn, String memberId){
        MemberJSPTable memVo = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = propetis.getProperty("mem_selectId");

        try{
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();

//            System.out.println(rs);

            while(rs.next()){
                // 조회해서 보여줄 필요 없는 비번 같은 건 안받아옴
                String userName = rs.getString("USER_NAME");
                String nickname = rs.getString("NICKNAME");
                String phone = rs.getString("PHONE");
                String email = rs.getString("EMAIL");
                String address = rs.getString("ADDRESS");
                String interest= rs.getString("INTEREST");
                memVo = new MemberJSPTable(memberId, userName,nickname,phone, email, address, interest);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return memVo;
    }


    public MemberJSPTable memSelectNick(Connection connection, String memberNickname) {
        MemberJSPTable memVo = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = propetis.getProperty("mem_selectNick");

        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, memberNickname);
            rs = pstmt.executeQuery();

            // 객체 하나 받아올거라 while으로 반복해서 값이 더 있는지 체크할 필요 없지 않을까?
            // 반복문 없이 만들어보자 -> error : java.sql.SQLException: ResultSet.next가 호출되지 않았음 
            // while(rs.next()) 안했을 시 발생하며, 에러가 안나도 값을 못받아옴. vo에 null로 계속있음
            while(rs.next()){
            // 조회해서 보여줄 필요 없는 비번 같은 건 안받아옴
            String userId = rs.getString("USER_ID");
            String username = rs.getString("USER_NAME");
            String phone = rs.getString("PHONE");
            String email = rs.getString("EMAIL");
            String address = rs.getString("ADDRESS");
            String interest = rs.getString("INTEREST");

                memVo = new MemberJSPTable(userId, username, phone, email, address, interest);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            close(rs);  // error : java.sql.SQLException: ResultSet.next가 호출되지 않았음 // while(rs.next()) 안했을 시 발생
            close(pstmt);
        }
        return memVo;
    }

}
