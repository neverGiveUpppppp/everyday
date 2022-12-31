package com.prac.MVC.jdbcTemplate.vo;

import java.sql.Date;

public class MemberVo {

    private String memberId;
    private String memberPwd;
    private String memberName;
    private char gender;
    private String email;
    private String phone;
    private int age;
    private String address;
    private Date enrollDate;


    public MemberVo() {}

    public MemberVo(String memberId, String memberPwd, String memberName, char gender,
                  String email, String phone, int age, String address, Date enrollDate) {
        this(memberId,memberPwd,memberName,gender,email,phone,age,address);
        this.enrollDate = enrollDate;
    }

    public MemberVo(String memberId, String memberPwd, String memberName, char gender,
                  String email, String phone, int age, String address) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.address = address;
    }


    // getter & setter
    public String getMemberId() {
        return memberId;
    }
    public String getMemberPwd() {
        return memberPwd;
    }
    public String getMemberName() {
        return memberName;
    }
    public char getGender() {
        return gender;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public int getAge() {
        return age;
    }
    public String getAddress() {
        return address;
    }
    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    //toString
//	@Override
//	public String toString() {
//		return memberId, memberPwd, memberName, gender, email, phone, address, age, enrollDate;
//
//	}

    @Override
    public String toString() {
        return memberId+" , "+memberPwd+" , "+memberName+" , "+gender+" , "+email+" , "+phone+" , "
                +phone+" , "+age+" , "+enrollDate;
    }


}
