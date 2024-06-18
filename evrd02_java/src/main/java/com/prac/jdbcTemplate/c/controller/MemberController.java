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
            case 1: String userId = mv.memUserId();
                    memList = ms.selectMemId(userId);
                    break;

            case 2: String nickname = mv.memNickname();
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



    public void insertMember(){
        MemberVO memVo = mv.insertMember();
        int result = ms.insertMember(memVo);

        if(result > 0){
            mv.msg("새 회원 정보 추가");
        }else{
            mv.msgError("새 회원 정보 추가 실패");
        }

    }


    public void updateMember(){
        String userId = mv.memUserId();
        int checkMem = ms.memIsExist(userId);

        if(checkMem == 1) {
            int selection = mv.updateMember();
            if(selection == 0){
                return; // 메뉴로 돌아가는 리턴
            }
            String resultCondt = mv.updateMemInfo();
            int result = ms.updateMember(selection, resultCondt, userId);
            
            if(result > 0){
                mv.msg("회원 정보 수정 완료");
            }else{
                mv.msgError("회원 정보 수정 실패. 문의 요망");
            }
        }
    }

    public void deleteMember() {
        String userId = mv.memUserId();
        int checkId = ms.memIsExist(userId);

        if(checkId > 0){
            char ansYN = mv.deleteMember();
            if(ansYN == 'Y') {
                int result = ms.deleteMember(userId);
                
                if(result > 0){
                    mv.msg("회원 삭제 완료");
                }
            }else{
                return;
            }
        }else{
            return;
        }

    }

    // package, domain 형식으로 재구성도 해보자

}
