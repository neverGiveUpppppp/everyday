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




    public void getMemberCondition(){

        int num = mView.getMemberCondition();

        MemberJSPTable member = null;
        switch(num){
            case 1:
                String mId = mView.getMemberId();
                mView.message("회원 아이디로 조회합니다.");
                member = mService.getMemberId(mId); // 없는 회원 A로 검색하면 null값으로 vo에 채워지는데 조회가 되는 이상한 상황
                break;
            case 2:
                String mNickname = mView.getMemberNickname();
                mView.message("회원 닉네임으로 조회합니다.");
                member = mService.getMemberNickname(mNickname);
                break;

            // 이슈
            // 없는 회원 A로 검색하면 null값으로 vo에 채워지는데 조회가 되는 이상한 상황


        }

        if(member != null){
            mView.getMember(member);
        }else{
            mView.message("회원정보 조회 실패. 문의 요망.");
        }


    }





    public void exitApp(){
        char YN = mView.exitApp();

        if(YN == 'Y'){
            mView.message("프로그램을 종료합니다.");
            mService.exitApp();  // db connection 연결 close
            return ; // return ;이 view의 mainMenu()로 돌려보냄
        }else{
            mView.message("메인 메뉴로 돌아갑니다.");
            mView.mainMenu();
        }

    }


}
