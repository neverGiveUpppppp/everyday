package chap12_Collection.A_List.controller;

import java.util.ArrayList;
import chap12_Collection.A_List.model.vo.Student;


public class ListController {
	
	public void method01() {

		ArrayList<Student> list = new ArrayList<Student>(3);
		
		// add(E e):boolean
		list.add(new Student("김철수",0));
		list.add(new Student("최철수",100));
		list.add(new Student("박철수",70));
		
		System.out.println("list = "+list); // list = [김철수(0점), 최철수(100점), 박철수(70점)]
		// ArrayList는 ___의 ____를 ____했기 때문에 list만 찍어도 안에 내용이 나온다
		// Object의 toString()를 오버라이딩 했기에
		
	
		// 장점1. 크기 제약 x
		// 값 추가 & 추가시, 자동 길이 추가
		list.add(new Student("이철수",80)); // 길이 3 -> 4로 자동추가
		System.out.println("list에 담긴 element 개수 : "+list.size());
	
	
		// 장점2. 추가/삭제/정렬 기능처리 간단
		// add(int index, E elemnet) 
		list.add(0, new Student("황철수",55)); // 0번 인덱스 자리에 객체추가
		System.out.println(list); // [황철수(55점), 김철수(0점), 최철수(100점), 박철수(70점), 이철수(80점)]
		System.out.println("list에 담긴 element 개수 : "+list.size()); // list에 담긴 element 개수 : 5
		
		
		
		// 삭제
		// remove(int index):E
//		// remove()의 return은 삭제한 값을 돌려준다
		Student stu = list.remove(4);
		System.out.println(stu);	// 이철수(80점)
		System.out.println(list);	// [황철수(55점), 김철수(0점), 최철수(100점), 박철수(70점)]
		
		
		
		// 삭제
		// remove(Object o):boolean
		list.remove(new Student("박철수",70)); // 주소값 비교
		System.out.println(list.remove(new Student("김철수",0))); // true -> boolean 반환
		System.out.println("list : " +list);  // list : [황철수(55점), 김철수(0점), 최철수(100점)]
		
		
		// 지네릭 추가 : <String> 
		
	
	}

	
/******************************** collection 2nd practice ***************************************/	
	public void method02() {
		
		ArrayList<Student> list = new ArrayList<Student>(3);
		
		
		// add(E e):boolean
		list.add(new Student("김",99));
		list.add(new Student("이",98));
		list.add(new Student("최",100));
		list.add(new Student("박",95)); // 길이 자동 추가 : 길이4
		
		System.out.println(list); // [김(99점), 이(98점), 최(100점), 박(95점)] 길이 4
		// Object의 toString()를 오버라이딩 때문에 주소값이 아닌 객체값이 바로나옴
		System.out.println(list.add(new Student("황",94))); // true // boolean값 반환
		
		
		// 장점1. 크기 제약 x
		// .size() : 인덱스 길이 반환
		System.out.println(list.size()); // 5
		
		
		// 장점2. 추가/삭제/정렬 기능처리 간단
		// add(int index, E elemnet) 
		list.add(5,new Student("차",88)); // 5번 인덱스에 '차' 추가
		list.add(5,new Student("사",87)); // 5번 인덱스 '차'자리에 '사'가 들어가면서 '차'가 뒤로 밀림
		
		System.out.println(list); // [김(99점), 이(98점), 최(100점), 박(95점), 황(94점), 사(87점), 차(88점)]
		
		
		// 삭제
		// remove(int index):E
//		// remove()의 return은 삭제한 값을 돌려준다
//		list.remove(7);
		System.out.println("remove(int index):E = "+list.remove(6)); // remove(int index):E = 차(88점)
		System.out.println(list); // [김(99점), 이(98점), 최(100점), 박(95점), 황(94점), 사(87점)]
		
		
		// 삭제
		// remove(Object o):boolean
		list.remove(new Student("사",87));
		System.out.println(list); // [김(99점), 이(98점), 최(100점), 박(95점), 황(94점)]
		
		System.out.println(list.remove(new Student("황",94))); // true - 반환값 불리안 겟
		System.out.println(list); // [김(99점), 이(98점), 최(100점), 박(95점)]
		
		
		// 지네릭 추가 : <String> 
		ArrayList<String> listStr = new ArrayList<String>(2);
		listStr.add("임");
		listStr.add("강");
		listStr.add("심");
		System.out.println(listStr); // [임, 강, 심]
		System.out.println(listStr.remove(2)); // 심
		System.out.println(listStr); // [임, 강] -> print에 써도 잘지워진다
		listStr.remove(new String("강"));
		System.out.println(listStr); // [임]
		// equals랑 hashCode가 잘 오버라이딩이 되어있기 때문에 삭제 가능
		
		
		// set(int index, E e)
		// 해당 인덱스 번호에 값  교체
		list.set(3, new Student("박박",99));
		System.out.println(list); // [김(99점), 이(98점), 최(100점), 박박(99점)]
		System.out.println(list.set(2, new Student("최최",1000))); // 최(100점)
		System.out.println(list); // [김(99점), 이(98점), 최최(1000점), 박박(99점)]
		
		
		//get(int index):E
		// 인덱스번호의 엘리먼트 값을 가져온다
		Student stu = list.get(1);
		System.out.println(stu); 		 // 이(98점)
		System.out.println(list.get(0)); // 김(99점)
		
		
		
		// contains(Object) : boolean
		// indexObject : int 
		
		System.out.println(list.contains(new Student("김",99))); // true
		System.out.println(list.indexOf(new Student("김",99)));	// 0
		
		// 지네릭<String>과 일반 참조객체<Student>의 오버라이딩 비교
		System.out.println(listStr.contains(new String("임")));   // true
		System.out.println(listStr.indexOf(new String("임")));	 // 0
		System.out.println(listStr.indexOf(new String("임없음"))); // -1 : 없는 값이라 -1반환
		//  equals메소드와 해쉬코드가 오버라이딩 되지 않으면 주소값이 달라 없는걸로 나옴. 현재는 오버라이딩된 상태
		
		
		// clear():void
		list.clear();
		System.out.println(list); 			 // []
//		System.out.println(listStr.clear()); // clear는 반환타입이 없는 void라 print시켜서 java.lang.Error 
		listStr.clear();
		
		// isEmpty():boolean
		System.out.println(list.isEmpty());		// true
		System.out.println(listStr.isEmpty());	// true
		
		
	}
	
	
	
/******************************** collection 3rd practice ***************************************/	
	public void method03() {
		// 3번째 연습할거는 오버라이딩 해제하면서, equals()와 hashCode() 비교
		
		
	}	
	
}
