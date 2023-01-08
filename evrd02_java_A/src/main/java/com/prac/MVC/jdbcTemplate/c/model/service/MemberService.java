package com.prac.MVC.jdbcTemplate.c.model.service;

import com.prac.MVC.jdbcTemplate.c.model.vo.MemberVO;
import com.prac.MVC.jdbcTemplate.c.model.dao.MemberDAO;

import java.sql.Connection;
import java.util.ArrayList;

import static com.prac.MVC.jdbcTemplate.c.common.JdbcTemplate.*;

public class MemberService {

    Connection conn = null;
    MemberDAO md = new MemberDAO();
    public ArrayList<MemberVO> selectMemAll() {
        Connection conn = getConnection();
        ArrayList<MemberVO> memList = md.selectMemAll(conn);

        if(memList != null){
            commit(conn);
        }else{
            rollback(conn);
        }
        return memList;
    }


    public ArrayList<MemberVO> selectMemId(String userId){
        Connection conn = getConnection();
        ArrayList<MemberVO> memList = md.selectMemId(conn, userId);

        if(memList != null){
            commit(conn);
        }else{
            rollback(conn);
        }
        return memList;
    }
    public ArrayList<MemberVO> selectMemNick(String nickname){
        Connection conn = getConnection();
        ArrayList<MemberVO> memList = md.selectMemNick(conn, nickname);

        if(memList != null){
            commit(conn);
        }else{
            rollback(conn);
        }
        return memList;
    }

}
