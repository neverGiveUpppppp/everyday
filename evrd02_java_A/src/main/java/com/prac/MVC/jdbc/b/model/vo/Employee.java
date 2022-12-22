package com.prac.MVC.jdbc.b.model.vo;

import java.sql.Date;

public class Employee {



    private int empNo; // 사번
    private String empName; // 이름
    private String job; // 직책
    private int mgr; // 직속 상사(manager)
    private Date hireDate; // java.sql.Date // 고용일
    private int sal; // 급여
    private int comm; // 커미션(인센티브)
    private int deptNo; // 부서번호

    // default constructor
    public Employee() {}

    // 매개변수 있는 생성자
    public Employee(int empNo, String empName, String job, int mgr, Date hireDate, int sal, int comm, int deptNo) {
        this.empNo = empNo;
        this.empName = empName;
        this.job = job;
        this.mgr = mgr;
        this.hireDate = hireDate;
        this.sal = sal;
        this.comm = comm;
        this.deptNo = deptNo;
    }

    public Employee(int empNo, String empName, String job, int mgr, int sal, int comm, int deptNo) {
        this.empNo = empNo;
        this.empName = empName;
        this.job = job;
        this.mgr = mgr;
        this.sal = sal;
        this.comm = comm;
        this.deptNo = deptNo;
    }

    public Employee(String job, int sal, int comm, int empNo) {
	    this(job,sal,comm);
        this.empNo = empNo;
    }

    public Employee(String job, int sal, int comm) {
        this.job = job;
        this.sal = sal;
        this.comm = comm;
    }

    public Employee(String empName, String job, int mgr, int sal, int comm, int deptNo) {
        this.empName = empName;
        this.job = job;
        this.mgr = mgr;
        this.sal = sal;
        this.comm = comm;
        this.deptNo = deptNo;
    }

    public Employee( String empName, String job, int mgr, int sal, int comm, int deptNo, int empNo) {
        this.empName = empName;
        this.job = job;
        this.mgr = mgr;
        this.sal = sal;
        this.comm = comm;
        this.deptNo = deptNo;
        this.empNo = empNo;
    }

    public Employee(String empName, String job, int mgr, Date hireDate, int sal, int comm, int deptNo) {
        this.empName = empName;
        this.job = job;
        this.mgr = mgr;
        this.hireDate = hireDate;
        this.sal = sal;
        this.comm = comm;
        this.deptNo = deptNo;
    }


    @Override
    public String toString() {
        return empNo + " / " + empName + " / " + job + " / " + mgr + " / " + hireDate +  " / " + sal +
                " / "  + comm +  " / "  + deptNo;
    }




    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMgr() {
        return mgr;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public int getComm() {
        return comm;
    }

    public void setComm(int comm) {
        this.comm = comm;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }


}
