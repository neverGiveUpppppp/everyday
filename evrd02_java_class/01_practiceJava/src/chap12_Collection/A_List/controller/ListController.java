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
		
		
		// set(int index, E e) : 바꾸기전 값 반환
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
		ArrayList a = new ArrayList(2); // 제네릭 안써서 노란줄 경고
		ArrayList<Student> al = new ArrayList<Student>(2);
		ArrayList<Student> list = new ArrayList<>(1);
		
		// vo클래스에 있는 오버라이딩된 toStirng(), equals(), hashCoding() 
		// 전부 주석처리
		
		
		// add(E e):boolean
		al.add(new Student("ㄱ",100));
		al.add(new Student("ㄴ",90));
		System.out.println(al);			// [ㄱ(100점), ㄴ(90점)]
		// Object의 toString()를 오버라이딩 때문에 주소값이 아닌 객체값이 바로나옴
		// toString() 오버라이딩 안된 경우(오버라이딩 주석처리) 결과값 : [chap12_Collection.A_List.model.vo.Student@6d06d69c, chap12_Collection.A_List.model.vo.Student@7852e922]
		al.add(2, new Student("ㄷ",95));	
		System.out.println(al);	// [chap12_Collection.A_List.model.vo.Student@6d06d69c, chap12_Collection.A_List.model.vo.Student@7852e922, chap12_Collection.A_List.model.vo.Student@4e25154f]
		System.out.println(al); // toString오버라이딩 후 : [ㄱ(100점), ㄴ(90점), ㄷ(95점)]
		
		// add(int index, E element) 
		list.add(0, new Student("ㄱㄱ",100));
		System.out.println(list);	// [ㄱㄱ(100점)]
		list.add(1, new Student("ㄴㄴ",90));
		list.add(2, new Student("ㄷㄷ",85));
		System.out.println(list);// [ㄱㄱ(100점), ㄴㄴ(90점), ㄷㄷ(85점)]
		

		// addAll(Collection<? extends E> c) : boolean
		al.addAll(al);
		System.out.println(al);// [ㄱ(100점), ㄴ(90점), ㄷ(95점), ㄱ(100점), ㄴ(90점), ㄷ(95점)]
		
		// addAll(int index, Collection c) : boolean
		al.addAll(1,al);
		System.out.println(al); // [ㄱ(100점), ㄱ(100점), ㄴ(90점), ㄷ(95점), ㄱ(100점), ㄴ(90점), ㄷ(95점), ㄴ(90점), ㄷ(95점), ㄱ(100점), ㄴ(90점), ㄷ(95점)]
		// toString() 오버라이딩 주석 후 : [chap12_Collection.A_List.model.vo.Student@6d06d69c, chap12_Collection.A_List.model.vo.Student@6d06d69c, chap12_Collection.A_List.model.vo.Student@7852e922, chap12_Collection.A_List.model.vo.Student@4e25154f, chap12_Collection.A_List.model.vo.Student@6d06d69c, chap12_Collection.A_List.model.vo.Student@7852e922, chap12_Collection.A_List.model.vo.Student@4e25154f, chap12_Collection.A_List.model.vo.Student@7852e922, chap12_Collection.A_List.model.vo.Student@4e25154f, chap12_Collection.A_List.model.vo.Student@6d06d69c, chap12_Collection.A_List.model.vo.Student@7852e922, chap12_Collection.A_List.model.vo.Student@4e25154f]
		
		
		
		// 장점1. 크기 제약 x
		// .size() : 인덱스 길이 반환
		System.out.println(al.size()); // 12
		
		
		// 장점2. 추가/삭제/정렬 기능처리 간단
		
		
		
		// 삭제
		// remove(int index):E
