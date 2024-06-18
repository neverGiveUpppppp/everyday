package com.prac.MVC.jdbc.a.model.dao;

import com.prac.MVC.jdbc.a.model.vo.Employee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeDAO {


    public ArrayList<Employee> selectAll() {
        ArrayList<Employee> aList = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rSet = null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "SCOTT", "qrwe");
            String query = "select * from EMP";
            stmt = con.createStatement();
            rSet = stmt.executeQuery(query);

            aList = new ArrayList<Employee>();
            while(rSet.next()){
                int empNo = rSet.getInt("EMPNO");
                String eName = rSet.getString("ENAME");
                String job = rSet.getString("JOB");
                int mgr = rSet.getInt("MGR");
                Date hireDate = rSet.getDate("HIREDATE");
                int sal = rSet.getInt("SAL");
                int comm = rSet.getInt("COMM");
                int deptNo = rSet.getInt("DEPTNO");

                Employee e = new Employee(empNo, eName, job, mgr, hireDate, sal, comm, deptNo );

                aList.add(e);
            }
            System.out.println(aList);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                rSet.close();
                stmt.close();
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return aList;
    }



}
