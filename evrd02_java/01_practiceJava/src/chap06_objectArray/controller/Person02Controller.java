package chap06_objectArray.controller;

import java.util.Arrays;

import chap06_objectArray.model.vo.Person;
import chap06_objectArray.model.vo.Person02;

public class Person02Controller {
	
	public void objectArr00() {
		
		// 예제
		// 출처 :http://daplus.net/java-java%EB%A1%9C-%EA%B0%9D%EC%B2%B4-%EB%B0%B0%EC%97%B4-%EB%A7%8C%EB%93%A4%EA%B8%B0/
		
		
		// 1.선언 및 인스턴스화
		// 2.객체 초기화
		// 		방법1 : index
		// 		방법2 : for문
		// 3.방금 만든 객체에서 기존 메소드 호출 가능해짐
		// 			   arr[i].toString()
		// 		방법1 : arr[i].getter
		// int x = arr[1].getNumber();
		// 		방법2 : arr[i].setter
		// arr[1].setNumber(x);
		
		// 1.선언 및 인스턴스화
		A[ ] arr = new A[4];
		
		// 2.객체 초기화
		// 방법1 : index
		arr[0] = new A();
		arr[1] = new A();
		arr[2] = new A();
		arr[3] = new A();
		
		// 방법2 : for문
		for( int i=0; i<4; i++ ) {
			  arr[i] = new A();
		}
		// 3.방금 만든 객체에서 기존 메소드 호출 가능해짐
		// arr[i].toString()
		
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
		// 배열 길이 확인 : 변수명.length
		// 객체배열 값 출력하는 방법 2가지 : for문 + vo클래스 toString메소드
		//		객체배열에서는 배열과 달리 Arrays.toString() 사용불가
		//		이때, 변수.toString()해도 에러뜨는데 인덱스변호를 지정해줘야한다.
		//			ex) ojbArr[1].toString()
		
		
		
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
		
		
		
System.out.println("=====Object Array practice 2nd=====");		

	}
/******************************** Object Array practice 2nd ***************************************/	
	