//		// remove()의 return은 삭제한 값을 돌려준다
//		list.remove(7);
		list.remove(2);
		System.out.println("remove(int index) : "+list); // [ㄱㄱ(100점), ㄴㄴ(90점)]
		System.out.println(list.remove(1)); // ㄴㄴ(90점) <- 대괄호 없고, 지운 객체 반환(pop개념) 
		System.out.println(list); // [ㄱㄱ(100점)]
		
		Student delList = al.remove(11);
		System.out.println("delList : "+delList);	// delList : ㄷ(95점)
		System.out.println(al);			// [ㄱ(100점), ㄱ(100점), ㄴ(90점), ㄷ(95점), ㄱ(100점), ㄴ(90점), ㄷ(95점), ㄴ(90점), ㄷ(95점), ㄱ(100점), ㄴ(90점)]
		Student delList2 = al.remove(10);
		System.out.println(delList2);  // ㄴ(90점)
		System.out.println(al);			// [ㄱ(100점), ㄱ(100점), ㄴ(90점), ㄷ(95점), ㄱ(100점), ㄴ(90점), ㄷ(95점), ㄴ(90점), ㄷ(95점), ㄱ(100점)]

		
		
		// 삭제
		// remove(Object o):boolean
		// 같은 데이터라면 앞에 있는거부터 삭제
		al.remove(new Student("ㄷ",95));
		al.remove(new Student("ㄷ",95));
		System.out.println(al.remove(new Student("ㄷ",95))); // false
		System.out.println(al);			// [ㄱ(100점), ㄱ(100점), ㄴ(90점), ㄷ(95점), ㄱ(100점), ㄴ(90점), ㄷ(95점), ㄴ(90점), ㄷ(95점), ㄱ(100점), ㄴ(90점), ㄷ(95점)]
		// equals()가 오버라이딩이 안되어 있어서 값 비교가 아니라 주소값 비교라 삭제 못한 것.
		System.out.println(al.size());
		
		
		// 지네릭 추가 : <String> 
		// equals랑 hashCode가 잘 오버라이딩이 되어있기 때문에 삭제 가능
		ArrayList<String> sList = new ArrayList<>(2);
		
		sList.add(new String("a"));
		sList.add(1, new String("b"));
		System.out.println(sList);		// [a, b]
		// 제네릭<String> 같은 경우, toString()이 오버라이딩 안되어있거나 데이터 리턴이 없어도 객체값을 잘 내보내줌
		
		
		
		// set(int index, E e)
		// 해당 인덱스 번호에 값  교체
		sList.set(1, new String("changed Capital B"));
		System.out.println(sList);  // [a, changed Capital B]
		al.set(0, new Student("a",50));
		System.out.println(al);		// [a(50점), ㄱ(100점), ㄴ(90점), ㄷ(95점), ㄱ(100점), ㄴ(90점), ㄷ(95점), ㄴ(90점), ㄷ(95점), ㄱ(100점), ㄴ(90점), ㄷ(95점)]
		
		
		// get(int index):E
		// 인덱스번호의 엘리먼트 값을 가져온다
		sList.get(0);
		String str = sList.get(1);
		System.out.println(sList);	// [a, changed Capital B]
		System.out.println(str);	// changed Capital B
		
		
		// contains(Object) : boolean
		// indexObject : int 
		System.out.println(al.contains(new Student("a",50))); // false
		
		
		// int값 데이터 반환된 걸 if문에 적용
		if(al.contains(new Student("ㄱ",100))){
			System.out.println("포함 & 출력");
		}else {
			System.out.println("미포함. 값이 맞다면 equals()오버라이딩 체크해보세여");
		}	// equals()가 오버라이딩 되어있다면 값을 비교하기 때문에 contains()가 작동하고, 
			// 아닐 경우 값이 같더라도 주소값이 비교되기 때문에 작동x
		al.indexOf(new Student("ㄴ",90));
		System.out.println(al.indexOf(new Student("ㄴ",90)));	// 2
		// 반환된 인덱스번호로 if조건문 줘서 실행하기
		if(al.indexOf(new Student("ㄴ",90)) > 0) {
			al.remove(6);
			System.out.println("indexOf의 번호가 0이상이면 객체값 하나 삭제함");	// indexOf의 번호가 0이상이면 객체값 하나 삭제함
		}else {
			System.out.println("조건 미충족. do nothing");
		}
		
		System.out.println(sList.get(0));	// a
//		if((sList.get(0)) == "a") {
		if((sList.get(0)).equals("a")) {
			System.out.println("true");	// true
		}else {
			System.out.println("false");
		}
		
		// 반환된 boolean타입으로 while조건 사용하기
		System.out.println("a : "+sList.contains("a")); // true
		int i = 0;
		while(sList.contains("a")) {
			System.out.print(i+" ");	// 0 1 2 3 4 5 6 7 8 9 
			i++;
			if(i>=10) {
				break;	// while문 무한루프 + break문 사용
			}
		}
