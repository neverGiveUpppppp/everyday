package com.prac.MVC.jdbcTemplate.e.controller;

import com.prac.MVC.jdbcTemplate.b.model.vo.MemberVO;
import com.prac.MVC.jdbcTemplate.e.model.service.MemberService;
import com.prac.MVC.jdbcTemplate.e.view.MenuView;
import com.prac.MVC.jdbcTemplate.vo.MemberJSPTable;

import java.util.ArrayList;

public class MemberController {
    MenuView mv = new MenuView();
    MemberService ms = new MemberService();

    public void memSelectAll() {
        ArrayList<MemberJSPTable> aList = ms.memSelectAll();

        if(aList != null && !aList.isEmpty()){
            mv.memSelectAll(aList);
        }else{
            mv.message("전체 회원 조회 실패");
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





}
