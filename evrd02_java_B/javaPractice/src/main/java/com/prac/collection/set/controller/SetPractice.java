package com.prac.collection.set.controller;

import com.prac.collection.set.model.vo.Dog;

import java.util.*;

public class SetPractice {

    // Collection Set

    // Set
    // 순서x 중복저장x
    // key로 저장
    // key에 null 저장불가능
    //      인덱스가 없음. 
    //      set,map 인덱스x
    //      list    인덱스o
    //          즉, 인덱스 관련 메소드도 x   ex)remove(int index),get(int index):E, setint index, E e):E
    //              인덱스번호로 해당 엘리먼트 지우는 메소드는 list에만 있음

    // HashSet
    //  Set 핵심 메소드

    // LinkedHashSet
    // 순서 o 중복저장x
    // 순서 유지되고 중복저장 안되는 컬렉션
    // 중복 저장이 안된 이유?
    // Dog클래스에 equals()를 오버라이딩 해줬기 때문에 내용비교가 가능하게 되었고
    // 이 때문에 같은 객체로 인지되었기 때문


    // TreeSet
    // 정렬을 해주는 Set
    // 오름차순 정렬
    //		정렬 기준을 세워주는 건 <String>
    //		String 클래스 안에 comparable 인터페이스가(compareTo())가
    //		구현 되어있기 때문에 정렬 기준이 정해져 있고, 정렬 기준이 정해져 있는
    //		클래스 String이 TreeSet 안에 들어가 있기 때문에 정렬 가능


    // 순서x 중복x HashSet, add하면 어디에 추가될까?
    // 맨 앞에 추가됨
    // 순서o LinkedHashSet, add하면 어디에 추가될까?
    // 마지막에 추가됨


    /*

    인텔리제이 단축키 익히기

Ctrl + Shift + 방향키 : 코드 라인 이동


Ctrl + Q : 도큐먼트를 조회
Ctrl + P : 메서드의 파라미터 정보를 조회
Ctrl + O : Override 가능한 메서드 목록을 확인하여 코드를 자동 생성
Ctrl + I : Implement 가능한 메서드 목록을 확인하여 코드를 자동 생성

Ctrl + Shift + / : 블록 단위로 주석 처리(* *로 주석치리)
Alt + Enter : 추가되지 않은 Import 추가

Ctrl + Alt + D : 커서가 위치한 라인
을 복사하여 바로 밑에 라인에 붙여넣기
Ctrl + d : 커서가 위치한 라인을 삭제

alt + shift + 방향키 : 라인 이동


https://gmlwjd9405.github.io/2019/05/21/intellij-shortkey.html
https://tychejin.tistory.com/326






     */