//		while(true) {
//			System.out.println("====");
//			i++;
//			if(i>=10) {
//				break;
//			}
//		}
		
//		while(sList.contains("a")) {
//			if(i>=10) {
//				break;
//			}
//			i += 2;
//			System.out.print(i+ " ");
//		}
		
		
		// 지네릭<String>과 일반 참조객체<Student>의 오버라이딩 비교
		//  equals메소드와 해쉬코드가 오버라이딩 되지 않으면 주소값이 달라 없는걸로 나옴. 현재는 오버라이딩된 상태
		
		
		// clear():void
		al.clear();
		System.out.println(al);// []
		// isEmpty():boolean
		al.isEmpty();
		System.out.println(al.isEmpty()); // true
		
		if(al.isEmpty()) {
			al.add(new Student("new",10));
			System.out.println(al);  // [new(10점)]
		}
		
	}	
	
	
	

/******************************** collection 4th practice ***************************************/	
	public void method04() {
		// 연습할 거 포인트 
		// 오버라이딩 해제하면서, equals()와 hashCode() 비교
		// 반환 타입(boolean,int등)에 따라 if,for,while문 조건식에 뭔가 응용해보기
		// 		반환 받은 객체로는 뭘 해볼 수 있을까? ==나 equals() 사용해서 해당 str 값이 있으면 if문 써봐도 될 듯
		
		
		ArrayList<Student> aList = new ArrayList<>(2);
		ArrayList<Student> aList2 = new ArrayList<Student>(1);
		// vo클래스에 있는 오버라이딩된 toStirng(), equals(), hashCoding() 
		// 전부 주석처리
		
		
		// add(E e):boolean
		aList.add(new Student("아이유",100));
		System.out.println(aList); 	// [chap12_Collection.A_List.model.vo.Student@6d06d69c]
		System.out.println(aList); 	// vo class의 toString() 메소드 만들어 준 이후 : [아이유(100점)]
		// Object의 toString()를 오버라이딩 때문에 주소값이 아닌 객체값이 바로나옴
		// 길이가 0 1 2인데 0하나만 들어갔지만 자동으로 0만 나오고 null값이 나오거나 에러가 뜨지 않음
		
		// add(int index, E element) 
		aList.add(1, new Student("손예진",100));
		System.out.println(aList); 	// [아이유(100점), 손예진(100점)]
		
		// addAll(Collection<? extends E> c) : boolean
		// 같은 타입의 다른 객체를 통째로 넣어버림
		aList2.addAll(aList);
		System.out.println("aList2 = "+aList2); // aList2 = [아이유(100점), 손예진(100점)]
		
		
		// addAll(int index, Collection c) : boolean
		// toString() 오버라이딩 주석 후
		aList2.addAll(1,aList);			// 1번 인덱스부터 새데이터를 넣겠다
		System.out.println(aList2); 	// [아이유(100점), 아이유(100점), 손예진(100점), 손예진(100점)]

		
		// 장점1. 크기 제약 x
		// .size() : 인덱스 길이 반환
		System.out.println(aList2.size()); // 4
		
		if(aList2.size() == 4) {	// ==은 primitive타입일 경우 값 비교, 참조형일 경우 주소값비교
			System.out.println("aList2의 길이는 4"); // aList2의 길이는 4
			aList.add(new Student("if로추가한add",100));
			System.out.println("aList added : "+aList); // aList added : [아이유(100점), 손예진(100점), if로추가한add(100점)]
		}
		
		
		
		// 장점2. 추가/삭제/정렬 기능처리 간단
		
		
		
		// 삭제
		// remove(int index):E
//		// remove()의 return은 삭제한 값을 돌려준다
		System.out.println(aList.remove(2)); // if로추가한add(100점) <- 삭제한 객체 반환
		aList2.remove(aList.size());	// aList의 길이인 인덱스를 삭제
		System.out.println(aList2);		// [아이유(100점), 아이유(100점), 손예진(100점)]
		
		// 삭제
		// remove(Object o):boolean
		// 같은 데이터라면 앞에 있는거부터 삭제
//		aList2.remove(new Student("아이유",100));
		System.out.println(aList2); // equals()가 오버라이딩이 안되어 있어서 값 비교가 아니라 주소값 비교라 삭제 못한 것.
		// equals() 오버라이딩 후
		aList2.remove(new Student("아이유",100));
		System.out.println(aList2); // [아이유(100점), 손예진(100점)]
		
		
		// 지네릭 추가 : <String> 
		// toString() 오버라이딩 삭제 -> 삭제해도 String Class자체에서 가능해서 값 출력이 됨
		// String Class이기 때문에 str만 가능
		// equals랑 hashCode가 잘 오버라이딩이 되어있기 때문에 삭제 가능
		ArrayList<String> listStr = new ArrayList<>(2);
		
		listStr.add(new String("킴"));
		// str이라면 인자 몇개까지 가능할까?
//		listStr.add(new String("킴","킴")); parameter 하나만 가능
		listStr.add(new String());
		System.out.println(listStr); // [킴, ]
		
		
		// str 인자 두개하고 싶으면 제네릭 두개쓰면 될까?
		// 파라미터 부적잘하다네. 제네리 두개 가능했던 것 같은데 뒤에 코드를 봐야할 듯
//		ArrayList<Student, String> listStrDouble = new ArrayList<Student, String>(1);
		

		
		
		// set(int index, E e)
		// 해당 인덱스 번호에 값  교체
		aList.set(1,new Student("손예진 결혼",100));
		System.out.println(aList); // [아이유(100점), 손예진 결혼(100점)]
		
		
		//get(int index):E
		// 인덱스번호의 엘리먼트 값을 가져온다
		System.out.println(aList.get(1)); // 손예진 결혼(100점)

		
		
		// contains(Object) : boolean
		// indexObject : int 
		// 없으면 -1 반환
		System.out.println(aList.indexOf(1)); // -1
		System.out.println(aList);	// [아이유(100점), 손예진 결혼(100점)]
		System.out.println(aList.indexOf(new Student("아이유",100))); // 0 <-아이유가 0번째 인덱스에 있다는걸 반환
		// equals() 오버라이딩을 삭제하면, 값비교가 안되서 자동으로 -1 반환함
		
		if(aList.indexOf(new Student("아이유",100)) >= 0) {
			System.out.println("aList 0보다 크면 조건 응용 -> add 실행");
			aList.add(2,new Student("김사랑",100));
			System.out.println(aList);	// [아이유(100점), 손예진 결혼(100점), 김사랑(100점)]
		}
		
		// 지네릭<String>과 일반 참조객체<Student>의 오버라이딩 비교
		//  equals메소드와 해쉬코드가 오버라이딩 되지 않으면 주소값이 달라 없는걸로 나옴. 현재는 오버라이딩된 상태
		
		
		// clear():void
		aList.clear();
		System.out.println(aList); // []
		
		// isEmpty():boolean
		aList.isEmpty();
		System.out.println(aList.isEmpty()); // true

		
		
	}	
		
	
	
	
