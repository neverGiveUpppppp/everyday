package com.prac.MVC.jdbcTemplate.a.controller;

import com.prac.MVC.jdbcTemplate.a.model.dao.MemberDao;
import main.java.com.prac.MVC.jdbcTemplate.a.model.service.MemberService;
import com.prac.MVC.jdbcTemplate.a.view.MemberMenu;
import com.prac.MVC.jdbcTemplate.a.model.vo.MemberJSPTable;

public class MemberController {

    MemberMenu mm = new MemberMenu();
    MemberService ms = new MemberService();
    MemberDao md = new MemberDao();

    public int memberInsert(){
        MemberJSPTable member = mm.memberInsert();
        int result = ms.memberInsert(member);

        if(result > 0){
            mm.displaySuccess(result+"명의 사원 추가");
        }else{
            mm.displayError("데이터 추가 실패");
        }
        return result;
    }

}