    public void method01() {
        HashSet set0 = new HashSet();
        HashSet<Dog> set1 = new HashSet<>(); // 뒤 제네릭 생략 → 타입추론 : 뒤 생략가능하다는 의미

//        Set set = new Set();

        // add(E e):boolean
        set1.add(new Dog("a",10));
        set1.add(new Dog("b",10));

        System.out.println(set1);   // [b 10.0kg, a 10.0kg]

        // set 중복 저장안되는데 중복 저장 되는 이유 : equals() 오버라이딩 안되어있어서
        set1.add(new Dog("a",10));
        System.out.println(set1);   // [b 10.0kg, a 10.0kg, a 10.0kg]

        // add(Object e):boolean
        Dog puppy = new Dog("c",10);
        set1.add(puppy);
        System.out.println(set1);   // [c 10.0kg, b 10.0kg, a 10.0kg, a 10.0kg]
        // 순서x 중복x HashSet, add하면 어디에 추가될까?
        // 맨 앞에 추가됨
        set1.add(new Dog("d",10)); // [d 10.0kg, c 10.0kg, b 10.0kg, a 10.0kg, a 10.0kg]
        System.out.println(set1);
        set1.add(new Dog("e",10));
        System.out.println(set1); // [e 10.0kg, d 10.0kg, c 10.0kg, b 10.0kg, a 10.0kg, a 10.0kg]


        // LinkedHashSet
        // 순서 o 중복저장x
        // 순서 유지되고 중복저장 안되는 컬렉션
        // 중복 저장이 안된 이유?
        // Dog클래스에 equals()를 오버라이딩 해줬기 때문에 내용비교가 가능하게 되었고
        // 이 때문에 같은 객체로 인지되었기 때문
        LinkedHashSet<Dog> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(new Dog("ㄱ",10));
        System.out.println(linkedHashSet);  // [ㄱ 10.0kg]



        // 순서o LinkedHashSet, add하면 어디에 추가될까?
        // 마지막에 추가됨
        linkedHashSet.add(new Dog("ㄴ",10));
        System.out.println(linkedHashSet); // [ㄱ 10.0kg, ㄴ 10.0kg]
        linkedHashSet.add(new Dog("ㄷ",10));
        System.out.println(linkedHashSet);  // [ㄱ 10.0kg, ㄴ 10.0kg, ㄷ 10.0kg]
        linkedHashSet.add(new Dog("ㄷ",10));
        // equals() 주석 처리후에는 중복가능해짐
        System.out.println(linkedHashSet);  // [ㄱ 10.0kg, ㄴ 10.0kg, ㄷ 10.0kg, ㄷ 10.0kg]
        // equals() 주석 풀고 난 후에는 중복x
        System.out.println(linkedHashSet);  // [ㄱ 10.0kg, ㄴ 10.0kg, ㄷ 10.0kg]


        // HashSet<String>
        // equals() 오버라이딩 필요x
        HashSet hashSet = new HashSet<String>();
        // equals() 메소드 주석처리함

        hashSet.add("가");
        hashSet.add("나");
        System.out.println(hashSet);    // [가, 나]
        HashSet<String> hSet = new HashSet<>();
        hSet.add("가가");
        // addAll
        hashSet.addAll(hSet);
        System.out.println(hashSet); // [가, 가가, 나]  <- 순서xxx 기억할 것
        // HashSet<String>은 객체도 받음
        //      <-> ArrayList<String>은 에러났었음
        hashSet.add(new Dog("다",10)); // [가, 다 10.0kg, 가가, 나]
        hashSet.add("나나");
        System.out.println(hashSet); // [가, 다 10.0kg, 가가, 나나, 나]
        // 순서 없어서 가운데에도 추가되고 앞에 추가되기도 하고...
        hashSet.remove(new Dog("다",10));
        System.out.println(hashSet); // [가, 가가, 나나, 나]

        // TreeSet
        // 정렬을 해주는 Set
        // 오름차순 정렬
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("아");
        treeSet.add("이");
        treeSet.add("우");

        System.out.println(treeSet); // [아, 우, 이]
        treeSet.addAll(hashSet);
        System.out.println(treeSet); // [가, 가가, 나, 나나, 아, 우, 이]



        // 전체데이터 넣는 방법
        // 1) addAll()
        // 2) 생성자 매개변수 이용



    }

