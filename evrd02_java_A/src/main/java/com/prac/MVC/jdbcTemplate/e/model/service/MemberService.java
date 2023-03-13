package com.prac.MVC.jdbcTemplate.e.model.service;

import com.prac.MVC.jdbcTemplate.b.model.vo.MemberVO;
import com.prac.MVC.jdbcTemplate.e.model.dao.MemberDAO;
import com.prac.MVC.jdbcTemplate.vo.MemberJSPTable;

import java.sql.Connection;
import java.util.ArrayList;

import static com.prac.MVC.jdbcTemplate.e.common.CommonTemplate.*;

public class MemberService {
    MemberDAO md = new MemberDAO();

    public ArrayList<MemberJSPTable> memSelectAll(){
        Connection connection = getConnection();
        ArrayList<MemberJSPTable> aList = md.memSelectAll(connection);
        if(aList != null){
            commit(connection);
        }else{
            rollback(connection);
        }
        return aList;
    }



}
