package com.prac.MVC.jdbc.c.model.dao;

import com.prac.MVC.jdbc.b.model.vo.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmpDao {



    public ArrayList<Employee> selectAll() {

        ArrayList<Employee> aList = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // DB 드라이버 설정
            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","SCOTT","qrwe"); // DB 연결 드라이버 설정

            String query =  "SELECT * FROM EMP"; // 쿼리문 작성
            stmt = conn.createStatement();
            rset = stmt.executeQuery(query);

            aList = new ArrayList<Employee>();
            while (rset.next()){
                // 조회할 컬럼명 지정
                // 여기서는 한테이블 전체 조회할거라 테이블 전체컬럼이지만 다중 조인시는 조회할 컬럼만 명시해야함

                int empNo = rset.getInt("EMPNO");
                String eName = rset.getString("ENAME");
                String job = rset.getString("JOB");
                int mgr = rset.getInt("MGR");
                Date hireDate = rset.getDate("HIREDATE");
                int sal = rset.getInt("SAL");
                int deptNo = rset.getInt("DEPTNO");

                Employee emp = new Employee(empNo,eName,job,mgr,hireDate,sal,empNo,deptNo);
                aList.add(emp);
            }

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                stmt.close();
                rset.close();
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return aList;
    }


    public Employee selectEmpOne(int empNo) {

        Employee emp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "SCOTT", "qrwe");

            String query = "SELECT * FROM EMP WHERE EMPNO = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, empNo);

            rset = pstmt.executeQuery();

            if (rset.next()) {

                String eName = rset.getString("ENAME");
                String job = rset.getString("JOB");
                int mgr = rset.getInt("MGR");
                Date hireDate = rset.getDate("HIREDATE");
                int sal = rset.getInt("SAL");
                int comm = rset.getInt("COMM");
                int deptNo = rset.getInt("DEPTNO");

                emp = new Employee(empNo, eName, job, mgr, hireDate, sal, comm, deptNo);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
                rset.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return emp;
    }


    public int insertEmp(Employee empInfo) {

        ArrayList<Employee> aList = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","SCOTT","qrwe");

            String query = "INSERT INTO EMP VALUES(?, ?, ?, ?, SYSDATE, ? ,? ,?)";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,empInfo.getEmpNo());
            pstmt.setString(2,empInfo.getEmpName());
            pstmt.setString(3,empInfo.getJob());
            pstmt.setInt(4, empInfo.getMgr());
            pstmt.setInt(5,empInfo.getSal());
            pstmt.setInt(6,empInfo.getComm());
            pstmt.setInt(7,empInfo.getDeptNo());

            result = pstmt.executeUpdate();

            if(result > 0){
                conn.commit();
            }else{
                conn.rollback();
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
        return result;
    }


    public int deleteEmp(int empNum) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","qrwe");

            String query = "DELETE FROM EMP WHERE EMPNO = ? ";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,empNum);
            result = pstmt.executeUpdate();

            if(result > 0){
                conn.commit();
            }else{
                conn.rollback();
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
        return result;
    }

    public int updateEmp(Employee emp) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","qrwe");

            String query = "UPDATE EMP SET ENAME = ?, JOB = ?, MGR = ?, SAL = ?, COMM = ?, DEPTNO = ? WHERE EMPNO = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, emp.getEmpName());
            pstmt.setString(2, emp.getJob());
            pstmt.setInt(3, emp.getMgr());
            pstmt.setInt(4, emp.getSal());
            pstmt.setInt(5, emp.getComm());
            pstmt.setInt(6, emp.getDeptNo());
            pstmt.setInt(7, emp.getEmpNo());

            result = pstmt.executeUpdate();

            if(result > 0){
                conn.commit();
            }else{
                conn.rollback();
            }

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }







}



