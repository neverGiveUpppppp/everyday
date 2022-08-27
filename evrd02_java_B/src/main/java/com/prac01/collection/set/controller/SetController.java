package com.prac01.collection.set.controller;

import com.prac01.collection.set.model.vo.Dog;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class SetController {

    // Collection Set

    // Set
    // 순서x 중복저장x
    // key로 저장
    // key에 null 저장불가능

    // HashSet

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
         HashSet<String>은 객체도 받음
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

    // add(E e):boolean
    // add(Object e):boolean


    // LinkedHashSet
    // 순서가 유지o 중복저장x 안되는 컬렉션
    // 중복 저장이 안된 이유?
    // Dog클래스에 equals()를 오버라이딩 해줬기 때문에 내용비교가 가능하게 되었고
    // 이 때문에 같은 객체로 인지되었기 때문

    // HashSet

    // TreeSet
    // 정렬을 해주는 Set


    // 전체데이터 넣는 방법
    // 1) addAll()
    // 2) 생성자 매개변수 이용


}
