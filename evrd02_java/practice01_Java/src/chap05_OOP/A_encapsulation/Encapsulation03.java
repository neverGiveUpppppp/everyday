package chap05_OOP.A_encapsulation;

public class Encapsulation03 {

	
	String name = "할수있다";
	int bal = 0; 
	
	//임금메소드
	public void deposit(int money) {
		
		if(money>0) {
			bal += money;
			System.out.println(name+"님의 계좌에 "+money+"원이 입금되었습니다");
		}else {
			System.out.println("금액 잘못 입력");
		}
		
	}
	
	// 출금메소드
	public void withdraw(int money) {
		
		if(money <= bal) {
			bal -= money;
			System.out.println(name+"님의계좌에서 "+money+"원이 출금되었습니다");
		}
		
	}
	
	// 잔액조회
	public void check() {
		System.out.println(bal+"원이 남아있습니다");
		
	}
	
}	
	