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
	
	
	
	
	
}
