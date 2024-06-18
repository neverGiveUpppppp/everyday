package com.prac.MVC.jdbc.b.controller;

import com.prac.MVC.jdbc.b.model.vo.Employee;
import com.prac.MVC.jdbc.b.model.dao.EmpDAO;
import com.prac.MVC.jdbc.b.view.Menu;

import java.util.ArrayList;

public class EmpController {

    private EmpDAO empDAO = new EmpDAO();
    private Menu menu = new Menu();
    public void selectAll() {
        ArrayList<Employee> list = empDAO.selectAll();

//        if(list != null){
//            menu.selectAll(list);
//        }else{
//            menu.displayError("조회 에러 발생");
//        }
        if(list.isEmpty()) {
            menu.displayError("조회 결과가 없습니다.");
        }else {
            // list가 비어있지 않을 때 = 사원 존재할 때
            menu.selectAll(list); // DAO에서 받아온 list의 값이 존재하니 받아온 데이터를 넘겨줌
        }

    }


}
