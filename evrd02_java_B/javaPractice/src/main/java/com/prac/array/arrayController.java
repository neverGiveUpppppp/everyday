package com.prac.array;

import java.util.Arrays;

public class arrayController {

    public static void main(String[] args) {


        //
        int[] score =  { 79, 88, 91, 33, 100, 55, 95};

        int max = score[0];
        int min = score[0]; // array 초기화한 변수 값 이용

        for(int i=1; i < score.length;i++){
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
        int[] ball = new int[45];

        // 배열의 각 요소에 1~45의 값을 저장한다.
        for(int i=0; i < ball.length; i++)
            ball[i] = i+1;
        System.out.println(Arrays.toString(ball));

        int temp = 0; // 두 값을 바꾸는데 사용할 임시변수
        int j = 0;    // 임의의 값을 얻어서 저장할 변수

        // 배열의 i번째 요소와 임의의 요소에 저장된 값을 서로 바꿔서 값을 섞는다.
        // 0번째 부터 5번째 요소까지 모두 6개만 바꾼다.
        for(int i=0; i < 6; i++){
            j = (int)(Math.random()*45); // 0~44범위의 임의의 값을 얻는다.
            temp = ball[i];
            ball[i] = ball[j];
            ball[j] = temp;
            System.out.println(Arrays.toString(ball));
        }

        // 배열 ball의 앞에서 부터 6개의 요소를 출력한다.
        for(int i=0; i < 6; i++)
            System.out.printf("ball[%d]=%d%n", i, ball[i]);




        // ch5-12,13
        // 가위바위보 하기
        // index : 0~3-1 = 0~2
        String[] strArr = {"가위","바위","보"};
        System.out.println(Arrays.toString(strArr));

        for(int i=0; i<5; i++){
            int tmp = (int)(Math.random()*3);
            System.out.println(strArr[tmp]); // 0~2
        }


        System.out.println("매개변수의 개수 : "+args.length);
        for(int i=0; i < args.length; i++){
            System.out.println("args["+i+"] = \""+args[i]+"\"");
        }





    }

}
