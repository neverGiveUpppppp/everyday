package com.prac.MVC.jdbc.view;

import com.prac.MVC.jdbc.controller.EmployeeController;

import java.util.Scanner;

public class Menu {

    public void mainMenu() {

        Scanner sc = new Scanner(System.in);
        EmployeeController ec = new EmployeeController();

        int command = 0;
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

            command = Integer.parseInt(sc.nextLine());

            switch (command){
                case 1: ec.selectAll();
                case 2: ec.selectEmp();
                case 3: ec.insertEmp();
                case 4: ec.updateEmp();
                case 5: ec.deleteEmp();
                case 0: ec.endProgramm();
            }

        }while(command != 0);

    }

}
