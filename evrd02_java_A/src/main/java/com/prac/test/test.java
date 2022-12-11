package com.prac.test;

public class test {
    public static void main(String[] args) {


        // isEmpty() vs == null 차이
        String testEmpty = "";
        String testNull = null;

        // 1. 공백값 & isEmpty() 체크
        if(testEmpty.isEmpty()){
            System.out.println("1 : "+testEmpty);
        }   // 공백값이라 true 되서 공백 출력

        // 2. null & isEmpty() 체크
        if(testNull.isEmpty()){
            System.out.println("2 : "+testNull);
        }   // NullPointerException

        // 3. 공백값 & null 체크
        if(testEmpty == null){
            System.out.println("3 : " + testEmpty);
        }   // 공백값이 있기 때문에 null 아님. false라 출력x

        // 4. null & null 체크
        if(testNull == null){
            System.out.println("4 : "+ testNull);
        }   // null이라 true되서 null 출력


    }



}
