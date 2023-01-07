package com.prac.MVC.jdbcTemplate.c.controller;

import com.prac.MVC.jdbcTemplate.c.model.vo.MemberVO;
import com.prac.MVC.jdbcTemplate.c.model.service.MemberService;
import com.prac.MVC.jdbcTemplate.c.view.MemberDisplay;

import java.util.ArrayList;

public class MemberController{

    MemberDisplay mv = new MemberDisplay();
    MemberService mc = new MemberService();

    public ArrayList<MemberVO> selectMemAll(){
        ArrayList<MemberVO> memList = mc.selectMemAll();

        return memList;
    }

}
