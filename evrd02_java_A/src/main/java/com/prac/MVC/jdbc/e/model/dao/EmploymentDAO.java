package com.prac.MVC.jdbc.e.model.dao;

import com.prac.MVC.jdbc.b.model.vo.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmploymentDAO {

    public ArrayList<Employee> empSelectAll(){

        ArrayList<Employee> al = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rset = null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","SCOTT","qrwe");

            String query = "SELECT * FROM EMP";

            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            al = new ArrayList<Employee>();
            while(rset.next()){

                int empNo = rset.getInt("EMPNO");
                String eName = rset.getString("ENAME");
                String job = rset.getString("JOB");
                int mgr = rset.getInt("MGR");
                Date hireDate = rset.getDate("HIREDATE");
                int sal = rset.getInt("SAL");
                int comm = rset.getInt("COMM");
                int depNo = rset.getInt("DEPTNO");

                Employee e = new Employee(empNo,eName,job,mgr,hireDate,sal,comm,depNo);
                al.add(e);
            }

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                rset.close();
                stmt.close();
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return al;
    }




    public Employee empSelect(int empNo){

        Employee em = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rst = null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","SCOTT","qrwe");

            String query = "SELECT * FROM EMP WHERE EMPNO = ?";

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,empNo);
            rst = pstmt.executeQuery();

            if(rst.next()){
                String eName = rst.getString("ENAME");
                String job = rst.getString("JOB");
                int mgr = rst.getInt("MGR");
                Date hireDate = rst.getDate("HIREDATE");
                int sal = rst.getInt("SAL");
                int comm = rst.getInt("COMM");
                int deptNo = rst.getInt("DEPTNO");

                em = new Employee(empNo,eName,job,mgr,hireDate,sal,comm,deptNo);
            }


        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }



        return em;
    }







}