/******************************** collection 5th practice ***************************************/	
	public void method05() {
		// 연습할 거 포인트 
		// 오버라이딩 해제하면서 toString(), equals()와 hashCode() 비교
		// 반환 타입(boolean,int등)에 따라 if,for,while문 조건식에 뭔가 응용해보기
		// 		반환 받은 객체로는 뭘 해볼 수 있을까? ==나 equals() 사용해서 해당 str 값이 있으면 if문 써봐도 될 듯
		
		// toString(), equals(), hashCode() 3개 코드 직접 써보기
		
		
		ArrayList<String> strList = new ArrayList<>(2);
		
		strList.add(new String("김"));
//		strList.add(new String("이","ㄱ")); // 인자 한개만 가능
		strList.add(new String("이"));
		
		System.out.println(strList); // [김, 이]
		
		// String class의 add, addAll
		strList.add(0,new String("박"));
		System.out.println(strList); // [박, 김, 이]
		
		ArrayList<String> aList = new ArrayList<>(2);
		aList.add("추가");
		
		strList.addAll(aList);	
		System.out.println(strList);	// [박, 김, 이, 추가]
		
		String a = new String("임시");
		
		strList.add(3,new String(a));	// "임시" 대신 변수명a로 대체가능
		System.out.println(strList); 	// [박, 김, 이, 임시, 추가]
		// 필드항목이 여러개인 사용자정의 클래스도 똑같이 적용 가능할까?
		
		
		ArrayList<Student> list = new ArrayList<>(2);
		ArrayList<Student> list2 = new ArrayList<Student>(2);
		
		
		// add(E e):boolean
		// Object의 toString()를 오버라이딩 때문에 주소값이 아닌 객체값이 바로나옴
		// add(int index, E element) 
		list.add(new Student("a",100));
		list.add(0, new Student("-a",99));

		list2.add(new Student("ㅁ",100));
		
		// addAll(Collection<? extends E> c) : boolean
		list.addAll(list2); // 다른 컬렉션 추가 성공. 아직 extends까지는 
		
		System.out.println(list); // [-a(99점), a(100점), ㅁ(100점)]
		
		
		// vo클래스에 있는 오버라이딩된 toString(), equals(), hashCoding() 
		// 전부 주석처리

		
		// 장점1. 크기 제약 x
		// 장점2. 추가/삭제/정렬 기능처리 간단

		// .size() : 인덱스 길이 반환
		System.out.println(list.size());	// 3
		
		
		// 삭제
		// remove(int index):E
//		// remove()의 return은 삭제한 값을 돌려준다
		// remove(Object o):boolean
		// 같은 데이터라면 앞에 있는거부터 삭제
		aList.remove(0);
		System.out.println(aList); // []
		
		strList.remove(new String("-a"));	
		System.out.println(strList.remove(new String("-a")));  // false
		System.out.println(strList.remove(new String("추가"))); // true
		strList.removeAll(aList);
		System.out.println(strList); // [박, 김, 이, 임시]
		System.out.println(aList);	 // []
		
		System.out.println(strList.remove(3)); // 임시   <- 삭제한 객체 반환
		
		// equals()가 오버라이딩이 안되어 있어서 값 비교가 아니라 주소값 비교라 삭제 못한 것.

		
		
		// 지네릭 추가 : <String> 
		// toString() 오버라이딩 삭제 -> 삭제해도 String Class자체에서 가능해서 값 출력이 됨
		// equals랑 hashCode가 잘 오버라이딩이 되어있기 때문에 삭제 가능
		
		
		
		// set(int index, E e) : String
		// 해당 인덱스 번호에 값  교체
		// equals(),hashCode() 필요x
		strList.set(2, new String("잉?"));
		System.out.println(strList); // [박, 김, 잉?]
		System.out.println(strList.set(2, new String("잉???"))); // 잉? <- 바꾸기전 값 반환
		// String Class라 equals(),hashCode() 필요x

		list.add(new Student("b",100));
		System.out.println(list); // [-a(99점), a(100점), ㅁ(100점), b(100점)]
		list.set(3, new Student("c",100));
		System.out.println(list); // [-a(99점), a(100점), ㅁ(100점), c(100점)]
		
		//get(int index):E
		// 인덱스번호의 엘리먼트 값을 가져온다
		System.out.println(strList.get(2)); // 잉???
		System.out.println(list.get(0));	// -a(99점)
		
		// contains(Object) : boolean
		// indexObject : int 
		System.out.println(list.contains(new Student("-a",99))); // false
		// equals() 오버라이딩 후에는 true로 찍힘
		
		System.out.println(list.indexOf(new Student("c",100))); // 3
		// equals() 삭제하면, 값이 없으므로 -1 반환
		
		
		// clear():void
		list.clear();
		System.out.println(list); // []
		// isEmpty():boolean
		list.isEmpty();
		System.out.println(list.isEmpty()); // true
		System.out.println(list); // []
	}	
	
	
