package com.prac.MVC.jdbcTemplate.d.controller;

import com.prac.MVC.jdbcTemplate.d.model.service.MemberService;
import com.prac.MVC.jdbcTemplate.d.view.MemberView;
import com.prac.MVC.jdbcTemplate.vo.MemberJSPTable;

import java.util.ArrayList;

public class MemberController {

    MemberView mView = new MemberView();
    MemberService mService = new MemberService();

    public void getMemberAll(){
        ArrayList<MemberJSPTable> list = mService.getMemberAll();

        if(list != null){
            mView.getMemberAll(list);
        }else{
            mView.message("전체 회원 조회 에러. 문의 요망");
        }
    }



}
