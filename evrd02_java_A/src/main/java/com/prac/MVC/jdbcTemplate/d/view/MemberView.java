package com.prac.MVC.jdbcTemplate.d.view;

import com.prac.MVC.jdbcTemplate.d.controller.MemberController;
import com.prac.MVC.jdbcTemplate.vo.MemberJSPTable;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberView {

    Scanner sc = new Scanner(System.in);
    public void mainMenu(){
        MemberController mCon = new MemberController();
        int menuSelect = 0;
        while(true){
            System.out.println("=== 메인 메뉴 ===");
            System.out.println("1.전체 회원 조회");
            System.out.println("2.특정 조건 회원 조회 ");
            System.out.println("3.회원 추가");
            System.out.println("4.회원 정보 수정");
            System.out.println("5.회원 정보 삭제");
            System.out.print("메뉴 번호 입력 : ");
            menuSelect = Integer.parseInt(sc.nextLine());

            switch (menuSelect){
                case 1: mCon.getMemberAll(); break;
                case 0: return ;
                default:
                    System.out.println("메뉴 번호가 잘못 입력되었습니다. 재입력 해주세요.");
            }
        }
    }


    public void message(String string){
        System.out.println(string);
    }



    public void getMemberAll(ArrayList<MemberJSPTable> list){
        for(int i=0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }

}