	public void objectArr02() {
		
		// 객체배열
		//
		// 배열 선언 : Stack 영역에 공간 생성(변수의 선언 : stack에 공간 차지)
		// 배열 할당 : heap 영역에 공간 생성
		// 배열 초기화 : 인덱스,중괄호,for문
		// 배열 삭제 : null
		// 배열 값 출력하는 방법 2가지 : for문 + vo클래스 toString메소드
		
		// 배열 선언
		Person[] oArr;
		
		// 배열 선언 & 할당
		Person[] obArr = new Person[3];
		
		// 배열 초기화
		// 	방법1 : index
		obArr[0] = new Person("킴",20,'M',173,69);		// 중괄호가 아닌 소괄호
		obArr[1] = new Person("choi", 22, 'M', 165,45);
		obArr[2] = new Person("yu",32,'M',173.2,72.3);
		
		// 	방법2 : 중괄호
		Person02[] obArrr000 = {new Person02(),
								new Person02(),
								new Person02()
								};
		
		
		// 	방법3 : for문
		Person02[] obArrr = new Person02[5];
		for(int i=0; i < obArrr.length; i++) {
			obArrr[i] = new Person02("a",1,'M',1,1);
			System.out.println(obArrr[i].personInfo()); // 객체값 출력
		}
		// 이중포문으로 객체 안 숫자 증가시켜보기
		for(int i=0; i < obArrr.length;i+=2) {
			int j=1;
			for(; j < obArrr.length; j++) {
				obArrr[i] = new Person02("a",j,'M',j+10,j+20);
				System.out.println(obArrr[i].personInfo());
			}
		}
		
		// 객체 배열 : 출력
		// 
		for(int i=0; i < obArrr.length; i++) {
			obArrr[i] = new Person02();
			System.out.println(obArrr[i] + " ");		// 주소값 출력
			System.out.println(obArrr[i].personInfo()); // 객체값 출력
		}
		
		System.out.println("====null====");
		System.out.println(Arrays.toString(obArrr)); // [chap06_objectArray.model.vo.Person02@6d06d69c, chap06_objectArray.model.vo.Person02@7852e922, chap06_objectArray.model.vo.Person02@4e25154f, chap06_objectArray.model.vo.Person02@70dea4e, chap06_objectArray.model.vo.Person02@5c647e05]
		System.out.println(obArrr[0].personInfo());  // null, 0,  0.0, 0.0
		obArrr = null;
		System.out.println(Arrays.toString(obArrr)); // null
		
		Person02[] objArr001;
		Person02[] objArr002 = new Person02[5];
		objArr002[0] = new Person02("a", 1,'M',171,66.2);
		objArr002[1] = new Person02();
		objArr002[2] = new Person02();
//		objArr002[2] = new Person02(Person02.getName()); // Person02클래스의 게터에서 값을 끌어올 수 있을까 해서 해봄 
		// 스태틱 요구함 Cannot make a static reference to the non-static method getName() from the type Person02
		Person02[] objArr004 = {new Person02(),
								new Person02("철희", 32, 'M', 171,56),
								new Person02("영희", 32, 'W', 161,46)
								};

		Person02[] objArr003 = new Person02[5];
		for(int i=0; i < objArr003.length; i++) {
			objArr003[i] = new Person02(); 
			System.out.println(objArr003[i].personInfo());
		}
		

		
		
		
System.out.println("=====Object Array practice 3rd=====");	

/******************************** Object Array practice 3rd ***************************************/	

		//객체배열
		//
		// 배열 선언 : Stack 영역에 공간 생성(변수의 선언 : stack에 공간 차지)
		// 배열 할당 : heap 영역에 공간 생성
		// 배열 초기화 : 인덱스,중괄호,for문
		// 배열 삭제 : null
		// 배열 값 출력하는 방법 2가지 : for문 + vo클래스 toString메소드
		
		// 객체배열 선언
		Person02[] oArr000;
		// 객체배열 할당
		Person02[] oArr001 = new Person02[3];
		Person02[] oArr003 = new Person02[3];
		
		// 초기화
		// 	방법1 : index
		oArr001[0] = new Person02("박",25,'M',161,65);
		oArr001[1] = new Person02("강",22,'F',161,45);
		oArr001[2] = new Person02("최",22,'F',161,45);
		
		// 	방법2 : 중괄호
		// 객체배열 선언,할당과 동시에 초기화
		Person02[] oArr002 = {new Person02("김",29,'M',171,62),
				new Person02("이",28,'M',192,83),
				new Person02("최",25,'M',185,81)
		};
		
		//  방법3 : for문
		for(int i=0; i < oArr003.length; i++) {
			oArr003[i] = new Person02("a",i+20,'M',i+170,i+60);
			System.out.println(Arrays.toString(oArr003));
		}
		
//		System.out.println(Arrays.toString(oArr003[1])); // error : toString // The method toString(long[]) in the type Arrays is not applicable for the arguments (Person02)
		System.out.println(oArr003[1].personInfo());
		
		for(int i=0; i < oArr003.length;i++) {
			System.out.println(oArr003[i]); // chap06_objectArray.model.vo.Person02@33909752
		}
	
		oArr003 = null;
		System.out.println(Arrays.toString(oArr003)); // null

		System.out.println(oArr001[0].personInfo()); 
		
		for(int i=0; i < oArr001.length; i++) {
			System.out.println(oArr001[i].personInfo()); 
		}
		
		
		
System.out.println("=====Object Array practice 4th=====");	

/******************************** Object Array practice 4th ***************************************/	

		//객체배열
		//
		// 배열 선언 : Stack 영역에 공간 생성(변수의 선언 : stack에 공간 차지)
		// 배열 할당 : heap 영역에 공간 생성
		// 배열 초기화 : 인덱스,중괄호,for문
		// 배열 삭제 : null
		// 배열 값 출력하는 방법 2가지 : for문 + vo클래스 toString메소드
		
		// 객체배열 선언
		Person[] objArr010;

		// 객체배열 선언&할당
		Person[] objArr011 = new Person[2];
		Person[] objArr013 = new Person[3];
		
		// 객체배열 초기화
		// 		방법1 : 인덱스
		objArr011[0] = new Person("김기기",22,'M',171,66);
		objArr011[1] = new Person("최치치",22,'M',171,67);
				
		
		// 		방법2 : 중괄화
		Person objArr012[] = {new Person("박비비",23,'M',182,67),
							  new Person("최치치",22,'M',171,67),
							  new Person("김기기",22,'M',171,66)
							};
		// 		방법3 : for문
		for(int i=0; i < objArr013.length; i++) {
			objArr013[i] = new Person("김기기",22,'M',171,66);
			System.out.println("for문 초기화 = "+objArr013[i].personInfo());
		}
			
			
			
		// 객체배열  삭제
		objArr013 = null;
		
		// 객체 배열 내 값 출력
		// 방법1 : for문
		// 방법2 : Arrays.toString
		System.out.println(Arrays.toString(objArr013));
		// 인덱스+toString(vo class)
		
		System.out.println(objArr012[0].personInfo());
	
		
		
		
System.out.println("=====Object Array practice 5th=====");	

/******************************** Object Array practice 5th ***************************************/	

// 객체배열
//
// 배열 선언 : Stack 영역에 공간 생성(변수의 선언 : stack에 공간 차지)
// 배열 할당 : heap 영역에 공간 생성
// 배열 초기화 : 인덱스,중괄호,for문
// 배열 삭제 : null
// 배열 값 출력하는 방법 2가지 : for문 + vo클래스 toString메소드

	
	// 배열 선언
	Person02[] pArr01;
	

	// 배열 할당
	Person02[] pArr02 = new Person02[2];
	Person02 pArr03[] = new Person02[2];


	// 배열 초기화
	//	방법1 : 인덱스
	pArr02[0] = new Person02("a",22,'M',171,66);
	pArr02[1] = new Person02("b",23,'M',185,69);
	
	// 	방법2 : 중괄호
	// 선언과 동시에 할당
	Person02[] pArr04 = {new Person02("김",25,'M',177,66),
						 new Person02("이",53,'M',177,66),
						 new Person02("박",32,'M',188,86)
						};	
	
	// 	방법3 : for문
	for(int i=0; i < pArr03.length; i++) {
		pArr03[i] = new Person02("for",20+i,'M',170+i,70+i);
		System.out.println(pArr03[i]); // chap06_objectArray.model.vo.Person02@42a57993, @75b84c92
//		System.out.println(Arrays.toString(pArr03[i])); // Arrays.toString()은 객체배열에 사용불가
		System.out.println(pArr03[i].personInfo()); // for, 20, M, 170.0, 70.0 //  for, 21, M, 171.0, 71.0
	}	// 객체배열 값 추출은 vo class에서 toString() 역할하는 메소드 사용해야함

	
	
	// 배열 삭제
	// 배열 출력
	pArr03 = null;
	System.out.println(pArr03); // null
//	System.out.println(pArr02.personInfo());    // 변수 전체를 찍으면 에러발생
	System.out.println(pArr02[0].personInfo()); // 인덱스 번호 지정해서 찍어야함
	
	

	
	








System.out.println("=====Object Array practice 6th=====");	

/******************************** Object Array practice 6th ***************************************/	


// 객체배열
//
// 배열 선언 : Stack 영역에 공간 생성(변수의 선언 : stack에 공간 차지)
// 배열 할당 : heap 영역에 공간 생성
// 배열 초기화 : 인덱스,중괄호,for문
// 배열 삭제 : null
// 배열 값 출력하는 방법 2가지 : for문 + vo클래스 toString메소드




// array, Object Array, Collection 모두 다
// 블로그 1일1코딩 깨달은거 적은거랑 연습코드에 주석 달은거 내용 한곳에 모아서 데이터 축적하는거 정리하기!!



	}
	
	
	
}