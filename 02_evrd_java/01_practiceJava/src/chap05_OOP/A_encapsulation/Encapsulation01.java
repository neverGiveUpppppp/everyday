package chap05_OOP.A_encapsulation;

public class Encapsulation01 {

	
	private String ClientName = "��ö��";
	private String phone = "010-1234-5678";
	private String pwd = "abcd";
	private int bankCode = 1500;
	private int balance = 0;
	
	
	// �Ա� �޼ҵ�
	public void deposit(int money) {
		
		if(money > 0) {
			balance += money;
			System.out.println(ClientName+"���� ���¿��� "+money+"���� �ԱݵǾ����ϴ�");
		}else {
			System.out.println("�ݾ��� �߸� �Է��ϼ̽��ϴ�");
		}
	}
	
	// ��� �޼ҵ�
	public void withdraw(int money) {
		if(money <= balance) {
			balance -= money;
			System.out.println(ClientName+"���� ���¿��� "+money+"���� ��ݵǾ����ϴ�");
		}else {
			System.out.println("�ܾ׺��� �ʰ��� �ݾ��� �Է��ϼ̽��ϴ�.");
		}
		
	}
	
	// �ܾ� ��ȸ �޼ҵ�
	public void checkBalance() {
		System.out.println(balance+"���� �����ֽ��ϴ�.");
	}
	
}
