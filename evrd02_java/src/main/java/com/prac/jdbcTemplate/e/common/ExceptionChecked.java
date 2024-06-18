package com.prac.MVC.jdbcTemplate.e.common;



public class ExceptionChecked extends Exception{
    public ExceptionChecked(){}
    public ExceptionChecked(String str){
        super(str);
    }
    // Checked Exception & Unchecked Exception
    // 		Checked Exception : 예외처리가 필수
    // 		Unchecked Exception : 예외처리 선택

    // Unchecked Exception의 조상 Runtime Exception
    // Checked Exception의 조상은 Exception
}
