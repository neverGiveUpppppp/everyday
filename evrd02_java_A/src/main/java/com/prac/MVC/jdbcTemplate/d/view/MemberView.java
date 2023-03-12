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
                case 3: mCon.postMember(); break;
                case 4: mCon.putMember(); break;
                case 5: mCon.deleteMember(); break;
                case 0: mCon.exitApp(); return ; // return ;이 메인메소드로 돌려보내서 앱 종료시킴
                default:
                    System.out.println("메뉴 번호가 잘못 입력되었습니다. 재입력 해주세요.");
            }
        }
    }


    public void message(String string){
        System.out.println(string);
    }

    public String checkId(){
        System.out.print("회원 아이디 : ");
        String checkId = sc.nextLine();
        return checkId;
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



    public MemberJSPTable postMember(){
        System.out.println("---회원 정보 입력---");
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
        MemberJSPTable memberVo = new MemberJSPTable(userId, userPwd,userName,nickname,phone,email,address,interest);
        return memberVo;
    }





    public int putMember(){

        int menuNum = 0;

        while(true) {
            System.out.println("=== 변경할 정보를 선택해주세요 ===");
            System.out.println("1.비밀번호 변경");
            System.out.println("2.이름 변경");
            System.out.println("3.닉네임 변경");
            System.out.println("4.이메일 변경");
            System.out.println("5.주소 변경");
            System.out.print("메뉴 선택 : ");
            menuNum = Integer.parseInt(sc.nextLine());

            switch (menuNum) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 0: return menuNum;
                default: System.out.println("메뉴 번호를 잘못 선택했습니다. 다시 선택해주세요");
            }
        }
    }
    public String putMemberContxt(){
        System.out.print("변경할 정보 입력: ");
        String putContext = sc.nextLine();
        return putContext;
    }



    public char deleteMember(){
        System.out.println("해당 회원을 탈퇴시킵니다");
        System.out.print("정말로 탈퇴 처리 하시겠습니까?(Y/N) : ");
        char YN = sc.nextLine().toUpperCase().charAt(0);
        return YN;
    }



//    public MemberJSPTable putMember(){
//        System.out.print("====== 회원 정보 수정 ====== ");
//        System.out.println("회원 아이디 : ");
//        String userId = sc.nextLine();
//        System.out.println("회원 비밀번호 : ");
//        String userPwd = sc.nextLine();
//        System.out.println("회원 이름 : ");
//        String username = sc.nextLine();
//        System.out.println("회원 닉네임 : ");
//        String nickname = sc.nextLine();
//        System.out.println("회원 핸드폰번호 : ");
//        String userPhone = sc.nextLine();
//        System.out.println("회원 이메일 : ");
//        String userEmail = sc.nextLine();
//        System.out.println("회원 주소 : ");
//        String userAdres =  sc.nextLine();
//        System.out.println("회원 관심사 : ");
//        String userIntrest = sc.nextLine();
//
//        MemberJSPTable mem = new MemberJSPTable(userId,userPwd,username,nickname,userPhone, userEmail, userAdres,userIntrest);
//        return mem;
//    }






    public char exitApp(){
        System.out.print("정말 종료하시겠습니까?(Y/N) : ");
        char YN = sc.nextLine().toUpperCase().charAt(0);
        return YN;
    }

}
