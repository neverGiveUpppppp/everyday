package chap05_OOP.A_encapsulation;

public class Encapsulation03 {

	
	String name = "�Ҽ��ִ�";
	int bal = 0; 
	
	//�ӱݸ޼ҵ�
	public void deposit(int money) {
		
		if(money>0) {
			bal += money;
			System.out.println(name+"���� ���¿� "+money+"���� �ԱݵǾ����ϴ�");
		}else {
			System.out.println("�ݾ� �߸� �Է�");
		}
		
	}
	
	// ��ݸ޼ҵ�
	public void withdraw(int money) {
		
		if(money <= bal) {
			bal -= money;
			System.out.println(name+"���ǰ��¿��� "+money+"���� ��ݵǾ����ϴ�");
		}
		
	}
	
	// �ܾ���ȸ
	public void check() {
		System.out.println(bal+"���� �����ֽ��ϴ�");
		
	}
	
	
	
}
