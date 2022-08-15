package chap05_OOP.A_encapsulation;

public class Run {

	public static void main(String[] args) {

		
		Encapsulation01 encap = new Encapsulation01();
		encap.deposit(100);
		encap.withdraw(50);
		encap.withdraw(150);
		encap.checkBalance();
	}

}
