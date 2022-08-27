package com.prac01.collection.set.controller;

import com.prac01.collection.set.model.vo.Dog;

import java.util.HashSet;

public class SetController {

    // Collection Set

    // Set
    // 순서x 중복저장x
    // key로 저장
    // key에 null 저장불가능

    public void method01() {
        HashSet set0 = new HashSet();
        HashSet<Dog> set1 = new HashSet<>(); // 뒤 제네릭 생략 → 타입추론 : 뒤 생략가능하다는 의미


        // add(E e):boolean
        set1.add(new Dog("a",10));
        set1.add(new Dog("b",10));

        System.out.println(set1);   // [b 10.0kg, a 10.0kg]

        // set 중복 저장안되는데 중복 저장 되는 이유 : equals() 오버라이딩 안되어있어서
        set1.add(new Dog("a",10));
        System.out.println(set1);

        // add(Object e):boolean
        Dog puppy = new Dog("c",10);
        set1.add(puppy);
        System.out.println(set1);


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
