package com.prac.MVC.jdbcTemplate.e.view;

import com.prac.MVC.jdbcTemplate.e.controller.MemberController;
import com.prac.MVC.jdbcTemplate.vo.MemberJSPTable;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuView {

    Scanner sc = new Scanner(System.in);

    public void mainMenu(){
        MemberController mc = new MemberController();
        int menuSelection = 0;

        while(true){
            System.out.println("======== 메인 메뉴 ========");
            System.out.println("1.전체 회원 조회");
            System.out.println("2.특정 조건 회원 조회 ");
            System.out.println("3.회원 추가");
            System.out.println("4.회원 정보 수정");
            System.out.println("5.회원 정보 삭제");
            System.out.println("0.프로그램 종료");
            System.out.print("메뉴 번호 입력 : ");
            menuSelection = Integer.parseInt(sc.nextLine());

            switch(menuSelection){
                case 1: mc.memSelectAll(); break;
                case 2: mc.memSelectCondition(); break;
//                case 3: break;
                default:
                    System.out.println("잘못 입력하셨습니다");
            }
        }
    }

    public void message(String str){
        System.out.println(str);
    }

    public String memberId(){
        System.out.print("회원 아이디 :  ");
        String memberId = sc.nextLine();
        return memberId;
    }
    public String memberNickname(){
        System.out.print("회원 닉네임 : ");
        String memberNickname = sc.nextLine();
        return memberNickname;
    }

    public void memSelectAll(ArrayList<MemberJSPTable> aList){
//        for(int i=0; i < aList.size();i++){
//            System.out.println(aList.get(i));
//        }
        for(MemberJSPTable m : aList){
            System.out.println(m);
        }
    }


    public int memSelectCondition() {
        System.out.println("1.아이디로 회원조회");
        System.out.println("2.닉네임으로 회원조회");
        System.out.println("0.메인 메뉴로 돌아가기");
        System.out.print("번호 선택 : ");
        int selectNum = Integer.parseInt(sc.nextLine());

        switch (selectNum){
            case 1: case 2: case 0: return selectNum;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                memSelectCondition(); // while문 안쓰고 반복 시켜보기
        }
        return selectNum;
    }
    public void memberSelectReslt(MemberJSPTable memVo) {
        System.out.println(memVo);
        System.out.println();
    }






}
