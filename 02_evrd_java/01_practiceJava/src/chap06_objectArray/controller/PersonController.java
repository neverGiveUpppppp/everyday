package chap06_objectArray.controller;

import java.util.Arrays;

import chap06_objectArray.model.vo.Person;

public class PersonController {
	
	public void objectArray01() {
		
		// 배열 선언 & 초기화
		Person[] pArr = new Person[5];
		
		pArr[0] = new Person();
		pArr[1] = new Person("a",25,'남',173.1,67.2);
		pArr[2] = new Person("b",23,'남',182.1,78.2);
		
//		System.out.println(Arrays.toString(pArr));
//		System.out.println(pArr.length);
//		System.out.println(Person.personInfo());
		
		for(int i=0; i < pArr.length; i++) {
			System.out.println(pArr[i].personInfo()); // for문으로 찎어서 비어있는 3,4배열 때문에 널포인터뜸
		}
		
	}
	
	public void objectArray02() {
		Person[] pArr = {new Person("힘내",46,'남',161,63),
						 new Person("아자아자",32,'남',272,46.1),
						 new Person("으랴차차",35,'남',171.1,69)};
						 
		
	}
	
	
}