package com.prac.MVC.jdbcTemplate.b.controller;

import com.prac.MVC.jdbcTemplate.b.model.service.MemberService;
import com.prac.MVC.jdbcTemplate.b.model.vo.MemberVO;
import com.prac.MVC.jdbcTemplate.b.model.view.MemberView;

public class MemberController {

    MemberView mv = new MemberView();
    MemberService ms = new MemberService();

    public void insertMember(){
        MemberVO member = mv.insertMember();
        int result = ms.insertMember(member);

        if(result > 0){
            mv.displaySuccess("회원 정보 추가");
//            char againAnswer = mv.displaySuccess("회원 정보 추가");
//            if(againAnswer == 'Y'){
//                mv.mainMenu();
//            }

        }else{
            mv.displayError("회원 정보 추가 실패. 에러발생");
//            char againAnswer = mv.displayError("회원 정보 추가 실패. 에러발생");
//            if (againAnswer == 'N') {
//                ms.exitApp();
//                // 메뉴선택mainMenu()에서 반복문이 없어야 이렇게 가능
//            }
        }
    }




    public void updateMember2(){
        String userId = mv.userId();
        int checkYN = ms.checkYN(userId);

        if(checkYN == 1){
            int updtMemPartNo = mv.updateMemberPart();
            if(updtMemPartNo == 0){
                return;
            }
            String updtMemPartInfo = mv.updateMemberPartInfo();
            int result = ms.updateMember(userId,updtMemPartNo,updtMemPartInfo);
            
            if(result > 0){


            }else{
                mv.displayError("회원 정보 수정 실패");
            }
        }else{
            mv.displayError("해당 회원이 없습니다");
        }
        
        
    }


    public void updateMember(){



    }





    public void exitApp(){
        ms.exitApp();
    }

}