/******************************** collection 6th practice ***************************************/	
	public void method06() {
		// 연습할 거 포인트 
		// 오버라이딩 해제하면서 toString(), equals()와 hashCode() 비교
		// 반환 타입(boolean,int등)에 따라 if,for,while문 조건식에 뭔가 응용해보기
		// 		반환 받은 객체로는 뭘 해볼 수 있을까? ==나 equals() 사용해서 해당 str 값이 있으면 if문 써봐도 될 듯
		
		// toString(), equals(), hashCode() 3개 코드 직접 써보기
		
		

		// vo클래스에 있는 오버라이딩된 toString(), equals(), hashCoding() 
		// 전부 주석처리
		
		ArrayList<Student> list1 = new ArrayList<>(2);
		ArrayList<Student> list2 = new ArrayList<Student>(2);
		ArrayList<Student> list3 = new ArrayList<>(1);
		
		// add(E e):boolean
		// add(int index, E element) 
		// Object의 toString()를 오버라이딩 때문에 주소값이 아닌 객체값이 바로나옴
		list1.add(new Student("zero",100));
		list1.add(0,new Student("0",0));
		
		list2.add(new Student("ㄱ",100));
		list2.add(1,new Student("ㄴ",100));
		
		System.out.println(list1); // [zero(0점), a(100점)]
		System.out.println(list2); // [ㄱ(100점), ㄴ(100점)]
		
		// addAll(Collection<? extends E> c) : boolean
//		list3.addAll(list1).addAll(list2); 메소드 체이닝 작동x
		list3.addAll(list1);
		list3.addAll(list2);
		System.out.println(list3); // [0(0점), zero(100점), ㄱ(100점), ㄴ(100점)]
		
		
		// addAll(int index, Collection c) : boolean
		list3.addAll(1,list1);
		System.out.println(list3); // [0(0점), 0(0점), zero(100점), zero(100점), ㄱ(100점), ㄴ(100점)]
		System.out.println(list3.addAll(4, list2)); // true
		System.out.println(list3); // [0(0점), 0(0점), zero(100점), zero(100점), ㄱ(100점), ㄴ(100점), ㄱ(100점), ㄴ(100점)]
		
		
		// 장점1. 크기 제약 x
		// 장점2. 추가/삭제/정렬 기능처리 간단
		
		// .size() : 인덱스 길이 반환
		System.out.println(list3.size()); // 8
		if(list3.size() >= 5) {
			System.out.println("아메리카노 1잔 주문"); // 아메리카노 1잔 주문
		}
		
		
		
		// 삭제
		// remove(int index):E
//		// remove()의 return은 삭제한 값을 돌려준다
//		list.remove(7);
		list3.remove(7);	// 끝에 ㄴ 지우기
		System.out.println(list3); // [0(0점), 0(0점), zero(100점), zero(100점), ㄱ(100점), ㄴ(100점), ㄱ(100점)]
		System.out.println(list3.remove(6)); // ㄱ(100점) <- 지운 값 반환
		
		
		// 삭제
		// remove(Object o):boolean
		// 같은 데이터라면 앞에 있는거부터 삭제
		// equals()가 오버라이딩이 안되어 있어서 값 비교가 아니라 주소값 비교라 삭제 못한 것.
		System.out.println(list3); // [0(0점), 0(0점), zero(100점), zero(100점), ㄱ(100점), ㄴ(100점)]
		System.out.println(list3.remove(new Student("ㄴ",100))); // false
		// VO class equals() 적용 후, true
		System.out.println(list3); // [0(0점), 0(0점), zero(100점), zero(100점), ㄱ(100점)]
		
		
		// 지네릭 추가 : <String> 
		// toString() 오버라이딩 삭제 -> 삭제해도 String Class자체에서 가능해서 값 출력이 됨
		// equals랑 hashCode가 잘 오버라이딩이 되어있기 때문에 삭제 가능
		ArrayList<String> sList = new ArrayList<>(1);
		
		sList.add(new String("a"));
		sList.add(new String("b"));
		sList.add(new String("c")); // 초기 지정길어 넘어서 길이 자동추가
		System.out.println(sList);  // [a, b, c]

		
		
		// set(int index, E e) : 바꾸기 전 값 반환
		// 해당 인덱스 번호에 값  교체
		// equals(),hashCode() 필요x
		sList.set(2, new String("cc"));
		System.out.println(sList);	// [a, b, cc]
		list2.set(1, new Student("ㄷ",100));
		System.out.println(list2);	// [ㄱ(100점), ㄷ(100점)]
		
		
		//get(int index):E
		// 인덱스번호의 엘리먼트 값을 가져온다
		System.out.println(list2.get(1)); // ㄷ(100점)
		System.out.println(sList.get(2)); // cc	
		
		
		// contains(Object) : boolean
		// indexObject : int 
		// equals() 삭제
		System.out.println(sList.contains("cc"));	// true
		System.out.println(list2.contains(new Student("ㄱ",100))); // false
		// String Class라서 cc는 true가 나왔고, list2는 값 비교가 안되고 객체참조의 디폴트인 주소값 비교라서 false가 나옴
		// equals()가 있고 다시 찍어보면 true
		
		
		// 지네릭<String>과 일반 참조객체<Student>의 오버라이딩 비교
		//  equals메소드와 해쉬코드가 오버라이딩 되지 않으면 주소값이 달라 없는걸로 나옴. 현재는 오버라이딩된 상태
		
		
		// clear():void
		list2.clear();
		System.out.println(list2); // []
		
		// isEmpty():boolean
		System.out.println(list2.isEmpty());	// true
		
		
	}	
	
	
