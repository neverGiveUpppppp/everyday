package com.prac.MVC.jdbc.c.view;

import com.prac.MVC.jdbc.b.model.vo.Employee;
import com.prac.MVC.jdbc.c.controller.EmpController;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuAPI {

    private Scanner sc = new Scanner(System.in);

    public void MeinMenu(){

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
                case 2: ec.selectEmpOne(); break;
                case 3: ec.insertEmp(); break;
                case 0: System.out.println("프로그램을 종료합니다"); break;
                default: System.out.println("잘못 입력했습니다");
            }
        }while(user != 0);
    }

    // 에러 처리문
    public void displayError(String str){
        System.out.println("에러 발생 : "+str);
    }

    /**
     * 메뉴1.전체 사원 조회
     * @param aList
     */
    public void selectAll(ArrayList<Employee> aList) {
        for(int i=0; i < aList.size(); i++){
            System.out.println(aList.get(i));
        }
    }

    /**
     * 메뉴2. 사번으로 사원 조회
     * @param emp
     */
    public void selectEmpOne(Employee emp) {
        System.out.println(emp);
    }
    public int selectEmpOneNum(){
        System.out.print("사번 입력 : ");
        int empNo = Integer.parseInt(sc.nextLine());
        return empNo;
    }

    public void insertEmp(int result) {
        System.out.println("사원 추가 완료 : "+result+"명");
    }
    public Employee insertEmpInfo() {
        System.out.print("사번 : ");
        int empNo = Integer.parseInt(sc.nextLine());
        System.out.print("이름 : ");
        String eName = sc.nextLine();
        System.out.print("job : ");
        String job = sc.nextLine();
        System.out.print("매니저번호 : ");
        int mgr = Integer.parseInt(sc.nextLine());
        System.out.print("샐러리 : ");
        int sal = Integer.parseInt(sc.nextLine());
        System.out.print("커미션 : ");
        int comm = Integer.parseInt(sc.nextLine());
        System.out.print("부서 : ");
        int deptNo = Integer.parseInt(sc.nextLine());

        Employee emp = new Employee(empNo,eName,job,mgr,sal,comm,deptNo);
        return emp;
    }



}
