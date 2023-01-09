package com.prac.MVC.jdbcTemplate.c.view;

import com.prac.MVC.jdbcTemplate.c.controller.MemberController;
import com.prac.MVC.jdbcTemplate.c.model.vo.MemberVO;

import java.util.ArrayList;
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
                case 1: memCon.selectMemAll(); break; // 이슈 : enroll부터 status까지 null나옴
                case 2: memCon.selectSpecific(); break;
                case 3: memCon.insertMember(); break;
                case 0:
                    System.out.println("프로그램 종료");
                default :
                    System.out.println("잘못 입력하셨습니다. 메뉴를 다시 선택해주세요.");
            }

        }while(num != 0);
    }


    public void msg(String str){
        System.out.println(str);
    }
    public void msgError(String str){
        System.out.println(str);
    }



    public ArrayList<MemberVO> selectMemAll(ArrayList<MemberVO> memList){
        for(int i=0; i < memList.size();i++){
            System.out.println(memList.get(i));
        }
        return memList;
    }


    public int selectSpecific(){
        
        int selection = 0;
        while(true) {
            System.out.println("1.아이디로 회원조회");
            System.out.println("2.닉네임으로 회원조회");
            System.out.println("0.메인 메뉴로 돌아가기");
            System.out.print("번호 선택 : ");
            selection = Integer.parseInt(sc.nextLine());
            
            switch(selection){
                case 1: case 2: case 3: return selection;
                default:
                    System.out.println("잘못 입력 됐습니다. 재입력해주세요");
            }
        }
    }
    public String MemUserId(){
        System.out.print("회원 ID : ");
        String userId = sc.nextLine();
        return userId;
    }
    public String MemNickname(){
        System.out.print("회원 닉네임 : ");
        String nickname = sc.nextLine();
        return nickname;
    }
    public void selectSpecificView(ArrayList<MemberVO> memList){
        System.out.println(memList);
    }



    public MemberVO insertMember(){
        System.out.println(" --- 새 회원 정보 입력 --- ");
        System.out.print("회원 아이디 : ");
        String userId = sc.nextLine();
        System.out.print("비밀번호 : ");
        String userPwd = sc.nextLine();
        System.out.print("이름 : ");
        String userName = sc.nextLine();
        System.out.print("닉네임 : ");
        String nickname = sc.nextLine();
        System.out.print("핸드폰 번호 : ");
        String phone = sc.nextLine();
        System.out.print("이메일 : ");
        String email = sc.nextLine();
        System.out.print("주소 : ");
        String address = sc.nextLine();
        System.out.print("관심분야 : ");
        String interest = sc.nextLine();
        MemberVO member = new MemberVO(userId, userPwd,userName,nickname,phone,email,address,interest);
        return member;
    }




}
