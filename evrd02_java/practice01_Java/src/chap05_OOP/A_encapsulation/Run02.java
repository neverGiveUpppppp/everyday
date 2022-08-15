package chap05_OOP.A_encapsulation;

public class Run02 {

	public static void main(String[] args) {

		Encapsulation02 encap = new Encapsulation02();
		encap.deposit(10000);
		encap.withdraw(10000);
		encap.withdraw(10000);
		encap.checkBalance();
	}

}