    public void method02(){


        // add(E e):boolean
        // addAll(Collection<? extends E> c) : boolean
        HashSet<Dog> hSet = new HashSet<Dog>();
        HashSet<Dog> hSet2 = new HashSet<>(); // 타입추론

        hSet.add(new Dog("aa",10));
        hSet.add(new Dog("bb",10));
        System.out.println(hSet);   // [aa 10.0kg, bb 10.0kg]

        hSet2.add(new Dog("ㄷ",0));
        // addAll도 맨 앞에 추가함
        hSet2.addAll(hSet);
        System.out.println(hSet2); // [aa 10.0kg, bb 10.0kg, ㄷ 0.0kg]

        // add(Object e):boolean
        // 순서x 중복x HashSet, add하면 어디에 추가될까?
        // 맨 앞에 추가됨
        hSet2.add(new Dog("ㄷ",0));
        System.out.println(hSet2); // [aa 10.0kg, bb 10.0kg, ㄷ 0.0kg]
        // set 중복 저장안되는데 중복 저장 되는 이유 : equals() 오버라이딩 안되어있어서

        HashSet<String> set = new HashSet<>();
        set.add("!");
        set.add("!!");
        set.add("!!");
        System.out.println(set); // [!!, !]   <- set 순서x 중복x

        // LinkedHashSet
        // 순서o 중복저장x 안되는 컬렉션
        LinkedHashSet<Dog> linkSet = new LinkedHashSet<>();
        linkSet.add(new Dog("가",0));
        linkSet.add(new Dog("나",0));
        System.out.println(linkSet); // [가 0.0kg, 나 0.0kg]
        linkSet.add(new Dog("ㄱ",0));
        linkSet.add(new Dog("A",-1));
        System.out.println(linkSet); // [가 0.0kg, 나 0.0kg, ㄱ 0.0kg, A -1.0kg] <- 순서유지



        // 중복 저장이 안된 이유?
        // Dog클래스에 equals()를 오버라이딩 해줬기 때문에 내용비교가 가능하게 되었고
        // 이 때문에 같은 객체로 인지되었기 때문
        linkSet.add(new Dog("A",-1));
        System.out.println(linkSet); // [가 0.0kg, 나 0.0kg, ㄱ 0.0kg, A -1.0kg]
        System.out.println(linkSet); // [가 0.0kg, 나 0.0kg, ㄱ 0.0kg, A -1.0kg, A -1.0kg]


        // HashSet<String>
        // equals() 오버라이딩 필요x
        // equals() 메소드 주석처리함
        HashSet<String> sSet = new HashSet<String>();
        HashSet<String> sSet2 = new HashSet<>();

        sSet.add("set은");
        sSet.add(" key저장");
        sSet2.addAll(sSet);
        System.out.println(sSet2); // [ key저장, set은]
        sSet2.add("map은 key&value저장");
        System.out.println(sSet2); // [map은 key&value저장,  key저장, set은]
        sSet2.clear();
        System.out.println(sSet2); // []
        sSet.remove("set은");
        System.out.println(sSet); // [ key저장]
        System.out.println(sSet.remove(" key저장")); // true
        System.out.println(sSet); // []

        sSet.add("a");

        // TreeSet
        // 정렬을 해주는 Set
        TreeSet<String> tSet = new TreeSet<>();
        tSet.add("ㄴ");
        tSet.add("ㄱ");
        tSet.add("ㄷ");
        System.out.println(tSet); // [ㄱ, ㄴ, ㄷ] <- 추가 순서랑 다르게 sorting 되서 저장됨

        tSet.add("ㄷ");
        System.out.println(tSet); // [ㄱ, ㄴ, ㄷ] <- 중복x 정렬o

    }

    public void method03(){

        HashSet<Dog> hSet = new HashSet<Dog>();
        hSet.add(new Dog("풍이",10));
        hSet.add(new Dog("해피",10));
        hSet.add(new Dog("해피",10));
        System.out.println(hSet); // [풍이(10.0kg), 해피(10.0kg)]
// equals(),hashCode() 주석처리 하면, 중복 저장o
        System.out.println(hSet); // [해피(10.0kg), 해피(10.0kg), 풍이(10.0kg)]

        // HashSet
//        HashSet<Dog> hSet = new HashSet<Dog>();
        hSet.add(new Dog("풍이",10));
        hSet.add(new Dog("해피",10));
        hSet.add(new Dog("해피",10));
        System.out.println(hSet); // [풍이(10.0kg), 해피(10.0kg)]
// equals(),hashCode() 주석처리 하면, 중복 저장o
        System.out.println(hSet); // [해피(10.0kg), 해피(10.0kg), 풍이(10.0kg)]


        System.out.println(hSet.contains(new Dog("풍이",10))); // true
// contains는 equals(), hashCode() 둘 다 필요

        boolean isTrue = false; // flag variable 플래그 변수
        isTrue = hSet.contains(new Dog("해피",10));
        System.out.println(isTrue); // false

        hSet.remove(new Dog("해피",10));
        System.out.println(hSet); // [풍이(10.0kg)]
        System.out.println(hSet.size()); // 1
// 변수에 넣어서 재사용성 늘리기
        int num = hSet.size();
        System.out.println(num); // 1
        isTrue=hSet.equals(new Dog("해피",10));
        System.out.println(isTrue); // false
        isTrue = hSet.equals(new Dog("풍이",10));
        System.out.println(isTrue); // false

        System.out.println(hSet.equals(new Dog("풍이",10)));
// 순서x 중복x HashSet, add하면 어디에 추가될까?
// 맨 앞에 추가됨

// set 중복 저장안되는데 중복 저장 되는 이유 : equals() 오버라이딩 안되어있어서
        HashSet<String> hSetStr = new HashSet<>();
        hSetStr.add("리트리버");
        hSetStr.add("리트리버");
        hSetStr.add("진돗개");

        System.out.println(hSetStr);    // [리트리버, 진돗개]
        System.out.println(hSetStr.equals("강하게")); // false
        System.out.println(hSetStr); // [리트리버, 진돗개]
        System.out.println(hSetStr.equals("강하게")); // false

// 해당 메소드마다 ctrl+click으로 내부 로직이 뭘로 구성되어있는지 체크해보자
// HashSet 같은 경우 HashMap으로 구성되어있는 걸 알 수 있었다
// equals() 같은 경우에는 object 비교 후 instanceof로 Set인지 비교하기도 했음

    }


