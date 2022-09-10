package com.prac.array;

import java.util.Arrays;

public class arrayControllerPrac {

    public static void main(String[] args) {

        // 최대값, 최소값 구하기
        int[] score = {79, 88, 91, 33, 100, 55,95};

        int max = score[0];
        int min = score[0];

        for(int i=0; i < score.length; i++){
            if(score[i] > max){
                max = score[i];
            }else if(score[i] < min){
                min = score[i];
            }
        }
        System.out.println("최대값"+max);
        System.out.println("최대값"+min);


        // ch5-10,11 배열의 활용
        // 로또번호 6개 만들기
        // 45개의 정수값을 저장하기 위한 배열 생성.
        // 배열의 각 요소에 1~45의 값을 저장한다.
        int[] lNumber = new int[45];

        // 값 저장 임시변수 생성
        int temp = 0;
        int j = 0;

        for(int i=0; i < lNumber.length; i++)
            lNumber[i] = i+1;
        System.out.println(Arrays.toString(lNumber));


        // 배열의 i번째 요소와 임의의 요소에 저장된 값을 서로 바꿔서 값을 섞는다.
        // 0번째 부터 5번째 요소까지 모두 6개만 바꾼다.
        for(int i=0; i < 6; i++){
            j = (int)(Math.random()*45);
            temp = lNumber[i];
            lNumber[i] = lNumber[j];
            lNumber[j] = temp;
            System.out.println(Arrays.toString(lNumber));
        }

        // 배열 ball의 앞에서 부터 6개의 요소를 출력한다.
        for(int i=0; i < 6; i++)
            System.out.printf("lotto_Number[%d]=%d%n",i,lNumber[i]);



    }

}



