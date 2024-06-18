package com.prac.MVC.jdbc.b.model.dao;

import com.prac.MVC.jdbc.b.model.vo.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmpDAO {

    ArrayList<Employee> list = null;
    Connection conn = null;
    Statement stmt =  null;
    ResultSet rset =  null;
    public ArrayList<Employee> selectAll() {

        try{
            // 1.DB드라이버 생성
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 2.DB 연결
            //   - 설정한 드라이버에 세부설정으로 DB 접속 연결
            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "SCOTT", "qrwe");

            // 3. 쿼리문 작성
            //   - 보낼 쿼리문 작성
            String query = "SELECT * FROM EMP";

            // 4. 쿼리문 보낼 객체 생성
            stmt = conn.createStatement();

            // 5. 쿼리문 전달 및 리턴받기
            //    - 쿼리문 전달 : excuteQuery()
            //    - 쿼리결과 리턴받기 : interface ResultSet 객체
            rset = stmt.executeQuery(query);

            list = new ArrayList<Employee>();
            while(rset.next()){
                int empNo = rset.getInt("EMPNO");
                String eName = rset.getString("ENAME");
                String job = rset.getString("JOB");
                int mgr = rset.getInt("MGR");
                Date hireDate = rset.getDate("HIREDATE");
                int sal = rset.getInt("SAL");
                int comm = rset.getInt("COMM");
                int deptNo = rset.getInt("DEPTNO");

                Employee emp = new Employee(empNo, eName, job, mgr, hireDate, sal, comm, deptNo );

                list.add(emp);
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                stmt.close();
                rset.close();
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return list;
    }

}
