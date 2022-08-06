package chap06_objectArray.controller;

import java.util.Arrays;

import chap06_objectArray.model.vo.Person;

public class Person02Controller {
	
	public void objectArr00() {
		
		// 예제
		// 출처 :http://daplus.net/java-java%EB%A1%9C-%EA%B0%9D%EC%B2%B4-%EB%B0%B0%EC%97%B4-%EB%A7%8C%EB%93%A4%EA%B8%B0/
		
		// 1.선언 및 인스턴스화
		A[ ] arr = new A[4];
		
		// 2.객체 초기화
		// 방법1
		arr[0] = new A();
		arr[1] = new A();
		arr[2] = new A();
		arr[3] = new A();
		
		// 방법2
		for( int i=0; i<4; i++ ) {
			  arr[i] = new A();
		}
		// 방금 만든 객체에서 기존 메소드 호출 가능해짐
		// 방법1
		int x = arr[1].getNumber();
		// 방법2
		arr[1].setNumber(x);
		
	}
	
	public void objectArr01() {
		
		// 객체배열
		//
		// 배열 선언 : Stack 영역에 공간 생성(변수의 선언 : stack에 공간 차지)
		// 배열 할당 : heap 영역에 공간 생성
		// 배열 초기화 : 인덱스,중괄호,for문
		// 배열 삭제 : null
		// 배열 값 출력하는 방법 2가지 : for문 + vo클래스 toString메소드
		
		
		
		
		// 배열 선언 - stack 영역에 생성
		String objArr;
		
		// 배열 할당 - heap 영역에 생성
		Person[] objArr01 = new Person[3];
		
		// 초기화
		//	1)인덱스
		objArr01[0] = new Person();
		objArr01[1] = new Person("김철수", 20, 'M', 170.5, 70);
		objArr01[2] = new Person("이순신", 40,'M', 180.5, 85.5); 

// vo Person		
//		private String name;
//		private int age;
//		private char gender;
//		private double height;
//		private double weight;
		
		// 	2) 중괄호
		Person objArr02 = new Person("안중근", 30,'M', 175.5, 75.3);
		Person[] objArr03 = {new Person("유관순", 18,'F', 160.2, 45.5),
							 new Person("신사임당", 40,'F', 157.5, 43.2),
							 new Person("세종대왕", 55, 'M', 180.5, 80.5)
							};
		System.out.println(objArr02); // chap06_objectArray.model.vo.Person@6d06d69c
		System.out.println(Arrays.toString(objArr03)); // 객체주소값 출력 : [chap06_objectArray.model.vo.Person@6d06d69c, chap06_objectArray.model.vo.Person@7852e922, chap06_objectArray.model.vo.Person@4e25154f]

//		System.out.println(Arrays.toString(objArr02[0])); // The type of the expression must be an array type but it resolved to Person
//		for(int i=0; i < objArr02.length; i++) { // 
//			System.out.println(objArr02[i]);	// The type of the expression must be an array type but it resolved to Person
//		}										// for문 출력은 int나 int로 자동형변환되는 소수들이 가능
		
		
		// 	3) for문
		Person[] objArr04 = new Person[3];
		for(int i=0; i < objArr04.length; i++) {
			objArr04[i] = new Person();
			System.out.println("==="+objArr04[i].personInfo()); 
//			 null, 0,  , 0.0, 0.0
//			 null, 0,  , 0.0, 0.0  -> vo클래스있는 Person 필드값의 데이터타입의 기본형들로 자바가 채움
//			 null, 0,  , 0.0, 0.0
		}
		
		
		// 객체 주소값 출력
		for(int i=0; i < objArr03.length; i++) {
			System.out.println("objArr03 = "+objArr03[i]);
//				objArr03 = chap06_objectArray.model.vo.Person@7852e922
//				objArr03 = chap06_objectArray.model.vo.Person@4e25154f
//				objArr03 = chap06_objectArray.model.vo.Person@70dea4e
		}
		// vo클래스 toString()을 이용한 객체속성값 출력
		for(int i=0; i < objArr03.length; i++) {
			System.out.println(objArr03[i].personInfo());
//				유관순, 18, F, 160.2, 45.5
//				신사임당, 40, F, 157.5, 43.2
//				세종대왕, 55, M, 180.5, 80.5
		}
//		System.out.println(Arrays.toString(objArr03.personInfo())); // Cannot invoke personInfo() on the array type Person[]
//		System.out.println(Arrays.toString(objArr03[0].personInfo())); // The method toString(long[]) in the type Arrays is not applicable for the arguments (String)
//		System.out.println(Arrays.personInfo()); // toString빼고 바로 출력할려했으나 에러 : The method personInfo() is undefined for the type Arrays
		
		// 삭제
		objArr03 = null;
		System.out.println(objArr03); // null
		System.out.println(Arrays.toString(objArr03)); // null
		for(int i=0; i < objArr03.length; i++) {
			System.out.println(objArr03[i].personInfo()); // Exception in thread "main" java.lang.NullPointerException
		}
		
		
		
System.out.println("Object Array practice 2nd");		

	}
/******************************** Object Array practice 2nd ***************************************/	
	
	public void objectArr02() {
		
	}
	
	
	
}