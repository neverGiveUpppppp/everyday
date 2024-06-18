package com.prac.MVC.jdbc.e.controller;

import com.prac.MVC.jdbc.b.model.vo.Employee;
import com.prac.MVC.jdbc.e.model.dao.EmploymentDAO;
import com.prac.MVC.jdbc.e.view.Employment;

import java.util.ArrayList;

public class EmploymentController {

    Employment empView = new Employment();
    EmploymentDAO empDao = new EmploymentDAO();

    public void empSelectAll(){
        ArrayList<Employee> al = empDao.empSelectAll();

        if(al != null){
            empView.empSelectAll(al);
        }else{
            empView.msgError("전체 조회 중 에러 발생");
        }
    }



    public void empSelect(){
        int empNo = empView.empNo();
        Employee em = empDao.empSelect(empNo);

        if(em != null){
            empView.empSelect(em);
        }else{
            empView.msgError("사원 조회 중 에러 발생");
        }

    }



    public void empInsert(){
        Employee em = empView.empInsert();
        int result = empDao.empInsert(em);
        
        if(result > 0){
            empView.msg(em.getEmpName()+" 사원 추가 완료");
        }else{
            empView.msgError("사원 추가 중 에러 발생");
        }


    }


}
