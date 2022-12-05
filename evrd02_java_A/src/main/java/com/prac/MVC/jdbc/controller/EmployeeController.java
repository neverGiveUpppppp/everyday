package com.prac.MVC.jdbc.controller;

import com.prac.MVC.jdbc.model.dao.EmployeeDAO;
import com.prac.MVC.jdbc.model.vo.Employee;
import com.prac.MVC.jdbc.view.Menu;

import java.util.ArrayList;

public class EmployeeController {

    private EmployeeDAO empDAO = new EmployeeDAO();
    private Menu menu = new Menu();
    public void selectAll() {

        ArrayList<Employee> aList = empDAO.selectAll();





    }



}
