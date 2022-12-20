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
            menu.msg("프로그램 오류 발생");
        }else if(aList.isEmpty()){
            menu.msg("해당 사원이 없습니다");
        }else{
            menu.selectAll(aList);
        }

    }

    /**
     * 메뉴2.사원 1명 사번 조회
     */
    public void selectEmpOne() {
        int empNo = menu.empNum();
        Employee emp = empDao.selectEmpOne(empNo);

        if(emp == null){
            menu.msgError("조회 중 프로그램 에러발생");
        }else {
            menu.selectEmpOne(emp);
        }

    }


    public void insertEmp() {
        Employee empInfo = menu.insertEmpInfo();
        int result = empDao.insertEmp(empInfo);

        if(result > 0 ){
            menu.msg(result+"명이 추가 되었습니다");
        }else{
            menu.msgError("추가 중 프로그램 에러 발생. 문의하세요");
        }


    }


    public void deleteEmp() {
        int empNum = menu.empNum();
        String answer = menu.deleteEmp();

        if(answer.toUpperCase().equals("Y")){
            int result = empDao.deleteEmp(empNum);
            if(result > 0){
                menu.msg(empNum+"번의 사원 정보가 삭제되었습니다");
            }else{
                menu.msgError("삭제 중 에러가 발생했습니다");
            }

        }else if(answer.toUpperCase().equals("N")){
            menu.msg("삭제를 취소합니다");
        }else{
            menu.msgError("문자를 잘못 입력하셨습니다.");
        }



    }

    public void updateEmp() {
        int empNum = menu.empNum();
        Employee emp = menu.updateEmp();
        emp.setEmpNo(empNum);
        int result = empDao.updateEmp(emp);
        
        // 사번 받아서 진행하는 코드 방식 구현해보기

        if(result > 0){
            menu.msg("사원 정보 수정 완료");
        }else{
            menu.msgError("수정 중 에러 발생");
        }

    }




}
