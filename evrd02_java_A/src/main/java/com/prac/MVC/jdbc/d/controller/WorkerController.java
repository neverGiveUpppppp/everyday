package com.prac.MVC.jdbc.d.controller;

import com.prac.MVC.jdbc.b.model.vo.Employee;
import com.prac.MVC.jdbc.d.model.dao.WorkerDao;
import com.prac.MVC.jdbc.d.view.WorkerView;

import java.util.ArrayList;

public class WorkerController {

    WorkerView wv = new WorkerView();
    WorkerDao wd = new WorkerDao();

    public void workerSelectAll() {
        ArrayList<Employee> al = wd.workerSelectAll();

        if(al != null){
            wv.msg("전 사원 조회 성공");
            wv.workerSelectAll(al);
        }else if(al.isEmpty()){
            wv.msg("isEmpty() 실험");
        }else{
            wv.msgError("조회 중 오류 발생");
        }
    }


    public void workerSelectOne(){
        int empNo = wv.empNo();
        Employee em = wd.workerSelectOne(empNo);

        if(em != null){
            wv.msg("사번 사원 조회 성공");
            wv.workerSelectOne(em);
        }else{
            wv.msgError("사번 사원 조회 중 에러발생");
        }


    }


    public void workerSelectOneRe() {
        int empNo = wv.empNo();
        Employee e = wd.workerSelectOneRe(empNo);

        if(e != null){
            wv.msg("사원 정보 조회 성공");
            wv.workerSelectOneRe(e);
        }else{
            wv.msgError("조회 중 에러 발생");
        }

    }


    public void workerInsert(){
        Employee em = wv.workerInfoReceive();
        int result = wd.workerInsert(em);

        if(result > 0){
            wv.msg("사원 추가 완료");
        }else{
            wv.msgError("추가 중 오류 발생");
        }

    }



    public void workerUpdate(){
        int empNo = wv.empNo();
        Employee em = wv.workerInfoReceive();
        em.setEmpNo(empNo);
        int result = wd.workerUpdate(em);

        if(result > 0){
            wv.workerUpdate(empNo);
            wv.msgNo(empNo,"번의 사원 정보가 수정 되었습니다");
        }else{
            wv.msgError("사원 정보 수정 중 에러 발생");
        }



    }




}