/******************************** collection 7th practice ***************************************/	
	public void method07() {
		// 연습할 거 포인트 
		// 각 메소드가 toString(), equals()와 hashCode() 이 셋 중 어떤거에 영향 받나 비교
		
		// 	오버라이딩 해제하면서 toString(), equals()와 hashCode() 비교
		// 	반환 타입(boolean,int등)에 따라 if,for,while문 조건식에 뭔가 응용해보기
		// 		반환 받은 객체로는 뭘 해볼 수 있을까? ==나 equals() 사용해서 해당 str 값이 있으면 if문 써봐도 될 듯
		// 	toString(), equals(), hashCode() 3개 코드 직접 써보기
		
		


		// add(E e):boolean
		// add(int index, E element) 
		// Object의 toString()를 오버라이딩 때문에 주소값이 아닌 객체값이 바로나옴
		ArrayList<Student> al = new ArrayList<>();
		al.add(new Student("a",100));
		al.add(new Student("b",100));
		System.out.println(al);
		
		al.add(0, new Student("0",0));
		al.add(1,new Student("delimeter",000));
		System.out.println(al); // [0(0점), delimeter(0점), a(100점), b(100점)]
		if(al.add(new Student("c",100))) {
			System.out.println(al+" : c 출력성공"); // [0(0점), delimeter(0점), a(100점), b(100점), c(100점)] : c 출력성공
		}
		

		// addAll(Collection<? extends E> c) : boolean
		// addAll(int index, Collection c) : boolean
		// .size() : 인덱스 길이 반환
		// toString() 오버라이딩 주석 후
		ArrayList<Student> al2 = new ArrayList<>();
		al2.addAll(al);
		System.out.println(al2); // [0(0점), delimeter(0점), a(100점), b(100점), c(100점)]
		System.out.println(al2.size()); // 5
		System.out.println(al.hashCode());	// 346539999 <- 주소값 같음
		System.out.println(al2.hashCode()); // 346539999 <- shallow copy
		// addAll로 객체를 통째로 넣어서 같게 만들면, shallow copy
		
		// 삭제
		// remove(int index):E
//		// remove()의 return은 삭제한 값을 돌려준다
		
		System.out.println(al.remove(4)); // c(100점)
		al.remove(0);
		System.out.println(al); // [delimeter(0점), a(100점), b(100점)]
		
		
		// remove(Object o):boolean
		// 같은 데이터라면 앞에 있는거부터 삭제
		// equals()가 오버라이딩이 안되어 있어서 값 비교가 아니라 주소값 비교라 삭제 못한 것.
		al.remove(al);
		System.out.println(al.hashCode());
		
		
		// 지네릭 추가 : <String> 
		// toString() 오버라이딩 삭제 -> 삭제해도 String Class자체에서 가능해서 값 출력이 됨
		// equals랑 hashCode가 잘 오버라이딩이 되어있기 때문에 삭제 가능
		ArrayList<String> alStr = new ArrayList<>();
		alStr.add("추가1");
		alStr.add("추가2");
		System.out.println(alStr); // [추가1, 추가2]
		
		
		// set(int index, E e) : 바꾸기 전 값 반환
		// 해당 인덱스 번호에 값  교체
		// equals(),hashCode() 필요x
		alStr.set(1, "추가의 추가");
		System.out.println(alStr); // [추가1, 추가의 추가]
		System.out.println(alStr.set(1, "추가의 추가를 바꾸기")); // 추가의 추가
		System.out.println(alStr); // [추가1, 추가의 추가를 바꾸기]
		
		
		//get(int index):E
		// 인덱스번호의 엘리먼트 값을 가져온다
		
		// ArrayList의 전체길이에서 마지막 값 빼오기
		System.out.println(alStr.get(alStr.size()-1)); // 추가의 추가를 바꾸기
		
		
		// contains(Object) : boolean
		// indexObject : int 
		System.out.println(al.contains(new Student("a",100))); // false
		// equals() on
		System.out.println(al.contains(new Student("a",100))); // true
		// equals() off
		System.out.println(alStr.contains("추가1")); // true
		// 		-> String class는 자동 오버라이딩이 되어 있어서 equals()가 필요없음
		
		System.out.println(al.indexOf(new Student("b",100))); // -1
		// equals() on
		System.out.println(al.indexOf(new Student("b",100))); // 2
		
		
		// clear():void
		al2.clear();
		System.out.println(al2); // []
		// isEmpty():boolean
		System.out.println(al2.isEmpty());  // true
		System.out.println(al.isEmpty());	// false

		
		
		
	}
	
	
	
	
