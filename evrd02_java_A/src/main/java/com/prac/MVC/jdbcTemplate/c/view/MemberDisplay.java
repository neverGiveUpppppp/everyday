package com.prac.MVC.jdbcTemplate.c.view;

import com.prac.MVC.jdbcTemplate.c.controller.MemberController;

import java.util.Scanner;

public class MemberDisplay {

    Scanner sc = new Scanner(System.in);
    public void mainMenu(){

        MemberController memCon = new MemberController();
        int num = 0;
        do{
            System.out.println("===== 회원 정보 관리 시스템 =====");
            System.out.println("1.전체 회원 정보 조회");
            System.out.println("2.특정 조건 회원 조회");
            System.out.println("3.새 회원 등록");
            System.out.println("4.회원 정보 수정");
            System.out.println("5.회원 삭제");
            System.out.print("메뉴 번호 선택 : ");
            num = Integer.parseInt(sc.nextLine());

            switch(num){
                case 1: memCon.selectMemAll(); break;
                default :
                    System.out.println("잘못 입력하셨습니다. 메뉴를 다시 선택해주세요.");
            }

        }while(num != 0);
    }



}
