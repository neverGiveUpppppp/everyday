package com.prac.MVC.jdbcTemplate.a.model.service;

import com.prac.MVC.jdbcTemplate.a.model.dao.MemberDao;
import com.prac.MVC.jdbcTemplate.a.model.vo.MemberJSPTable;

import java.sql.Connection;

import static com.prac.MVC.jdbcTemplate.a.common.Template.*;
import static main.java.com.prac.MVC.jdbcTemplate.a.common.TemplatePrac2.getConnection;

public class MemberService2 {

    MemberDao md = new MemberDao();


    public MemberJSPTable insertMember(MemberJSPTable member){
        Connection con = getConnection();
        int result = md.memberInsert(member,con);

        if (result > 0){
            commit(con);
        }else{
            rollback(con);
        }
        return member;
    }



}
