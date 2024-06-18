package com.prac.array.controller;

import java.util.Arrays;

public class ArrayController {

    public void arrayPractice() {
        // 배열 선언 : Stack 영역에 공간 생성(변수의 선언 : stack에 공간 차지)
        // 배열 할당 : heap 영역에 공간 생성
        // 배열 초기화 : 인덱스,중괄호,for문
        // 배열 삭제 : null
        // 배열 값 출력하는 방법 2가지 : for문, Arrays.toString()
        // 배열 길이 확인 : 변수명.length
        // 배열 복사 : shallow copy & deep copy
        //
        // 		배열 크기 변경 불가(삭제하고 새로 만들기에 주소값이 변경됨)
        //		배열 안 값이 최대길이보다 짧으면 값만큼만 출력되서 나옴(여분공간에 다른값x)
        //		dArr[3] = 3;	// 3.0으로 출력 -> 자동형변환(int to double)
        // 		초기화 for문 방법은 int만 가능하나, 실수로 형변환 가능한 int면 for문 가능 -> int인 i가 실수로 변환 가능한 수이기에
        // 		null인 배열값 확인 시, for문방법은 널포인트익셉션 쓰므로 Arrays.toString() 사용해야함
        //		출력 방법1 for문 : 대괄호 없이 일반으로 출력됨
        //		출력 방법2 Arrays.toString()은 배열 형태로 반환 : []



        //배열 선언
        String[] arr; // Stack 영역에 공간 생성

        //배열 할당
        String[] sArr1 = new String[3];
        String sArr2[] = new String[3];

        // 배열 초기화
        // 방법1 : 인덱스
        sArr1[0] = "a";
        sArr1[1] = "b";
        sArr1[2] = "c";

//        // 방법2 : 중괄호 {} 이용
//        // 선언과 동시에 초기화
////		sArr2 = {"a","b","c"}; // error : 중괄호 타입은 자료형부터 풀로 적어주는 형태에만 가능
        String[] sArr3 = {"a","b","c"};

        // 방법3 : for문
        // 배열이 int타입인 경우만 사용가능
        // 		double로 형변환 가능한 int면 for문 가능할까?
        // 		가능. int인 i가 실수로 변환 가능한 수이기에
        int[] intArr = new int[5];
        for(int i=0; i < intArr.length;i++) {
            intArr[i] = i + 1;
        }
        for(int i=0; i < intArr.length; i++) {
            System.out.print(intArr[i]+" "); 	// 1 2 3 4 5
        }

        // 배열 삭제
        intArr = null;
        System.out.println(Arrays.toString(intArr)); // null

        // 배열 값 출력하는 방법
        //  1)for문
        //  2)Arrays.toString()

        // 1)for문
//		int[] baseArr = {1,2,3,4,5};
//		for(int i=0; i<baseArr.length;i++) {
//			System.out.print(baseArr[i] + " "); // 1 2 3 4 5
//		}
        //  2)Arrays.toString()
//		System.out.println(Arrays.toString(baseArr)); // [1, 2, 3, 4, 10]


/******************************practice 2nd ************************************/

        // 배열 선언
        char[] cArr;

        // 배열 할당
        char[] cArr1 = new char[3];
        char[] cArr2 = new char[3];

        // 배열 초기화(추가)
        // 방법1 : 인덱스
        cArr1[0] = 'a';
        cArr1[1] = 'b';
        cArr1[2] = 'c';
//		cArr1[3] = 'd';
//		System.out.println(Arrays.toString(cArr1));
        // 방법2 : 중괄호
        char[] cArr11 = {'a','b','c'};
        char[] cArr22 = {'ㄱ','ㄴ','다'};
        char[] cArr3 = {'a','b','c'};
        // 방법3 : for문
        // int배열만 사용가능
//		char[] cArr4 = new char[4];
//		for(int i=0; i < cArr4.length; i++) {
//			cArr4[i] = i + 1; // int배열만 사용가능하기에 error : Type mismatch: cannot convert from int to char
//			System.out.print(cArr4[i] + " ");
//		}

        int[] cArr4 = new int[4];
        for(int i=0; i < cArr4.length; i++) {
            cArr4[i] = i + 1;
            System.out.print(Arrays.toString(cArr4)+'\n'); // [1, 0, 0, 0][1, 2, 0, 0][1, 2, 3, 0][1, 2, 3, 4]
        }
        System.out.println(Arrays.toString(cArr4)); // [1, 2, 3, 4]

        // 배열 삭제
        cArr4 = null;
        System.out.println(Arrays.toString(cArr4));
        System.out.println(Arrays.toString(cArr3));
        cArr3 = null;
        System.out.println(Arrays.toString(cArr3));


        System.out.println("===practice 3rd===");


/******************************practice 3rd ************************************/
        // 배열 선언
        float[] fArr;
        float[] floatArr;	// Stack 영역에 공간 생성

        // 배열 할당
        float[] fArr1 = new float[5];
        double[] dArr = new double[5];

        // 배열 초기화
        // 방법 1 : 인덱스
        dArr[0] = 0.1;
        dArr[1] = 0.2;
        dArr[2] = 3.0;
        dArr[3] = 3;	// 3.0으로 출력 -> 자동형변환
        System.out.println(Arrays.toString(dArr));

        // 방법2 : 중괄호
        double[] dArr2 = {0.1,0.2,0.3};
        System.out.println(Arrays.toString(dArr2)); // [0.1, 0.2, 0.3] -> 배열 안 값이 최대길이보다 짧으면 값만큼만 출력되서 나옴(여분공간에 다른값x)
        // 방법3 : for문
        double[] dArr3 = new double[3];
        // double로 형변환 가능한 int면 for문 가능할까?
        // 가능. int인 i가 실수로 변환 가능한 수이기에
        for(int i=0; i < dArr3.length; i++) {
            dArr3[i] = i + 1;
            System.out.print(dArr3[i]+" "); // 1.0 2.0 3.0
        }
        System.out.println();


        dArr3 = null;
//		for(int i = 0; i < dArr3.length; i++) {
//			System.out.println(dArr3); // null인 배열값 확인 시, for문방법은 널포인트익셉션 쓰므로 Arrays.toString() 사용해야함
//		}
        System.out.println(Arrays.toString(dArr3)); // null


        System.out.println("===practice 4th===");


/******************************practice 4th ************************************/



        // 배열 선언
        // 배열 할당
        // 배열 초기화 : 인덱스,중괄호,for문
        // 배열 삭제 : null
        // 배열 값 출력하는 방법 2가지 : for문, Arrays.toString()


        // 배열 선언 : Stack 영역에 공간 생성(변수의 선언 : stack에 공간 차지)
        byte[] bArr;

        // 배열 할당 : heap 영역에 공간 생성
        byte[] bArr1 = new byte[3];
        byte[] bArr2 = new byte[5];
        double[] dArr10 = new double[5];

        // 배열 초기화 : 인덱스,중괄호,for문
        // 방법1 : 인덱스
        bArr1[0] = 1;
        bArr1[1] = 2;
        bArr1[2] = 3;
        bArr2[0] = 10; // bArr2는 10칸 중 한칸만 채움 나머지는 어떻게될까?
        dArr10[0] = 10;
        System.out.println(Arrays.toString(bArr2));
        System.out.println(Arrays.toString(dArr10));

        // 방법2 : 중괄호
        byte[] bArr3 = {1,2,3};
        // 방법3 : for문
        for(int i=0; i<bArr1.length;i++) {
            System.out.print(bArr1[i]+ " "); // 1 2 3
        }
        System.out.println();


        // 배열 삭제 : null1
        bArr2 = null;

        // 배열 값 출력하는 방법 2가지 : for문, Arrays.toString()
        // 방법1 for문
        for(int i=0; i < bArr1.length; i++) {
            System.out.print(bArr1[i]+" ");
        }
        // 방법 2 Arrays.toString
        System.out.println(Arrays.toString(bArr2));
        System.out.println(bArr1.toString());
//		System.out.println(bArr2.toString());


        System.out.println("===practice 5th===");

/******************************practice 5th ************************************/

        // 배열 선언
        int[] nArr;

        // 배열 할당
        int[] nArr1 = new int[3];

        // 배열 초기화
        // 방법1 : 인덱스
        nArr1[0] = 1;
        nArr1[1] = 2;

        // 방법2 : 중괄호
        int[] nArr3 = {1,2,3};

        // 방법3 : for문
        for(int i=0; i < 3; i++) {

            nArr3[i] = i +1;
            System.out.print(nArr3[i]+" ");

        }

        nArr3 = null;
        System.out.println(Arrays.toString(nArr3));


        System.out.println("===practice 6th===");

/******************************practice 6th ************************************/

        // 배열 선언 : Stack 영역에 공간 생성(변수의 선언 : stack에 공간 차지)
        // 배열 할당 : heap 영역에 공간 생성
        // 배열 초기화 : 인덱스,중괄호,for문
        // 배열 삭제 : null
        // 배열 값 출력하는 방법 2가지 : for문, Arrays.toString()

        //배열 선언
        float[] fArr00;

        // 배열 할당
        float[] fArr10 = new float[3];
        float fArr20[] = new float[3];

        // 배열 초기화
        // 1)인덱스
        fArr10[0] = 1;
//		fArr10[1] = 2.0; // cannot convert double to float -> 해결 : 접미사 f 붙여야함 또는 강제형변환
        fArr10[1] = (float)2.0; // 강제형변환
        fArr10[1] = 2.0f;		// 접미사 f 추가
        fArr10[2] = 3;


        // 2)중괄호
        // 선언과 동시에 초기화
        float[] fArr30 = {1.0f,2.0f,3};

        // 3)for문
        float[] fArr40 = new float[5];
        for(int i=0; i < fArr40.length; i++) {
            fArr40[i] = i + 1;
            System.out.print(fArr40[i] + ' ');	// 33.034.035.036.037.0
            // 싱글 쿼테이션으로 하면 33.0 34.0 이런식으로 나오는데 아스키코드 영향인가?
            // 아스키코드표 찾아보니까 33 34쪽에 SOH STX이런게 있는데 뭔지 모르니 일단 스킵
        }
        System.out.println();
        System.out.println(Arrays.toString(fArr40)); // [1.0, 2.0, 3.0, 4.0, 5.0]


        // 배열 삭제
        System.out.println(Arrays.toString(fArr10)); // [1.0, 2.0, 3.0]
        fArr10 = null;
        System.out.println(Arrays.toString(fArr10)); // null



        System.out.println("===practice 7th===");

/******************************practice 7th ************************************/


// 배열 선언 : Stack 영역에 공간 생성(변수의 선언 : stack에 공간 차지)
// 배열 할당 : heap 영역에 공간 생성
// 배열 초기화 : 인덱스,중괄호,for문
// 배열 삭제 : null
// 배열 값 출력하는 방법 2가지 : for문, Arrays.toString()

//
// 		배열 크기 변경 불가(삭제하고 새로 만들기에 주소값이 변경됨)
//		배열 안 값이 최대길이보다 짧으면 값만큼만 출력되서 나옴(여분공간에 다른값x)
//		dArr[3] = 3;	// 3.0으로 출력 -> 자동형변환(int to double)
// 		초기화 for문 방법은 int만 가능하나, 실수로 형변환 가능한 int면 for문 가능 -> int인 i가 실수로 변환 가능한 수이기에
// 		null인 배열값 확인 시, for문방법은 널포인트익셉션 쓰므로 Arrays.toString() 사용해야함





        // 배열 선언
        String[] arr020;

        // 배열 선언 & 할당
        String arr021[] = new String[2];
        String[] arr022 = new String[3];

        // 배열 초기화
        // 	방법1 : 인덱스
        arr021[0] = "1";
        arr021[1] = "둘";
//		arr021[2] = "셋"; // 길이 초과  java.lang.ArrayIndexOutOfBoundsException: 2
        System.out.println(Arrays.toString(arr021));	// [1, 둘]

        // 	방법2 : 중괄호
        // 선언과 동시에 할당
        String[] arr023 = {"a","b"};
        System.out.println(Arrays.toString(arr023));	// [a, b]


        // 	방법3 : for문
        long[] arr025 = {1,2,3};
        for(int i=0; i < arr025.length; i++) {
            arr025[i] += 1;
            System.out.println(arr025[i]); // 2,3,4
//			System.out.println(Arrays.toString(arr025[i])); //long타입은 Arrays.toString이 안됨
        }



        // 배열 삭제
        arr025 = null;
        System.out.println(Arrays.toString(arr025)); // null

        // 배열 출력
        // 	방법1 : for문
        // 	방법2 : Arrays.toString()






        System.out.println("===practice 8th===");

/******************************practice 8th ************************************/
        // 다음 연습 내용 char나 byte, long
        // long타입은 Arrays.toString()에 에러나면서 작동 안하는 것 발견


//배열 선언 : Stack 영역에 공간 생성(변수의 선언 : stack에 공간 차지)
//배열 할당 : heap 영역에 공간 생성
//배열 초기화 : 인덱스,중괄호,for문
//배열 삭제 : null
//배열 값 출력하는 방법 2가지 : for문, Arrays.toString()

//
//		배열 크기 변경 불가(삭제하고 새로 만들기에 주소값이 변경됨)
//		배열 안 값이 최대길이보다 짧으면 값만큼만 출력되서 나옴(여분공간에 다른값x)
//		dArr[3] = 3;	// 3.0으로 출력 -> 자동형변환(int to double)
//		초기화 for문 방법은 int만 가능하나, 실수로 형변환 가능한 int면 for문 가능 -> int인 i가 실수로 변환 가능한 수이기에
//		null인 배열값 확인 시, for문방법은 널포인트익셉션 쓰므로 Arrays.toString() 사용해야함




        // 배열 선언 : Stack 영역에 공간 생성(변수의 선언 : stack에 공간 차지)
        double[] dNum;
        float fNum[];

        // 배열 할당 : heap 영역에 공간 생성
        double[] dNum2 = new double[2];
        float fNum2[] = new float[3];


        // 배열 초기화 : 인덱스,중괄호,for문
        // 방법1 : 인덱스
        fNum2[0] = 1;
        fNum2[1] = 1.5f;
        fNum2[2] = (float)1.8; // 1.8 == 1.8d // 강제형변환
        System.out.println(fNum2);	// [F@14ae5a5
        System.out.println(Arrays.toString(fNum2)); // [1.0, 1.5, 1.8]


        // 방법2 : 중괄호
        // 선언과 동기에 초기화
        String[] sArr01 = {"a","b","c"};
        char[] cArr01 = {'a','b','c'};

        // 방법3 : for문
        for(int i=0; i < dNum2.length; i++) {
            dNum2[i] += i;
            System.out.println(dNum2[i]); // 0.0 1.0
        }

        // 배열 삭제 : null
        dNum2 = null;
        System.out.println(dNum2);					// null
        System.out.println(Arrays.toString(dNum2));	// null

        // 배열 값 출력하는 방법 2가지 : for문, Arrays.toString()
        // 방법1 for문
        // 방법2 Arrays.toString
        for(int i=0; i < fNum2.length; i++) {
            System.out.print(fNum2[i]+" "); // 1.0 1.5 1.8
        }
        System.out.println(Arrays.toString(fNum2)); // [1.0, 1.5, 1.8]




    }



    // 배열 선언 : Stack 영역에 공간 생성(변수의 선언 : stack에 공간 차지)

    // 배열 할당 : heap 영역에 공간 생성

    // 배열 초기화 : 인덱스,중괄호,for문
    // 방법1 : 인덱스
    // 방법2 : 중괄호
    // 방법3 : for문

    // 배열 삭제 : null

    // 배열 값 출력하는 방법 2가지 : for문, Arrays.toString()
    // 방법1 for문
    // 방법2 Arrays.toString




    /********************************************************************************/


    public void shallowCopy() {

        int[] baseArr = {1,2,3,4,5};
        int[] copyArr = baseArr;

        System.out.println(baseArr); // [I@677327b6
        System.out.println(copyArr); // [I@677327b6

        // 원본 배열값 변경 후 주소값 확인해보기
        baseArr[4] = 10;
        for(int i=0; i<baseArr.length;i++) {
            System.out.print(baseArr[i] + " "); // 1 2 3 4 10
        }
//		System.out.println();
//		System.out.println(baseArr); // [I@677327b6
//		System.out.println(copyArr); // [I@677327b6





    }

}
