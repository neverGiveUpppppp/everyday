package com.prac.MVC.jdbc.e.view;

import com.prac.MVC.jdbc.b.model.vo.Employee;
import com.prac.MVC.jdbc.e.controller.EmploymentController;

import java.util.ArrayList;
import java.util.Scanner;

public class Employment {

    Scanner sc = new Scanner(System.in);

    public void mainMenu(){

        EmploymentController empCon = new EmploymentController();
        int menu = 0;

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
            menu = Integer.parseInt(sc.nextLine());

            switch(menu){
                case 1: empCon.empSelectAll(); break;
                case 2: empCon.empSelect(); break;
                case 3: empCon.empInsert(); break;
                default:
                    System.out.println("메뉴 번호를 잘못 입력했습니다.");
            }
        }while(menu != 0);
    }



    public void msg(String str){
        System.out.println(str);
    }
    public void msgError(String str){
        System.out.println(str);
    }

    public int empNo(){
        System.out.print("사원 번호 입력 : ");
        int empNo = Integer.parseInt(sc.nextLine());
        return empNo;
    }




    public void empSelectAll(ArrayList<Employee> al){
        for(int i = 0; i < al.size(); i++){
            System.out.println(al.get(i));
        }
    }


    public void empSelect(Employee em){
        System.out.println(em);
    }


    public Employee empInsert(){
        System.out.print("사원 이름 : ");
        String eName = sc.nextLine();
        System.out.print("사원 job : ");
        String job = sc.nextLine();
        System.out.print("사원 매니저번호 : ");
        int mgr = Integer.parseInt(sc.nextLine());
        System.out.print("사원 샐러리 : ");
        int sal = Integer.parseInt(sc.nextLine());
        System.out.print("사원 커미션 : ");
        int comm = Integer.parseInt(sc.nextLine());
        System.out.print("사원 부서 : ");
        int deptNo = Integer.parseInt(sc.nextLine());

        Employee em = new Employee(eName,job,mgr,sal,comm,deptNo);

        return em;
    }



}