    public void method04(){
        // 공용 메소드들
        // add(E e):boolean
        // contains(Object o) : boolean
        // iterator() : Iterator<E>
        // remove(Object o) : boolean
        // size() : int
        // equals(Object o) : boolean
        // isEmpty() : boolean
        // clear() : void

        // HashSet
        HashSet<Dog> hSet = new HashSet<Dog>();
        hSet.add(new Dog("풍이",10));
        hSet.add(new Dog("해피",10));
        System.out.println(hSet);   // [풍이(10.0kg), 해피(10.0kg)]


        // 순서x 중복x HashSet, add하면 어디에 추가될까?
        // 위치는 랜덤
        hSet.add(new Dog("뚱이",10));
        System.out.println(hSet); // 마지막 추가 : [풍이(10.0kg), 해피(10.0kg), 뚱이(10.0kg)]
        hSet.add(new Dog("호야",10));
        System.out.println(hSet); // 중간 추가 : [풍이(10.0kg), 해피(10.0kg), 호야(10.0kg), 뚱이(10.0kg)]

        // set 중복 저장안되는데 중복 저장 되는 이유 : equals() 오버라이딩 안되어있어서
        hSet.add(new Dog("뚱이",10));
        System.out.println(hSet);              // [풍이(10.0kg), 해피(10.0kg), 호야(10.0kg), 뚱이(10.0kg)]
        // equals() 오버라이딩 주석처리하면 중복 가능 : [풍이(10.0kg), 해피(10.0kg), 호야(10.0kg), 뚱이(10.0kg), 뚱이(10.0kg)]

        // LinkedHashSet
        // 순서가 유지o 중복저장x 안되는 컬렉션
        LinkedHashSet<Dog> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.addAll(hSet);
        System.out.println("linkedHashSet : " +linkedHashSet); // [풍이(10.0kg), 해피(10.0kg), 호야(10.0kg), 뚱이(10.0kg)]

        // LinkedHashSet 순서가 있으니까 add로 추가하면 어디서부터 추가될까?
        // 뒤부터 추가
        linkedHashSet.add(new Dog("도리",10));
        linkedHashSet.add(new Dog("아리",10));
        System.out.println(linkedHashSet); // [풍이(10.0kg), 해피(10.0kg), 호야(10.0kg), 뚱이(10.0kg), 도리(10.0kg), 아리(10.0kg)]

        // contains()
        // 값 비교 메소드 필요
        System.out.println(linkedHashSet.contains(new Dog("풍이",10))); // true
        // 오버라이딩 메소드 주석처리하면 false

        // size() : int
        linkedHashSet.size();
        System.out.println(linkedHashSet.size()); // 6


        // remove(Object o) : boolean
        // removeAll(Collection<?> c ) : boolean
        // Collection<?> c는 해당 컬렉션 변수 통째로 넣는 걸 의미 : ex)HashSet의 변수명 hSet
        linkedHashSet.remove(new Dog("아리",10));
        System.out.println(linkedHashSet);
        linkedHashSet.removeAll(linkedHashSet); // [풍이(10.0kg), 해피(10.0kg), 호야(10.0kg), 뚱이(10.0kg), 도리(10.0kg)]
        System.out.println(linkedHashSet); // []

        // isEmpty() : boolean
        linkedHashSet.isEmpty();
        System.out.println(linkedHashSet.isEmpty()); // true

        // equals(Object o) : boolean
        System.out.println(hSet); // [풍이(10.0kg), 해피(10.0kg), 호야(10.0kg), 뚱이(10.0kg)]
        hSet.equals(new Dog("풍이",10));
        System.out.println(hSet.equals(new Dog("풍이",10))); // false


        // clear() : void
        hSet.clear();
        System.out.println(hSet); // []

        // 공용 메소드들
        // add(E e):boolean
        // contains(Object o) : boolean
        // iterator() : Iterator<E>
        // remove(Object o) : boolean
        // size() : int
        // equals(Object o) : boolean
        // isEmpty() : boolean
        // clear() : void


        // TreeSet
        // 정렬을 해주는 Set
        TreeSet<Dog> treeSet = new TreeSet<>();

        System.out.println(treeSet); // []
        treeSet.add(new Dog("멍뭉이",10));
        System.out.println(treeSet); // [멍뭉이(10.0kg)]
        treeSet.clone();
        System.out.println(treeSet); // [멍뭉이(10.0kg)]

        // remove(int index):E
        // remove()의 return은 삭제한 값을 돌려준다
        // set은 인덱스가 없으므로 위의 인덱스 번호로 지우는 것은 불가능. 따로 해당 메소드는 set과 map에는 없음
//        System.out.println(treeSet.remove(1)); Exception in thread "main" java.lang.ClassCastException


        // remove(Object o):boolean
        // 같은 데이터라면 앞에 있는거부터 삭제
        // equals()가 오버라이딩이 안되어 있어서 값 비교가 아니라 주소값 비교라 삭제 못한 것.
        System.out.println(treeSet.remove(new Dog("멍뭉이",10))); //  true
        System.out.println(treeSet); // []

        treeSet.add(new Dog("멍뭉이",15));
        System.out.println(treeSet);


        // Iterator<E>	iterator()
        Iterator<Dog> it = treeSet.iterator();
        System.out.println(it); // java.util.TreeMap$KeyIterator@1b6d3586

        System.out.println(it.equals(new Dog("멍뭉이",15))); // false
        // 왜 false일까...?
        while(it.hasNext()){
            Dog d = it.next();
            System.out.println("iterator : "+it); // iterator : java.util.TreeMap$KeyIterator@1b6d3586
//            System.out.println(it.next());  // Exception in thread "main" java.util.NoSuchElementException
            System.out.println("Dog d : "+d); // Dog d : 멍뭉이(15.0kg)
        }


//        인텔리제이가 자동생성 해준 이터레이터 코드
//        Iterator<Dog> iterator = new Iterator<Dog>() {
//            @Override
//            public boolean hasNext() {
//                return false;
//            }
//
//            @Override
//            public Dog next() {
//                return null;
//            }
//        };


        // hSet.set();
        // linkedHashSet.set();
        // treeSet.set();
        // 위의 셋 다 set(),get()가 없음 -> 인덱스 번호로 replace해주기 때문
        //      set(int index, E e) : 바꾸기 전 값 반환
        //      해당 인덱스 번호에 값  교체
        //      get(int index):E
        //      인덱스번호의 엘리먼트 값을 가져온다

        // contains(Object) : boolean
        System.out.println(treeSet.contains(new Dog("멍뭉이",15))); // true

        // clear():void
        treeSet.clear();
        System.out.println(treeSet); // []

        // isEmpty():boolean
        System.out.println(treeSet.isEmpty()); // true


    }


