package confidence;

import java.util.Arrays;

public class D_array {
	
	public void arrayPractice() {
		// �迭 ���� : Stack ������ ���� ����(������ ���� : stack�� ���� ����)
		// �迭 �Ҵ� : heap ������ ���� ����
		// �迭 �ʱ�ȭ : �ε���,�߰�ȣ,for��
		// �迭 ���� : null
		// �迭 �� ����ϴ� ��� 2���� : for��, Arrays.toString()
		// �迭 ���� : shallow copy & deep copy
		//
		// 		�迭 ũ�� ���� �Ұ�(�����ϰ� ���� ����⿡ �ּҰ��� �����)
		//		�迭 �� ���� �ִ���̺��� ª���� ����ŭ�� ��µǼ� ����(���а����� �ٸ���x)
		//		dArr[3] = 3;	// 3.0���� ��� -> �ڵ�����ȯ(int to double) 
		// 		�ʱ�ȭ for�� ����� int�� �����ϳ�, �Ǽ��� ����ȯ ������ int�� for�� ���� -> int�� i�� �Ǽ��� ��ȯ ������ ���̱⿡ 
		// 		null�� �迭�� Ȯ�� ��, for������� ������Ʈ�ͼ��� ���Ƿ� Arrays.toString() ����ؾ���
		
		
		//�迭 ����
		String[] arr; // Stack ������ ���� ����
		
		//�迭 �Ҵ�
		String[] sArr1 = new String[3];
		String sArr2[] = new String[3];
		
		// �迭 �ʱ�ȭ
		// ���1 : �ε���
		sArr1[0] = "a";
		sArr1[1] = "b";
		sArr1[2] = "c";
		
		// ���2 : �߰�ȣ {} �̿�
		// ����� ���ÿ� �ʱ�ȭ
//		sArr2 = {"a","b","c"}; // error : �߰�ȣ Ÿ���� �ڷ������� Ǯ�� �����ִ� ���¿��� ����
		String[] sArr3 = {"a","b","c"};
		
		// ���3 : for��
		// �迭�� intŸ���� ��츸 ��밡��
		// 		double�� ����ȯ ������ int�� for�� �����ұ�?  
		// 		����. int�� i�� �Ǽ��� ��ȯ ������ ���̱⿡
		int[] intArr = new int[5];
		for(int i=0; i < intArr.length;i++) {
			intArr[i] = i + 1;
		}
		for(int i=0; i < intArr.length; i++) {
			System.out.print(intArr[i]+" "); 	// 1 2 3 4 5
		}
		
		// �迭 ����
		intArr = null;
		System.out.println(Arrays.toString(intArr)); // null

		// �迭 �� ����ϴ� ���
		//  1)for��
		//  2)Arrays.toString()
		
		// 1)for��
//		int[] baseArr = {1,2,3,4,5};
//		for(int i=0; i<baseArr.length;i++) {
//			System.out.print(baseArr[i] + " "); // 1 2 3 4 5
//		}
		//  2)Arrays.toString()
//		System.out.println(Arrays.toString(baseArr)); // [1, 2, 3, 4, 10]

		
/******************************practice 2nd ************************************/
		
		// �迭 ����
		char[] cArr;
		
		// �迭 �Ҵ�
		char[] cArr1 = new char[3];
		char[] cArr2 = new char[3];
		
		// �迭 �ʱ�ȭ(�߰�)
		// ���1 : �ε���
		cArr1[0] = 'a';
		cArr1[1] = 'b';
		cArr1[2] = 'c';
//		cArr1[3] = 'd';
//		System.out.println(Arrays.toString(cArr1));
		// ���2 : �߰�ȣ
		char[] cArr11 = {'a','b','c'};
		char[] cArr22 = {'��','��','��'};
		char[] cArr3 = {'a','b','c'};
		// ���3 : for��
		// int�迭�� ��밡��
//		char[] cArr4 = new char[4];
//		for(int i=0; i < cArr4.length; i++) {
//			cArr4[i] = i + 1; // int�迭�� ��밡���ϱ⿡ error : Type mismatch: cannot convert from int to char
//			System.out.print(cArr4[i] + " ");
//		}
		
		int[] cArr4 = new int[4];
		for(int i=0; i < cArr4.length; i++) {
			cArr4[i] = i + 1;
			System.out.print(Arrays.toString(cArr4)+'\n'); // [1, 0, 0, 0][1, 2, 0, 0][1, 2, 3, 0][1, 2, 3, 4]
		}
		System.out.println(Arrays.toString(cArr4)); // [1, 2, 3, 4]
		
		// �迭 ����
		cArr4 = null; 
		System.out.println(Arrays.toString(cArr4));
		System.out.println(Arrays.toString(cArr3));
		cArr3 = null;
		System.out.println(Arrays.toString(cArr3));
		
		
System.out.println("===practice 3rd===");		


/******************************practice 3rd ************************************/
		// �迭 ����
		float[] fArr;
		float[] floatArr;	// Stack ������ ���� ����
	
		// �迭 �Ҵ�
		float[] fArr1 = new float[5];
		double[] dArr = new double[5];
		
		// �迭 �ʱ�ȭ
		// ��� 1 : �ε���
		dArr[0] = 0.1;
		dArr[1] = 0.2;
		dArr[2] = 3.0;
		dArr[3] = 3;	// 3.0���� ��� -> �ڵ�����ȯ
		System.out.println(Arrays.toString(dArr));
		
		// ���2 : �߰�ȣ
		double[] dArr2 = {0.1,0.2,0.3};
		System.out.println(Arrays.toString(dArr2)); // [0.1, 0.2, 0.3] -> �迭 �� ���� �ִ���̺��� ª���� ����ŭ�� ��µǼ� ����(���а����� �ٸ���x)
		// ���3 : for��
		double[] dArr3 = new double[3];
		// double�� ����ȯ ������ int�� for�� �����ұ�?  
		// ����. int�� i�� �Ǽ��� ��ȯ ������ ���̱⿡
		for(int i=0; i < dArr3.length; i++) {
			dArr3[i] = i + 1;
			System.out.print(dArr3[i]+" "); // 1.0 2.0 3.0 
		}
		System.out.println();
		
		
		dArr3 = null;
//		for(int i = 0; i < dArr3.length; i++) {
//			System.out.println(dArr3); // null�� �迭�� Ȯ�� ��, for������� ������Ʈ�ͼ��� ���Ƿ� Arrays.toString() ����ؾ���
//		}
		System.out.println(Arrays.toString(dArr3)); // null

		
System.out.println("===practice 4th===");		
		
		
/******************************practice 4th ************************************/		
		
		
		
		// �迭 ���� 
		// �迭 �Ҵ�
		// �迭 �ʱ�ȭ : �ε���,�߰�ȣ,for��
		// �迭 ���� : null
		// �迭 �� ����ϴ� ��� 2���� : for��, Arrays.toString()
		
		
		// �迭 ���� : Stack ������ ���� ����(������ ���� : stack�� ���� ����)
		byte[] bArr;
		
		// �迭 �Ҵ� : heap ������ ���� ����
		byte[] bArr1 = new byte[3];
		byte[] bArr2 = new byte[5];
		double[] dArr10 = new double[5];
		
		// �迭 �ʱ�ȭ : �ε���,�߰�ȣ,for��
		// ���1 : �ε���
		bArr1[0] = 1;
		bArr1[1] = 2;
		bArr1[2] = 3;
		bArr2[0] = 10; // bArr2�� 10ĭ �� ��ĭ�� ä�� �������� ��Եɱ�?
		dArr10[0] = 10;
		System.out.println(Arrays.toString(bArr2));
		System.out.println(Arrays.toString(dArr10));
		
		// ���2 : �߰�ȣ
		byte[] bArr3 = {1,2,3};
		// ���3 : for��
		for(int i=0; i<bArr1.length;i++) {
			System.out.print(bArr1[i]+ " "); // 1 2 3
		}
		System.out.println();
		
		
		// �迭 ���� : null1
		bArr2 = null;
		
		// �迭 �� ����ϴ� ��� 2���� : for��, Arrays.toString()
		// ���1 for��
		for(int i=0; i < bArr1.length; i++) {
			System.out.print(bArr1[i]+" ");
		}
		// ��� 2 Arrays.toString
		System.out.println(Arrays.toString(bArr2));
		System.out.println(bArr1.toString());
//		System.out.println(bArr2.toString());
		

System.out.println("===practice 5th===");		
		
/******************************practice 5th ************************************/		
		
		// �迭 ����
		int[] nArr;
		
		// �迭 �Ҵ�
		int[] nArr1 = new int[3];
		
		// �迭 �ʱ�ȭ
		// ���1 : �ε���
		nArr1[0] = 1;
		nArr1[1] = 2;
		
		// ���2 : �߰�ȣ
		int[] nArr3 = {1,2,3};
		
		// ���3 : for��
		for(int i=0; i < 3; i++) {
			
			nArr3[i] = i +1;
			System.out.print(nArr3[i]+" ");
			
		}
		
		nArr3 = null;
		System.out.println(Arrays.toString(nArr3));
		
		
	}

	
	
	
	
	public void shallowCopy() {

		int[] baseArr = {1,2,3,4,5};
		int[] copyArr = baseArr;

		System.out.println(baseArr); // [I@677327b6
		System.out.println(copyArr); // [I@677327b6
		
		// ���� �迭�� ���� �� �ּҰ� Ȯ���غ���
		baseArr[4] = 10;
		for(int i=0; i<baseArr.length;i++) {
			System.out.print(baseArr[i] + " "); // 1 2 3 4 10
		}
//		System.out.println();
//		System.out.println(baseArr); // [I@677327b6
//		System.out.println(copyArr); // [I@677327b6
		

		
		
		
	}
	
}
