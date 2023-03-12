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



    public void putMember(){
//        MemberJSPTable mem = mView.putMember();
        String checkId = mView.getMemberId();
        int checkIdReslt = mService.checkId(checkId);
        int result = 0;

        if(checkIdReslt > 0){
            int menuNum = mView.putMember();
            String putContext = mView.putMemberContxt();

            switch(menuNum){
                case 1:
                    result = mService.putMemberPwd(checkId, menuNum, putContext);
                    break;
                case 2:
                    result = mService.putMemberName(checkId, menuNum, putContext);
                    break;
                case 3:
                    result = mService.putMemberNick(checkId, menuNum, putContext);
                    break;
                case 4:
                    result = mService.putMemberEmail(checkId, menuNum, putContext);
                    break;
                case 5:
                    result = mService.putMemberAdres(checkId, menuNum, putContext);
                    break;
            }
        }

        // 어느 정보를 받아 업데이트 해도 하나로 화면단 처리
        if (result > 0) {
            mView.message("회원정보 업데이트 성공");
        }else{
            mView.message("회원정보 업데이트 실패");
        }

    }


    public void deleteMember(){
        String checkId = mView.checkId();
        char YN = mView.deleteMember();
        System.out.println(YN);
        if(YN != 0){
            int result = mService.deleteMember(checkId);
            if(result > 0){
                mView.message("해당 회원 탈퇴 완료");
            }else{
                mView.message("회원 탈퇴 실패");
            }
        }

    }

    // 멤버조건검색 이슈
    // 없는 회원 A로 검색하면 null값으로 vo에 채워지는데 조회가 되는 이상한 상황

    // 개선점
    // 회원 조회 시, 검색이니까 부분 단어 검색이 일치해도 나올 수 있도록 부분검색 가능하게 만들기
    //      sql에서 like 사용해보기
    // 비밀번호 변경 시, 기존 비밀번호랑 같으면 변경 안되게 유효성 검사 추가
    // 닉네임, 주소 등 회원정보 변경 시, 기존과 같으면 변경 안되게 유효성 검사 및 메세지


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
