package confidence;

import java.util.Scanner;

public class B_operator {

	Scanner sc = new Scanner(System.in);
	
	public void switch01() {
		
		System.out.println("1-12 입력하시오 : ");
		int quaters = sc.nextInt();
		
		switch(quaters) {
		case 1 : case 2 : case 3:
			System.out.println("1분기"); break;
		case 4: case 5: case 6:
			System.out.println("2분기"); break;
		case 7: case 8: case 9:
			System.out.println("3분기"); break;
		case 10: case 11 : case 12 :
			System.out.println("4분기"); break;
		default: System.out.println("잘못 입력 하셨습니다");
		}
		
	
	}
	
	
	
	
	
}