/******************************** collection 8th practice ***************************************/	
	public void method08() {
		// 연습할 거 포인트 
		// 각 메소드가 toString(), equals()와 hashCode() 이 셋 중 어떤거에 영향 받나 비교
		
		// 	오버라이딩 해제하면서 toString(), equals()와 hashCode() 비교
		// 	반환 타입(boolean,int등)에 따라 if,for,while문 조건식에 뭔가 응용해보기
		// 		반환 받은 객체로는 뭘 해볼 수 있을까? ==나 equals() 사용해서 해당 str 값이 있으면 if문 써봐도 될 듯
		// 	toString(), equals(), hashCode() 3개 코드 직접 써보기
		
		
		
		// vo클래스에 있는 오버라이딩된 toString(), equals(), hashCoding() 
		// 전부 주석처리
		
		
		
	}
	
	
	
	
	// vo클래스에 있는 오버라이딩된 toString(), equals(), hashCoding() 
	// 전부 주석처리
	
	
	// add(E e):boolean
	// add(int index, E element) 
	// Object의 toString()를 오버라이딩 때문에 주소값이 아닌 객체값이 바로나옴
	

	// addAll(Collection<? extends E> c) : boolean
	// addAll(int index, Collection c) : boolean
	// .size() : 인덱스 길이 반환
	// toString() 오버라이딩 주석 후

	
	// 장점1. 크기 제약 x
	// 장점2. 추가/삭제/정렬 기능처리 간단
	
	
	// 삭제
	// remove(int index):E
