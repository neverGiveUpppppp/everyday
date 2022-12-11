package com.prac.MVC.jdbc.b.view;

import com.prac.MVC.jdbc.b.controller.EmpController;
import com.prac.MVC.jdbc.b.model.vo.Employee;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private Scanner sc = new Scanner(System.in);

    public void mainMenu(){

        EmpController ec = new EmpController();

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
//                case 2: ec.selectEmp(); break;
//                case 3: ec.insertEmp(); break;
//                case 4: ec.updateEmp(); break;
//                case 5: ec.deleteEmp(); break;
//                case 0: System.out.println("프로그램 종료");
                default : System.out.println("잘못된 입력");

            }

        }while(user != 0);

    }

    public void displayError(String string) {
        System.out.println("서비스 요청 실패 : "+ string);
    }


    // case1.전체사원 정보 조회
    public void selectAll(ArrayList<Employee> list) {
        for(int i=0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }




}
