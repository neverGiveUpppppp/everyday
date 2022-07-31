package chap05_OOP.A_encapsulation;

public class Encapsulation02 {

	
	String ClientName = "최영수";
	int balance = 0;
	
	
	
	// 입금 기능
	public void deposit(int transfer) {
		if(transfer > 0) {
			balance += transfer;
			System.out.println(ClientName+"님의 계좌에 "+transfer+"원이 입금되었습니다.");
		}else {
			System.out.println("입금 금액을 잘못 입력하셨습니다.");
		}
	}

	
	// 출금 기능
	public void withdraw(int transfer) {
		if(transfer <= balance) {
			balance -= transfer;
			System.out.println(ClientName+"님의 계좌에서 "+transfer+"원이 출금 됩니다.");
		}else {
			System.out.println("잔액이 부족합니다");
		}
	}

	
	// 잔액 조회 기능
	public void checkBalance() {
		System.out.println(balance+"원이 남아있습니다");
	}
	
	

}
