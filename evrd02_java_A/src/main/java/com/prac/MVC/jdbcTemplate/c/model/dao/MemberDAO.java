package com.prac.MVC.jdbcTemplate.c.model.dao;

import com.prac.MVC.jdbcTemplate.c.common.JdbcTemplate.*;
import com.prac.MVC.jdbcTemplate.c.model.vo.MemberVO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static com.prac.MVC.jdbcTemplate.c.common.JdbcTemplate.conn;

public class MemberDAO{

    private Properties prop = null;
    public MemberDAO(){
        prop = new Properties();
        try {
            prop.load(new FileReader("D:\\development\\practice\\workspace_00_evrd\\evrd02_java_A\\src\\main\\java\\com\\prac\\MVC\\jdbcTemplate\\c\\common\\config\\memberMapper.properties"
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



        public ArrayList<MemberVO> selectMemId(Connection conn, String userId){

            ArrayList<MemberVO> memList = null;
            PreparedStatement pstmt = null;
            ResultSet rset = null;

            String query = prop.getProperty("selectMemId");

            try{
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1,userId);
                rset = pstmt.executeQuery();

                memList = new ArrayList<>();
                if(rset.next()){
                    String userPwd = rset.getString("USER_PWD");
                    String userName = rset.getString("USER_NAME");
                    String nickname = rset.getString("NICKNAME");
                    String phone = rset.getString("PHONE");
                    String email = rset.getString("EMAIL");
                    String address = rset.getString("ADDRESS");
                    String interest = rset.getString("INTEREST");

                    MemberVO member = new MemberVO(userId,userPwd,userName,nickname,phone,email,address,interest);
                    memList.add(member);
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            return memList;
        }
        public ArrayList<MemberVO> selectMemNick(Connection conn, String nickname){
            ArrayList<MemberVO> memList = null;
            PreparedStatement pstmt = null;
            ResultSet rset = null;

            try{
                String query = prop.getProperty("selectMemNick");

                pstmt = conn.prepareStatement(query);
                pstmt.setString(1,nickname);
                rset = pstmt.executeQuery();

                memList = new ArrayList<>();
                while(rset.next()){
                    String userId = rset.getString("USER_ID");
                    String userPwd = rset.getString("USER_PWD");
                    String userName = rset.getString("USER_NAME");
                    String phone = rset.getString("PHONE");
                    String email = rset.getString("EMAIL");
                    String address = rset.getString("ADDRESS");
                    String interest = rset.getString("INTEREST");

                    MemberVO member = new MemberVO(userId,userPwd,userName,nickname,phone,email,address,interest);
                    memList.add(member);
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            return memList;
        }



        public int insertMember(Connection conn, MemberVO memVo){

            PreparedStatement pstmt = null;
            int result = 0;

            String query = prop.getProperty("insertMember");

            try{
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1,memVo.getUserId());
                pstmt.setString(2,memVo.getUserPwd());
                pstmt.setString(3,memVo.getUserName());
                pstmt.setString(4,memVo.getNickname());
                pstmt.setString(5,memVo.getPhone());
                pstmt.setString(6,memVo.getEmail());
                pstmt.setString(7,memVo.getAddress());
                pstmt.setString(8,memVo.getInterest());
                result = pstmt.executeUpdate();

            }catch(SQLException e){
                e.printStackTrace();
            }

            return result;
        }




        public int memIsExist(Connection conn, String userId){

            PreparedStatement pstmt = null;
            int result = 0;
            String query = prop.getProperty("memIsExist");

            try{
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1,userId);
                result = pstmt.executeUpdate();

            }catch(SQLException e){
                e.printStackTrace();
            }
        return result;
        }
        public int updateMember(Connection conn, int selection, String resultCondt, String userId){
            PreparedStatement pstmt = null;
            int result = 0;
            String query = prop.getProperty("updateMember"+selection);

            try{
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1,resultCondt);
                pstmt.setString(2,userId);
                result = pstmt.executeUpdate();

            }catch(SQLException e){
                e.printStackTrace();
            }
            return  result;
        }






}
