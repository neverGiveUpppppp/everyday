package com.prac.MVC.jdbc.a.view;

import com.prac.MVC.jdbc.a.controller.EmployeeController;

import java.util.Scanner;

public class Menu {

    private Scanner sc = new Scanner(System.in);
    public void mainMenu(){

        EmployeeController ec = new EmployeeController();

        int user = 0;
        do{
            System.out.println("====================");
            System.out.println("[Main Menu]");
            System.out.println("1.전체 사원 정보 조회");
            System.out.println("2.사번으로 사원 정보 조회");
            System.out.println("3.새로운 사원 정보 추가");
            System.out.println("4.사번으로 사원 정보 수정");
            System.out.println("5.사번으로 사원 정보 삭제");
            System.out.println("0.프로그램 종료");
            System.out.println("====================");
            System.out.print("메뉴 선택 : ");

            user = Integer.parseInt(sc.nextLine());

            switch(user){
                case 1: ec.selectAll(); break;
//                case 2: ec.selectEmployee(); break;
//                case 3: ec.insertEmployee(); break;
//                case 4: ec.updateEmplyee(); break;
//                case 5: ec.deleteEmployee(); break;
                case 0: System.out.println("프로그램 종료"); break;
                default: System.out.println("잘못 입력");
            }

        }while(user != 0);


    }


}
