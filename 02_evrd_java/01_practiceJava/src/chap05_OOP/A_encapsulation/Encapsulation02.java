package chap05_OOP.A_encapsulation;

public class Encapsulation02 {

	
	String ClientName = "�ֿ���";
	int balance = 0;
	
	
	
	// �Ա� ���
	public void deposit(int transfer) {
		if(transfer > 0) {
			balance += transfer;
			System.out.println(ClientName+"���� ���¿� "+transfer+"���� �ԱݵǾ����ϴ�.");
		}else {
			System.out.println("�Ա� �ݾ��� �߸� �Է��ϼ̽��ϴ�.");
		}
	}

	
	// ��� ���
	public void withdraw(int transfer) {
		if(transfer <= balance) {
			balance -= transfer;
			System.out.println(ClientName+"���� ���¿��� "+transfer+"���� ��� �˴ϴ�.");
		}else {
			System.out.println("�ܾ��� �����մϴ�");
		}
	}

	
	// �ܾ� ��ȸ ���
	public void checkBalance() {
		System.out.println(balance+"���� �����ֽ��ϴ�");
	}
	
	

}
