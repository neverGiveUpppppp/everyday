package com.prac.array;

import java.util.Arrays;

public class arrayController {

    public static void main(String[] args) {


        // 최대값, 최소값 구하기
        int[] score =  { 79, 88, 91, 33, 100, 55, 95};

        int max = score[0];
        int min = score[0]; // array 초기화한 변수 값 이용

        for(int i=1; i < score.length;i++){
            if(score[i] > max){
                max = score[i]; // 기존 max보다 더 큰 값이 있으면 max변수에 넣어서 최대값을 찾는 방식
            }else if(score[i] < min){
                min = score[i]; // 기존 min보다 더 작은 값이 있으면 min변수에 넣어서 최소값을 찾는 방식
            }
        }
        System.out.println("최대값"+max);
        System.out.println("최대값"+min);
        // 로직은 배열 안에 첫번째 값을 넣고 for문으로 전체 값을 읽는데
        // 값을 하나씩 비교해서 더 큰 값이 있으면 max변수에 넣고 아니면 다음꺼 비교하고 하는 식으로 최대값, 최소값을 찾는 식




        // ch5-10,11 배열의 활용
        // 로또번호 6개 만들기
        // 45개의 정수값을 저장하기 위한 배열 생성.
        int[] ball = new int[45];

        // 배열의 각 요소에 1~45의 값을 저장한다.
        for(int i=0; i < ball.length; i++)
            ball[i] = i+1;
        System.out.println(Arrays.toString(ball));

        // 값 저장 임시변수 생성
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
            // a=b
            // b=c
            // c=a
            // 파이썬 할 때 배웠던 그 알고리즘 방식 적용해서 숫자 바꿔치기함
            // 여기까지 이미 랜덤 셔틀은 끝. 여기서 보여줄 앞 6자리만 뽑아서 보여주면 됨
        }

        // 배열 ball의 앞에서 부터 6개의 요소를 출력한다.
        for(int i=0; i < 6; i++)
            System.out.printf("ball[%d]=%d%n", i, ball[i]);
        //            lotto[0]=33
        //            lotto[1]=19
        //            lotto[2]=13
        //            lotto[3]=36
        //            lotto[4]=8
        //            lotto[5]=21



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



        //ch5-21~23 2차원 배열 예제
        // 학생별 평균,총점
        int[][] score01 = {
                         {100,100,100}
                        ,{20,20,20}
                        ,{30,30,30}
                        ,{40,40,40}
                        ,{50,50,50}
                        };

        // 과목별 총점
        int korTotal = 0, engTotal = 0, mathTotal = 0;
        System.out.println("번호 국어 영어 수학 총점 평균");
        System.out.println("=============================");

        for(int i=0; i < score01.length;i++){
            int sum = 0;        // 각 개인 총점
            float avg = 0.0f;   // 각 개인 평균

            korTotal += score01[i][0];
            engTotal += score01[i][1];
            mathTotal += score01[i][2];
            System.out.printf("%3d", i+1); // 3d는 왼쪽으로 세칸 공간차지 // 번호 1~5 출력

            for(int jj=0;jj < score01[i].length;jj++) {
                sum += score01[i][jj];
                System.out.printf("%5d", score01[i][jj]);
            }

            avg = sum/(float)score01[i].length;  // 평균계산
            System.out.printf("%5d %5.1f%n", sum, avg);

        System.out.println("=============================");
        System.out.printf("총점:%3d %4d %4d%n", korTotal, engTotal, mathTotal);

/*  
    출력 결과

            번호 국어 영어 수학 총점 평균
            =============================================
            1  100  100  100  300 100.0
            =============================
            총점:100  100  100
            2   20   20   20   60  20.0
            =============================
            총점:120  120  120
            3   30   30   30   90  30.0
            =============================
            총점:150  150  150
            4   40   40   40  120  40.0
            =============================
            총점:190  190  190
            5   50   50   50  150  50.0
            =============================
            총점:240  240  240

*/



            
        }


    }

}