    public void method05(){
        // 공용 메소드들
        // add(E e):boolean
        // contains(Object o) : boolean
        // iterator() : Iterator<E>
        // remove(Object o) : boolean
        // size() : int
        // equals(Object o) : boolean
        // isEmpty() : boolean
        // clear() : void


        // HashSet

        HashSet<Dog> hSet = new HashSet<>();

        // 순서x 중복x HashSet, add하면 어디에 추가될까?
        // 위치 랜덤
        hSet.add(new Dog("ㄱ",10));
        hSet.add(new Dog("ㄴ",10));
        hSet.add(new Dog("ㄷ",10));
        hSet.add(new Dog("ㄷ",10));

        System.out.println(hSet); // [ㄷ(10.0kg), ㄴ(10.0kg), ㄱ(10.0kg)]
        // 순서x 중복x -> 추가시 랜덤

        // set 중복 저장안되는데 중복 저장 되는 이유 : equals() 오버라이딩 안되어있어서
        System.out.println(hSet); // [ㄷ(10.0kg), ㄷ(10.0kg), ㄴ(10.0kg), ㄱ(10.0kg)]

        System.out.println(hSet.size()); // 3
        System.out.println(hSet.contains(new Dog("ㄱ",10))); // true
        hSet.remove(new Dog("ㄷ",10));
        System.out.println(hSet); // [ㄴ(10.0kg), ㄱ(10.0kg)]
        System.out.println(hSet.remove(new Dog("ㄴ",10))); // true
        System.out.println(hSet);   // [ㄱ(10.0kg)]
        System.out.println(hSet.equals(new Dog("ㄱ",10))); // false

        hSet.removeAll(hSet);
        System.out.println(hSet); // []
        hSet.add(new Dog("ㄱ",10));
        hSet.clear();
        System.out.println(hSet);   // []
        System.out.println(hSet.isEmpty()); // true



        // LinkedHashSet
        // 순서가 유지o 중복저장x 안되는 컬렉션
        LinkedHashSet<Dog> lhSet = new LinkedHashSet<Dog>();
        lhSet.add(new Dog("a",10));
        lhSet.add(new Dog("b",10));
        lhSet.add(new Dog("c",10));

        lhSet.iterator();
        System.out.println(lhSet.iterator()); // java.util.LinkedHashMap$LinkedKeyIterator@1b6d3586



        // LinkedHashSet 순서가 있으니까 add로 추가하면 어디서부터 추가될까?
        // 뒤부터 추가
        lhSet.add(new Dog("d",10));
        System.out.println(lhSet);  // [a(10.0kg), b(10.0kg), c(10.0kg), d(10.0kg)]

        // 중복 저장이 안된 이유?
        // Dog클래스에 equals()를 오버라이딩 해줬기 때문에 내용비교가 가능하게 되었고
        // 이 때문에 같은 객체로 인지되었기 때문

        // Iterator<E>
        // Iterator : 내가 컬렉션에 저장된 element에 접근 가능케 하는 역할
        // iterator(): iterator<E>
        // set안에 접근 가능한 엘리먼트를 반환
        // Returns an iterator over the elements in this set.

        Iterator<Dog> it = lhSet.iterator();
        while(it.hasNext()){
            System.out.print(it.next()+" "); // a(10.0kg) b(10.0kg) c(10.0kg) d(10.0kg)
        }

        hSet.add(new Dog("a",10));
        System.out.println(hSet); // a(10.0kg)

        Iterator<Dog> it2 = hSet.iterator();
        while(it2.hasNext()){
            System.out.println(it2.next());
        }




    }