//	// remove()의 return은 삭제한 값을 돌려준다
	
	
	// remove(Object o):boolean
	// 같은 데이터라면 앞에 있는거부터 삭제
	// equals()가 오버라이딩이 안되어 있어서 값 비교가 아니라 주소값 비교라 삭제 못한 것.

	
	
	// 지네릭 추가 : <String> 
	// toString() 오버라이딩 삭제 -> 삭제해도 String Class자체에서 가능해서 값 출력이 됨
	// equals랑 hashCode가 잘 오버라이딩이 되어있기 때문에 삭제 가능
	
	
	
	// set(int index, E e) : 바꾸기 전 값 반환
	// 해당 인덱스 번호에 값  교체
	// equals(),hashCode() 필요x
	
	
	//get(int index):E
	// 인덱스번호의 엘리먼트 값을 가져온다
	
	
	
	// contains(Object) : boolean
	// indexObject : int 
	// 		- equals()가 필요한 메소드들
	
	
	// 지네릭<String>과 일반 참조객체<Student>의 오버라이딩 비교
	//  equals메소드와 해쉬코드가 오버라이딩 되지 않으면 주소값이 달라 없는걸로 나옴. 현재는 오버라이딩된 상태
	
	
	// clear():void
	
	// isEmpty():boolean

	
	
	
	// addAll로 객체를 통째로 넣어서 같게 만들면, shallow copy
//	System.out.println(al.hashCode());	// 346539999 <- 주소값 같음
//	System.out.println(al2.hashCode()); // 346539999 <- shallow copy
	// ArrayList의 전체길이에서 마지막 값 빼오기
//	System.out.println(alStr.get(alStr.size()-1)); // 추가의 추가를 바꾸기
	
	
	
	
}


