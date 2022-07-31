package chap05_OOP.A_encapsulation;

public class Encapsulation01 {

	
	private String ClientName = "김철수";
	private String phone = "010-1234-5678";
	private String pwd = "abcd";
	private int bankCode = 1500;
	private int balance = 0;
	
	
	// 입금 메소드
	public void deposit(int money) {
		
		if(money > 0) {
			balance += money;
			System.out.println(ClientName+"님의 계좌에서 "+money+"원이 입금되었습니다");
		}else {
			System.out.println("금액을 잘못 입력하셨습니다");
		}
	}
	
	// 출금 메소드
	public void withdraw(int money) {
		if(money <= balance) {
			balance -= money;
			System.out.println(ClientName+"님의 계좌에서 "+money+"원이 출금되었습니다");
		}else {
			System.out.println("잔액보다 초과된 금액을 입력하셨습니다.");
		}
		
	}
	
	// 잔액 조회 메소드
	public void checkBalance() {
		System.out.println(balance+"원이 남아있습니다.");
	}
	
}
