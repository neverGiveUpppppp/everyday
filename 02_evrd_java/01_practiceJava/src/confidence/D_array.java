package confidence;

import java.util.Arrays;

public class D_array {

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
		
		
		
	}
	
}
