package com.prac.MVC.jdbc.d.model.dao;

import com.prac.MVC.jdbc.b.model.vo.Employee;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class WorkerDao {


    public ArrayList<Employee> workerSelectAll() {

        ArrayList<Employee> al = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","SCOTT","qrwe");

            String query = "SELECT * FROM EMP";

            stmt = conn.createStatement();
            rset = stmt.executeQuery(query);

            al = new ArrayList<>();
            while(rset.next()){

                int empNo = rset.getInt("EMPNO");
                String eName = rset.getString("ENAME");
                String job = rset.getString("JOB");
                int mgr = rset.getInt("MGR");
                Date hireDate = rset.getDate("HIREDATE");
                int sal = rset.getInt("SAL");
                int comm = rset.getInt("COMM");
                int deptNo = rset.getInt("DEPTNO");

                Employee em = new Employee(empNo,eName, job,mgr, hireDate, sal, comm, deptNo);
                al.add(em);
            }

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                conn.close();
                stmt.close();
                rset.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return al;
    }




    public Employee workerSelectOne(int empNo){
        Employee em = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","qrwe");

            String query = "SELECT * FROM EMP WHERE EMPNO = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,empNo);
            rset = pstmt.executeQuery();

            while(rset.next()){
                String empName = rset.getString("EMPNO");
                String job = rset.getString("JOB");
                int mgr = rset.getInt("MGR");
                Date hireDate = rset.getDate("HIREDATE");
                int sal = rset.getInt("SAL");
                int comm = rset.getInt("COMM");
                int deptNo = rset.getInt("DEPTNO");

                em = new Employee(empName, job, mgr, hireDate, sal, comm, deptNo);
            }

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                conn.close();
                pstmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return em;
    }


    public Employee workerSelectOneRe(int empNo){
        Employee e = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rst = null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","SCOTT","qrwe");

            String query = "SELECT * FROM EMP WHERE EMPNO = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,empNo);
            rst = pstmt.executeQuery();

            if(rst.next()){
                String empName = rst.getString("ENAME");
                String job = rst.getString("JOB");
                int mgr = rst.getInt("MGR");
                Date hireDate = rst.getDate("HIREDATE");
                int sal = rst.getInt("SAl");
                int comm = rst.getInt("COMM");
                int deptNo = rst.getInt("DEPTNO");

                e = new Employee(empNo,empName,job,mgr,hireDate,sal,comm,deptNo);
            }

        }catch(ClassNotFoundException e1){
            e1.printStackTrace();
        }catch(SQLException e1){
            e1.printStackTrace();
        }finally{
            try {
                rst.close();	// 발생 시킨 객체들 전부 닫아줘야한다
                pstmt.close();
                conn.close(); 	// 규칙 : 가장 나중에 발생한 것부터 닫아준다
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return e;
    }



    public int workerInsert(Employee em){

        Connection connection = null;
        PreparedStatement pstmt = null;
        int result = 0;

        try{    // 사번을 SEQ로 처리

            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","SCOTT","qrwe");
            String q = "INSERT INTO EMP VALUES(SEQ_SCOTT.NEXTVAL,?,?,?,SYSDATE,?,?,?)";
            pstmt = connection.prepareStatement(q);
            pstmt.setString(1,em.getEmpName());
            pstmt.setString(2,em.getJob());
            pstmt.setInt(3,em.getMgr());
            pstmt.setInt(4,em.getSal());
            pstmt.setInt(5,em.getComm());
            pstmt.setInt(6,em.getDeptNo());
            result = pstmt.executeUpdate();

            if(result > 0){
                connection.commit();
            }else{
                connection.rollback();
            }


        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                pstmt.close();
                connection.close(); 	// 규칙 : 가장 나중에 발생한 것부터 닫아준다
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return result;
    }



    public int workerUpdate(Employee em){

        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","SCOTT","qrwe");

            String query = "UPDATE EMP SET ENAME=?, JOB=?,MGR=?,SAL=?,COMM=?,DEPTNO=? WHERE EMPNO=?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1,em.getEmpName());
            pstmt.setString(2,em.getJob());
            pstmt.setInt(3,em.getMgr());
            pstmt.setInt(4,em.getSal());
            pstmt.setInt(5,em.getComm());
            pstmt.setInt(6, em.getDeptNo());
            pstmt.setInt(7,em.getEmpNo());
            result = pstmt.executeUpdate();

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                pstmt.close();
                con.close(); 	// 규칙 : 가장 나중에 발생한 것부터 닫아준다
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }



}
