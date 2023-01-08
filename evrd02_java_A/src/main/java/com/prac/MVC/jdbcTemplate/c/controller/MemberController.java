package com.prac.MVC.jdbcTemplate.c.controller;

import com.prac.MVC.jdbcTemplate.c.model.vo.MemberVO;
import com.prac.MVC.jdbcTemplate.c.model.service.MemberService;
import com.prac.MVC.jdbcTemplate.c.view.MemberDisplay;

import java.util.ArrayList;

public class MemberController{

    MemberDisplay mv = new MemberDisplay();
    MemberService ms = new MemberService();

    public void selectMemAll(){
        ArrayList<MemberVO> memList = ms.selectMemAll();
        if(memList != null){
            mv.selectMemAll(memList);
        }
    }



    public void selectSpecific(){
        int selection = mv.selectSpecific();
        ArrayList<MemberVO> memList = null;

        switch(selection){
            case 1: String userId = mv.MemUserId();
                    memList = ms.selectMemId(userId);
                    break;

            case 2: String nickname = mv.MemNickname();
                    memList = ms.selectMemNick(nickname);
                    break;
            case 0: return;
        }

        if(memList != null && !memList.isEmpty()){
            mv.selectSpecificView(memList);
        }else if(memList.isEmpty()){
            mv.msgError("해당 회원이 없습니다");
        }else{
            mv.msgError("에러 발생. 문의요망");
        }


    }





}
