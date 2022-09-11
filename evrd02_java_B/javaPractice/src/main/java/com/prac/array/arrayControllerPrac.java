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


        // 최대값, 최소값 구하기
        int[] score2 = {79, 88, 91, 33, 100, 55,95};

        int max2 = score2[0];
        int min2 = score2[0];


        for(int i = 0; i < score2.length; i++){
            if(score2[i] > max2){
                max2 = score2[i];
            }else if(score[i] < min2){
                min2 = score2[i];
            }
        }
        System.out.println(max2);
        System.out.println(min2);

        int[] score3 = {79, 88, 91, 33, 100, 55,95};
        int max3 = score3[0];
        int min3 = score3[0];

        for(int i = 0; i < score3.length; i++){
            if(score3[i]  > max3){
                max3 = score3[i];
            }else if(score3[i] < min3){
                min3 = score3[i];
            }
        }
        System.out.println(max3);
        System.out.println(min3);




        // 로또번호 6개 만들기
        // 45개의 정수값을 저장하기 위한 배열 생성.
        int[] lotto = new int[45];

        // 배열의 각 요소에 1~45의 값을 저장한다.
        for(int i = 0; i < lotto.length; i++){
            lotto[i] = i+1;
        }
        System.out.println(Arrays.toString(lotto)); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45]

        // 값 저장 임시변수 생성
        int temp2 = 0;
        int j2 = 0;

        // 배열의 i번째 요소와 임의의 요소에 저장된 값을 서로 바꿔서 값을 섞는다.
        // 0번째 부터 5번째 요소까지 모두 6개만 바꾼다.
        for(int i=0; i < 6; i++){
            j2 = (int)(Math.random()*45);
            temp2 = lotto[i];
            lotto[i] = lotto[j2];
            lotto[j2] = temp2;
            System.out.println(lotto); // [I@610455d6
            // 주소값이  갑자기 왜 나왔을까?
            // 객체 주소로 저장되는 배열 그냥 찍으니까 주소값 나옴ㅋㅋ Arrays.toString으로 찍으면 제대로 나올 듯
            System.out.println(Arrays.toString(lotto)); // [10, 23, 1, 34, 40, 16, 7, 8, 9, 3, 11, 12, 13, 14, 15, 6, 17, 18, 19, 20, 21, 22, 2, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 4, 35, 36, 37, 38, 39, 5, 41, 42, 43, 44, 45]
            // Arrays.toString으로 찍으니 제대로 값 잘 나옴. 이제 앞에서 6자리만 뽑아서 보여주면 된다
        }

        // 배열 ball의 앞에서 부터 6개의 요소를 출력한다.
        for(int i=0; i < 6; i++){
            System.out.print(lotto[i] + " ");   // 33 19 13 36 8 21
        }
        System.out.println();
        for(int i=0; i < 6; i++){
            System.out.printf("lotto[%d]=%d%n", i, lotto[i]);
//            lotto[0]=33
//            lotto[1]=19
//            lotto[2]=13
//            lotto[3]=36
//            lotto[4]=8
//            lotto[5]=21
        }







    }


    // 최대값, 최소값 구하기




    // 로또번호 6개 만들기
    // 45개의 정수값을 저장하기 위한 배열 생성.
    // 배열의 각 요소에 1~45의 값을 저장한다.

    // 값 저장 임시변수 생성

    // 배열의 i번째 요소와 임의의 요소에 저장된 값을 서로 바꿔서 값을 섞는다.
    // 0번째 부터 5번째 요소까지 모두 6개만 바꾼다.

    // 배열 ball의 앞에서 부터 6개의 요소를 출력한다.



}



