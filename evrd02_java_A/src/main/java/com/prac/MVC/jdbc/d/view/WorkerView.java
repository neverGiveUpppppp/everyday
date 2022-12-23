package com.prac.MVC.jdbc.d.view;

import com.prac.MVC.jdbc.b.model.vo.Employee;
import com.prac.MVC.jdbc.d.controller.WorkerController;

import java.util.ArrayList;
import java.util.Scanner;

public class WorkerView {

    Scanner sc = new Scanner(System.in);

    public void mainMenu() {
        WorkerController wc = new WorkerController();
        int worker = 0;
        do {
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
            worker = Integer.parseInt(sc.nextLine());

            switch (worker) {
                case 1:
                    wc.workerSelectAll();
                    break;
                case 2:
                    wc.workerSelectOneRe();
                    break;
                case 3:
                    wc.workerInsert();
                    break;
                case 4:
                    wc.workerUpdate();
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다");

            }


        } while (worker != 0);
    }


    public void msg(String str){
        System.out.println(str);
    }
    public void msgError(String str) {
        System.out.println(str);
    }


    public int empNo(){
        System.out.print("조회할 사원 번호 : ");
        int empNo = Integer.parseInt(sc.nextLine());
        return empNo;
    }

    public Employee workerInfoReceive(){
//        System.out.print("사번 : ");
//        int empNo = Integer.parseInt(sc.nextLine());
        System.out.print("이름 : ");
        String eName = sc.nextLine();
        System.out.print("포지션 : ");
        String job = sc.nextLine();
        int mgr = Integer.parseInt(sc.nextLine());
        System.out.print("샐러리 : ");
        int sal = Integer.parseInt(sc.nextLine());
        System.out.print("커미션 : ");
        int comm = Integer.parseInt(sc.nextLine());
        System.out.print("부서번호 : ");
        int depNo = Integer.parseInt(sc.nextLine());

        // Employee em = new Employee(empNo,eName,job,mgr,sal,comm,depNo);
        Employee em = new Employee(eName,job,mgr,sal,comm,depNo);
        return em;
    }



    /** 메뉴1.전사원 조회
     *
     * @param al
     */
    public void workerSelectAll(ArrayList<Employee> al) {
        for(int i=0; i < al.size();i++){
            System.out.println(al.get(i));
        }
    }


    public void workerSelectOne(Employee em) {
        System.out.println(em);
    }

    public void workerSelectOneRe(Employee e){
        System.out.println(e);
    }



    public void workerUpdate(Employee em){
        System.out.println(em);
    }



}
