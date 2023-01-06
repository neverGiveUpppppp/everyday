package com.prac.MVC.jdbcTemplate.b.view;

import com.prac.MVC.jdbcTemplate.b.controller.MemberController;
import com.prac.MVC.jdbcTemplate.b.model.vo.MemberVO;

import java.util.Scanner;

public class MemberView {

    Scanner sc = new Scanner(System.in);

    public void mainMenu(){
        MemberController mc = new MemberController();
        int selNum = 0;
        do{
            System.out.println("====회원관리 프로그램====");
            System.out.println("1.회원 추가");
            System.out.println("2.회원 정보 수정");
            System.out.println("3.회원 삭제");
            System.out.println("4.회원 조회");
            System.out.println("5.회체 사원 조회");
            System.out.print("메뉴 번호 선택 : ");
            selNum = Integer.parseInt(sc.nextLine());

            switch(selNum){
                case 1: mc.insertMember(); break;
                case 2: mc.updateMember(); break;
                case 0: mc.exitApp(); break;
                default:
                    System.out.println("잘못 입력하셨습니다. 재입력");
            }
        }while(selNum != 0);
    }
    
    
    
    public void displaySuccess(String str){

        System.out.println("\n"+"서비스 요청 성공 : "+str);
//        System.out.print("다른 메뉴를 선택하시겠습니까?(Y/N) : ");       // 메뉴선택 반복문 없어야 가능
//        char againAnswer = sc.nextLine().toUpperCase().charAt(0);
//        return againAnswer;
    }
    public void displayError(String str){
        System.out.println("서비스 요청 실패 : "+str);
//        System.out.print("다른 메뉴를 선택하시겠습니까?(Y/N) : ");
//        char againAnswer = sc.nextLine().toUpperCase().charAt(0);
//        return againAnswer;
    }

    public String userId(){
        System.out.print("회원 아이디 : ");
        String userId = sc.nextLine();
        return userId;
    }


    public MemberVO insertMember(){
        System.out.println("---1.회원추가---");
        System.out.print("회원ID : ");
        String userId = sc.nextLine();
        System.out.print("회원PW : ");
        String userPwd = sc.nextLine();
        System.out.print("회원명 : ");
        String userName = sc.nextLine();
        System.out.print("닉네임 : ");
        String nickname = sc.nextLine();
        System.out.print("핸드폰번호 : ");
        String phone = sc.nextLine();
        System.out.print("이메일 : ");
        String email = sc.nextLine();
        System.out.print("주소 : ");
        String address = sc.nextLine();
        System.out.print("관심사 : ");
        String interest = sc.nextLine();

        MemberVO member = new MemberVO(userId,userPwd,userName,nickname,phone,email,address,interest);
        return member;
    }




    public int updateMemberPart(){
        System.out.println("해당 회원이 확인 되었습니다");
        System.out.println("어떤 것을 수정 하시겠습니까?");
        int updateMemPartNo = 0;
        while(true) {
            System.out.println("1. 비밀번호 변경");
            System.out.println("2. 이메일 변경");
            System.out.println("3. 전화번호 변경");
            System.out.println("4. 주소 변경");
            System.out.println("0. 메인 메뉴로 돌아가기");
            System.out.print("번호 선택 : ");
            updateMemPartNo = Integer.parseInt(sc.nextLine());

            switch(updateMemPartNo){
                case 1:
                case 2:
                case 3:
                case 4:
                case 0:
                return updateMemPartNo;
                default: System.out.println("번호를 잘못 입력하셨습니다. 재입력해주세요.");
            }
            return updateMemPartNo;
        }
    }
    public String updateMemberPartInfo(){
        System.out.print("수정할 정보 입력 : ");
        String updtMemPartInfo = sc.nextLine();
        return updtMemPartInfo;
    }










}
