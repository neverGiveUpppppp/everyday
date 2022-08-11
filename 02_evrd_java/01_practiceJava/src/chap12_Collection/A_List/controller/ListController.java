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
	
	
	
	
	
}
