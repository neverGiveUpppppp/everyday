package com.prac.MVC.jdbcTemplate.view;

import com.prac.MVC.jdbcTemplate.controller.MemberController;

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
            System.out.print("메뉴 번호 입력 : ");
            menuSelect = Integer.parseInt(sc.nextLine());

        }while(menuSelect !=0);

        switch(menuSelect){
            case 1:
                mc.memberInsert();
                break;

            default:
                System.out.println("번호를 잘못 선택했습니다");
                break;
        }

    }





}
