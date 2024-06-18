package com.prac.MVC.jdbcTemplate.a.view;

import com.prac.MVC.jdbcTemplate.a.controller.MemberController;
import com.prac.MVC.jdbcTemplate.a.model.vo.MemberJSPTable;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberMenu {

    Scanner sc = new Scanner(System.in);

    public void mainMenu(){
        MemberController mc = new MemberController();
        int menuSelect = 0;

        do{
            System.out.println("\n *** 회원 관리 프로그램 *** \n");
            System.out.println("1. 새회원 등록");
            System.out.println("2. 모든 회원 조회");
            System.out.println("3. 특정 조건 회원 조회");
            System.out.println("4. 회원 정보 수정");
            System.out.println("5. 회원 탈퇴");
            System.out.println("0. 프로그램 종료");
            System.out.println("----------------");
            System.out.print("메뉴 번호 입력 : ");
            menuSelect = Integer.parseInt(sc.nextLine());

            switch(menuSelect){
                case 1: mc.memberInsert(); break;


                default:
                    System.out.println("번호를 잘못 선택했습니다");
            }
        }while(menuSelect !=0);



    }



    public void displaySuccess(String str){
        System.out.println("서비스 요청 성공 : "+str);
    }
    public void displayError(String str){
        System.out.println("서비스 요청 실패"+str);
    }



    public MemberJSPTable memberInsert(){
        System.out.println(" ===== 사원 등록 ===== ");
        System.out.print("사원 아이디 : ");
        String userId = sc.nextLine();
        System.out.print("사원 아이디 : ");
        String userPwd = sc.nextLine();
        System.out.print("사원 이름 : ");
        String userName = sc.nextLine();
        System.out.print("사원 닉네임 : ");
        String nickname = sc.nextLine();
        System.out.print("사원 핸드폰번호 : ");
        String phone = sc.nextLine();
        System.out.print("사원 이메일 : ");
        String email = sc.nextLine();
        System.out.print("사원 주소 : ");
        String address = sc.nextLine();
        System.out.print("사원 관심사 : ");
        String interest = sc.nextLine();

        MemberJSPTable member = new MemberJSPTable(userId,userPwd,userName,nickname,phone,email,address,interest);

        return member;
    }


}
