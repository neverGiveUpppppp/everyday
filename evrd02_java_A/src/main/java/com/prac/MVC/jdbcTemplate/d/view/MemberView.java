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
            System.out.println("0.프로그램 종료");
            System.out.print("메뉴 번호 입력 : ");
            menuSelect = Integer.parseInt(sc.nextLine());

            switch (menuSelect){
                case 1: mCon.getMemberAll(); break;
                case 2: mCon.getMemberCondition(); break;
                case 0: mCon.exitApp(); return ; // return ;이 메인메소드로 돌려보내서 앱 종료시킴
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



    public int getMemberCondition(){
        while(true) {
            System.out.println("어떤 조건으로 검색하시겠습니까?");
            System.out.println("1.회원 아이디");
            System.out.println("2.회원 닉네임");
            System.out.println("3.회원 전화번호");
            System.out.println("4.회원 주소");
            System.out.print("메뉴 번호 : ");
            int num = Integer.parseInt(sc.nextLine());

            switch(num){
                case 1: case 2: case 3: case 4: return num; default:
                    System.out.println("메뉴 번호를 잘못 입력하셨습니다. 다시 입력해주세요.");
            }
        }
    }
    public void getMember(MemberJSPTable member){
        System.out.println(member);
    }
    public String getMemberId(){
        System.out.print("회원 아이디 : ");
        String mId = sc.nextLine();
        return mId;
    }
    public String getMemberNickname(){
        System.out.print("회원 닉네임 : ");
        String mNickname = sc.nextLine();
        return mNickname;
    }
    public String getMemberPhone(){
        System.out.print("회원 전화번호 : ");
        String mPhone = sc.nextLine();
        return mPhone;
    }
    public String getMemberAdres(){
        System.out.print("회원 주소 : ");
        String mAdres = sc.nextLine();
        return mAdres;
    }







    public char exitApp(){
        System.out.print("정말 종료하시겠습니까?(Y/N) : ");
        char YN = sc.nextLine().toUpperCase().charAt(0);
        return YN;
    }

}
