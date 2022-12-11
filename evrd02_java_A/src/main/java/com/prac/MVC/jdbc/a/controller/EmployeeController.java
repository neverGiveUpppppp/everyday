package com.prac.MVC.jdbc.a.controller;

import com.prac.MVC.jdbc.a.model.dao.EmployeeDAO;
import com.prac.MVC.jdbc.a.model.vo.Employee;

import java.util.ArrayList;

public class EmployeeController {

    private EmployeeDAO empDAO = new EmployeeDAO();
    public void selectAll() {
        ArrayList<Employee> aList = new ArrayList();

        aList = empDAO.selectAll();


    }
}
