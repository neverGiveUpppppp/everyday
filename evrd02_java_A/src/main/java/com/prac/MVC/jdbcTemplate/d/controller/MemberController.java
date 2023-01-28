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
            case 3:
                String mPhone = mView.getMemberPhone();
                mView.message("회원 전화번호로 조회합니다.");
                member = mService.getMemberPhone(mPhone);
                break;
            case 4:
                String mAdres = mView.getMemberAdres();
                mView.message("회원 주소로 조회합니다"); // 띄어쓰기 때문에 주소 한글 안나오는 듯. 쿼리나 검색 어떻게 해야할까?
                member = mService.getMemberAdres(mAdres);
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


    public void postMember(){
        MemberJSPTable memberVo = mView.postMember();
        int postReslt = mService.postMember(memberVo);

        if(postReslt > 0){
            mView.message("회원 정보 추가 완료");    
        }else{
            mView.message("회원 정보 추가 실패");
        }

    }


    // 개선점
    // 회원 조회 시, 검색이니까 부분 단어 검색이 일치해도 나올 수 있도록 부분검색 가능하게
    //  sql에서 like 쓰면 되지 않을까?

    // 이슈
    // 없는 회원 A로 검색하면 null값으로 vo에 채워지는데 조회가 되는 이상한 상황

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