    public void method06(){

        LinkedHashSet<Dog> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(new Dog("ㄱ",10));
        linkedHashSet.add(new Dog("ㄴ",10));
        System.out.println(linkedHashSet); // [ㄱ(10.0kg), ㄴ(10.0kg)]


        // ListIterator<E>
        // Iterator인터페이스의 확장된 인터페이스로, 이터레이터와 달리 컬렉션 요소를 앞뒤 방향으로 탐색가능 및 컬렉션 요소 추가,제거,수정 가능
        ArrayList<Dog> al = new ArrayList<>();
        al.add(new Dog("aa",10));
        al.add(new Dog("bb",10));

        ListIterator<Dog> listIterator = al.listIterator();
        listIterator.add(new Dog("bb",10));
        System.out.println(listIterator); // java.util.ArrayList$ListItr@1b6d3586 -> 주소값이 나오므로 Arrays.toString 한번 찍어보자
//        System.out.println(Arrays.toString(listIterator)); // 배열이 아니라 에러뜸
        // iterator는 while문 써서 접근했어야했다...
        while(listIterator.hasNext()){
            System.out.println(listIterator.next()); // aa(10.0kg) bb(10.0kg)
        }
        while(listIterator.next()){ // next()에 boolean타입이 들어와야하는데 Dog가 들어와서 에러발생
            System.out.println(listIterator);
        }


        // Exception in thread "main" java.lang.ClassCastException:
        // java.util.LinkedHashMap$LinkedKeyIterator cannot be cast to java.util.ListIterator
        //      ArrayList의 값을 가져왔어야하는건데 LinkedHashSet의 값을 가져와버림

    }

