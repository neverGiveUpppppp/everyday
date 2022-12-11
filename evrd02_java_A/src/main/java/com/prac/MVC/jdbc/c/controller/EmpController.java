package com.prac.MVC.jdbc.c.controller;

import com.prac.MVC.jdbc.b.model.vo.Employee;
import com.prac.MVC.jdbc.c.model.dao.EmpDao;
import com.prac.MVC.jdbc.c.view.MenuAPI;

import java.util.ArrayList;

public class EmpController {

    private EmpDao empDao = new EmpDao();
    private MenuAPI menu = new MenuAPI();

    /**
     * 메뉴1. 전체사원 조회
     */
    public void selectAll() {
        ArrayList<Employee> aList = empDao.selectAll();

        if(aList == null){
            System.out.println("프로그램 오류 발생");
        }else if(aList.isEmpty()){
            System.out.println("사원이 없습니다");
        }else{
            menu.selectAll(aList);
        }

    }

    /**
     * 메뉴2.사원 1명 사번 조회
     */
    public void selectEmpOne() {
        int empNo = menu.selectEmpOneNum();
        Employee emp = empDao.selectEmpOne(empNo);

        if(emp == null){
            menu.displayError("프로그램 에러발생");
        }else {
            menu.selectEmpOne(emp);
        }

    }


    public void insertEmp() {
        Employee empInfo = menu.insertEmpInfo();
        int result = empDao.insertEmp(empInfo);

        if(result > 0 ){
            menu.insertEmp(result);
        }else{
            menu.displayError("프로그램 에러 발생. 문의하세요");
        }


    }






}
