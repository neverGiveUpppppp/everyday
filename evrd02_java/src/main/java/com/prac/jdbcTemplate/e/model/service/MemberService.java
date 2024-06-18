package com.prac.MVC.jdbcTemplate.e.model.service;

import com.prac.MVC.jdbcTemplate.b.model.vo.MemberVO;
import com.prac.MVC.jdbcTemplate.e.common.CommonTemplate;
import com.prac.MVC.jdbcTemplate.e.model.dao.MemberDAO;
import com.prac.MVC.jdbcTemplate.vo.MemberJSPTable;

import java.sql.Connection;
import java.util.ArrayList;

import static com.prac.MVC.jdbcTemplate.e.common.CommonTemplate.*;

public class MemberService {
    MemberDAO md = new MemberDAO();

    public ArrayList<MemberJSPTable> memSelectAll(){
        Connection connection = CommonTemplate.getConnection();
        ArrayList<MemberJSPTable> aList = md.memSelectAll(connection);
        // DML이 아니므로 트랜잭션 필요x
        return aList;
    }


    public MemberJSPTable memSelectId(String memberId) {
        Connection conn = getConnection();
        MemberJSPTable memVo = md.memSelectId(conn, memberId);
        return memVo;
    }

    public MemberJSPTable memSelectNick(String memberNickname){
        Connection connection = getConnection();
        MemberJSPTable memVo = md.memSelectNick(connection, memberNickname);
        return memVo;
    }

}
