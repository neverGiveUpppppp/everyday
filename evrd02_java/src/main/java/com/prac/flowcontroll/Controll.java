package com.prac.flowcontroll;

import java.util.Scanner;

public class Controll {

    Scanner sc = new Scanner(System.in);

    public void switch01() {
        System.out.println("정수 입력 : ");
        int num = sc.nextInt();
        int lastNum = num % 2;

        switch(lastNum) { // switch조건문 안에는 정수,문자,문자열 3가지 가능
            case 0 : even(); break;
            case 1 : odd(); break;
            default : System.out.println("잘못입력");
        }
    }
    private void even() {
        System.out.println("짝수입니다");
    }
    private void odd() {
        System.out.println("홀수입니다");
    }


    public void switch02() {

        System.out.println("월 입력 :");
        int month = sc.nextInt();

        switch(month) {
            case 1 : case 3 : case 5: case 7: case 8: case 10: case 12:
                System.out.println("입력한 달은 31일까지 입니다");
                break;
            case 2: System.out.println("입력한 달은 28일까지 입니다"); break;
            case 4:	case 6:	case 11:
                System.out.println("입력한 달은 30일까지 입니다");  break;
            default : System.out.println("잘못 입력");
        }


    }

    // 8부터 3까지 출력
    public void for_01() {
        for(int i=8; i>=3; i--) {
            System.out.println(i);
        }
    }

    // 1에서 10사이의 홀수만 출력 : 1 3 5 7 9
    public void for_02() {
        for(int i=1; i<=10; i+=2) {
            System.out.println(i);
        }
    }
    // 8부터 3까지 출력
    public void while_01() {
        int i = 8;
        while(i > 2) {
            System.out.println(i); // 8 7 6 5 4 3
            i -= 1;
        }
    }
    // 1에서 10사이의 홀수만 출력 : 1 3 5 7 9
    public void while_02() {
        int i = 1;
        while(i < 11) {
//			System.out.println(i);
//			i += 2;
            if(i % 2 != 0) {
                System.out.println(i); //  1 3 5 7 9
            }
            i += 1;
        }
    }

    // 1-9 사이 수를 입력 받아 구구단 출력
    public void while_03() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1-9 정수입력 : ");
        int num1 = sc.nextInt();

        if(num1 < 1 || num1 > 9 ) {
            System.out.println("1-9 정수입력만 입력가능");
        }else {
            for(int i=1; i < 10; i++) {
                System.out.println(num1+" x "+i+" = "+num1*i);
            }
        }
    }

    public void for_03() {

        int random = 0;
        for(int i=2; i<=9;i++) {
            for(int j=1; j<=9; j++) {
                System.out.println(i+" x " + j+ " = "+ i*j);
            }
            System.out.println();
        }
    }



    // 2단부터 9단까지 출력
    public void for_04() {
        int random = 1;
//		for(int i=2; i<=9;i++) {
//			random = (int)(Math.random() * 10 + 2);
//			for(int j=1; j<=9;j++) {
//				System.out.println(random+" x "+j+" = "+random*j);
//			}
//			System.out.println();
//		}
        random = (int)(Math.random() * 10);
        if(random == 1) { // 랜덤이 1일 경우에는 +1해줘서 1이 안나오고 2부터 나오도록 조건문 추가
            random += 1;
            for(int j=1; j<=9;j++) {
                System.out.println(random+" x "+j+" = "+random*j);
            }
            System.out.println();
        }else {
            for(int j=1; j<=9;j++) {
                System.out.println(random+" x "+j+" = "+random*j);
            }
            System.out.println();
        }

    }


    // 2부터 20까지의 임의의 난수를 뽑아 해당 숫자에 대한 구구단 출력
    public void for_05() {
        int random = 0;
        random = (int)(Math.random() * 20 + 2);
        if(random>1 && random <= 20) {
            for(int i=1;i<10;i++) {
                System.out.println(random+" x "+i+" = "+random*i);
            }
        }
    }

    // 최기현
    /*
     0 <= Math.random() < 1
     0 <= Math.random() * 19 < 19
     2 <= Math.random() * 19 + 2 < 21
     */
//	    int num = (int)(Math.random() * 19 + 2);
//
//	    System.out.println("==================" + num + "단 출력" + "==================");
//
//	    for(int i = 1; i <= 9; i++) {
//	       System.out.println(num + "x" + i + "=" + num * i);
//	    }
//	 }

    // 김인수
//	 int random =(int)(Math.random()*19 +2);
//     for(int i = 1; i <10; i++) {
//        System.out.println(random + " * " + i + " = " + random * i);
//     }

    // 10부터 5까지 출력
    public void for_06() {
        for(int i=10; i>=5; i--) {
            System.out.println(i);
        }
    }
    public void while_06() {
        int i = 10;
        while(i > 4) {
            System.out.println(i);
//				i -= 1;
            i--;
        }
    }

    // 1-10 사이 짝수만 출력
    public void for_07() {
        for(int i=1; i <= 10; i++) {
            if(i%2 == 0) {
                System.out.print(i+" ");
            }
        }
    }
    public void while_07() {
        int i = 2;
        while(i<=10) {
            System.out.print(i+" ");
            i+=2;
        }

    }




}
