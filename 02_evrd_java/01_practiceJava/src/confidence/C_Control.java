package confidence;

import java.util.Scanner;

public class C_Control {

	
	public void for_01() {
		for(int i=8; i>=3; i--) {
			System.out.println(i);
		}
	}
	
	public void for_02() {
		for(int i=1; i<=10; i+=2) {
			System.out.println(i);
		}
	}
	
	public void while_01() {
		int i = 8;
		while(i > 2) {
			System.out.println(i);
			i -= 1;
		}
	}
	public void while_02() {
		int i = 1;
		while(i < 11) {
//			System.out.println(i);
//			i += 2;
			if(i % 2 != 0) {
				System.out.println(i);
			}
			i += 1;
		}
	}
	
	public void while_03() {
		// 1-9 사이 수를 입력 받아 구구단 출력
		Scanner sc = new Scanner(System.in);
		System.out.println("1-9 정수입력 : ");
		int num1 = sc.nextInt();
		
		if(num1 < 1 || num1 > 9 ) {
			System.out.println("1-9 정수입력만 입력가능");
		}else {
			for(int i=1; i < 10; i++) {
				System.out.println(num1+" x "+i+" = "+num1*i);
//				System.out.printf(num1+);
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

	
}