    public void method07(){



    }

    public void method08(){



    }


    // 공용 메소드들
    // add(E e):boolean
    // contains(Object o) : boolean
    // iterator() : Iterator<E>
    // remove(Object o) : boolean
    // size() : int
    // equals(Object o) : boolean
    // isEmpty() : boolean
    // clear() : void


    // HashSet

    // 순서x 중복x HashSet, add하면 어디에 추가될까?
    // 위치 랜덤

    // set 중복 저장안되는데 중복 저장 되는 이유 : equals() 오버라이딩 안되어있어서


    // LinkedHashSet
    // 순서가 유지o 중복저장x 안되는 컬렉션

    // LinkedHashSet 순서가 있으니까 add로 추가하면 어디서부터 추가될까?
    // 뒤부터 추가


    // 중복 저장이 안된 이유?
    // Dog클래스에 equals()를 오버라이딩 해줬기 때문에 내용비교가 가능하게 되었고
    // 이 때문에 같은 객체로 인지되었기 때문

    // Iterator<E>
    // Iterator : 내가 컬렉션에 저장된 element에 접근 가능케 하는 역할
    // iterator(): iterator<E>
    // set안에 접근 가능한 엘리먼트를 반환
    // Returns an iterator over the elements in this set.

    // Iterator<E>	iterator()


    // HashSet<String>
    // equals() 오버라이딩 필요x
    // equals() 메소드 주석처리함


    // TreeSet
    // 정렬을 해주는 Set



    // equals() 주석 처리후에는 중복가능해짐
    // equals() 주석 풀고 난 후에는 중복x



    // addAll
    // HashSet<String>은 객체도 받음
    //      <-> ArrayList<String>은 에러났었음
    // 순서 없어서 가운데에도 추가되고 앞에 추가되기도 하고...


    // add(E e):boolean
    //     // addAll(Collection<? extends E> c) : boolean


    // .size() : 인덱스 길이 반환

    // remove(int index):E
    // remove()의 return은 삭제한 값을 돌려준다
    // set은 인덱스가 없으므로 위의 인덱스 번호로 지우는 것은 불가능. 따로 해당 메소드는 set과 map에는 없음



    // remove(Object o):boolean
    // 같은 데이터라면 앞에 있는거부터 삭제
    // equals()가 오버라이딩이 안되어 있어서 값 비교가 아니라 주소값 비교라 삭제 못한 것.

    // list에 있는 element에 하나씩 접근하고 싶을 때 for문 이용 가능. 인덱스가 존재했기 때문
//		for(int i=0, i<list.size();i++) {
//			list.get(i);
//		}
    // set에는 인덱스 존재x -> list와 같은 방법으로 하나씩 element에 접근 불가능.
    // 인덱스가 없기 때문에 for문 같은 것도 불가능하며 Iterator를 사용해야함
    // set에서는 Iterator 사용

    // Iterator<E>
    // Iterator : 내가 컬렉션에 저장된 element에 접근 가능케 하는 역할
    // iterator(): iterator<E>
    // set안에 접근 가능한 엘리먼트를 반환
    // Returns an iterator over the elements in this set.

    // Iterator<E>	iterator()


    // removeAll(Collection<?> c) : boolean
    // Removes from this set all of its elements that are contained in the specified collection (optional operation).

    // equals(Object o) :  boolean
    //Compares the specified object with this set for equality.

    // hSet.set();
    // linkedHashSet.set();
    // treeSet.set();
    // 위의 셋 다 set(),get()가 없음 -> 인덱스 번호로 replace해주기 때문
    //      set(int index, E e) : 바꾸기 전 값 반환
    //      해당 인덱스 번호에 값 교체
    //      get(int index):E
    //      인덱스번호의 엘리먼트 값을 가져온다

    // contains(Object) : boolean


    // clear():void
    // isEmpty():boolean



}