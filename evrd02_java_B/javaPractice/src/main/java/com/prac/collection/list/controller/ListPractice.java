package com.prac.collection.list.controller;

import com.prac.collection.list.model.vo.Student;
import com.prac.collection.list.model.vo.pModelVo05;

import javax.xml.stream.events.StartDocument;
import java.util.ArrayList;
import java.util.ListIterator;

public class ListPractice {

    public void method01(){

        ArrayList<Student> al = new ArrayList<>();
        // 1-1. // add(E e):boolean
        al.add(new Student("a",10));
        al.add(new Student("b",10));
        System.out.println(al); // [a(10점), b(10점)]

        ArrayList<Student> al2 = new ArrayList<>(al); // shallow copy
        System.out.println(al2); // [a(10점), b(10점)]

        // 1-2. add(int index, E element):void
        al.add(2,new Student("c",10));
        System.out.println(al); // [a(10점), b(10점), c(10점)]

        // 2. size()
        System.out.println(al.size()); // 3

        // 3.remove()
        // remove(int index):E
        // remove(Object o):boolean
        al2.remove(1);
        System.out.println(al2); // [a(10점)]
        System.out.println(al2.remove(0)); // a(10점) <- 지운 객체 반환
        System.out.println(al2); // []

        al2.addAll(al);

        // 4. set()
        // set(int index, E e):E
        // == replace 대체
        al2.set(2,new Student("d",10));
        System.out.println(al2); // [a(10점), b(10점), d(10점)]
        System.out.println(al2.set(2, new Student("e",10))); // d(10점) <- 교체한 객체
        System.out.println(al2); // [a(10점), b(10점), e(10점)]
        System.out.println();


        // 5. get()
        // get(int index):E
        System.out.println(al.get(1)); // b(10점)

        System.out.println();
        for(Student a : al){
            System.out.println(a); // a(10점) b(10점) c(10점)
        }
        for(Student a : al){
            System.out.println(al);
//            [a(10점), b(10점), c(10점)]
//            [a(10점), b(10점), c(10점)]
//            [a(10점), b(10점), c(10점)]
        }


        // 6. contains(Object) : boolean
        // 해당 객체를 포함한지 true/false반환

        System.out.println(al.contains(new Student("a",10))); // true
        if(al.contains(new Student("a",10))){
            System.out.println("al안에 a가 있으면 출력하기"); // al안에 a가 있으면 출력하기
        }


        // 7. indexOf(Object o): int
        // 해당 객체의 인덱스번호 반환
        // 해당 값이 없을경우 -1 반환
        System.out.println(al.indexOf(new Student("-c",10))); // -1
        System.out.println(al.indexOf(new Student("c",10))); // 2


        // 8. equals(Object o):boolean
        // 지정된 객체와 목록이 같은지 비교

        System.out.println(al.equals(new Student("a",10))); // false


        // 9. clear()
        // clear():void
        al.clear();
        System.out.println(al); // []

        // 10. isEmpty():boolean
        al.isEmpty();
        System.out.println(al); // []


    }
    public void method02(){
        ArrayList<Student> al = new ArrayList<>(5);

        System.out.println(al.size()); // 0

        for(int i=0; i< 5; i++ ){
           al.add(new Student("a",i));
        }
        System.out.println(al); // [a(0점), a(1점), a(2점), a(3점), a(4점)]
        System.out.println();

        for(Student a : al){
            System.out.println(al);
            // 변수 al 전체를 al의 길이만큼 반복
//            [a(0점), a(1점), a(2점), a(3점), a(4점)]
//            [a(0점), a(1점), a(2점), a(3점), a(4점)]
//            [a(0점), a(1점), a(2점), a(3점), a(4점)]
//            [a(0점), a(1점), a(2점), a(3점), a(4점)]
//            [a(0점), a(1점), a(2점), a(3점), a(4점)]
        }
        for(Student a : al){
            // 변수 al의 요소 하나씩 순서대로 al길이만큼 반복
            System.out.println(a);
//            a(0점)
//            a(1점)
//            a(2점)
//            a(3점)
//            a(4점)
        }

        // ListIterator
        // iterator()
        ListIterator<Student> li = al.listIterator();
        System.out.println(li); // java.util.ArrayList$ListItr@1b6d3586


        // ListIterator요소 꺼내는 방법 : while + hasNext()
        while(li.hasNext()){
            System.out.print(li.next()+" "); // a(0점) a(1점) a(2점) a(3점) a(4점)
        }
        // hasNext() 두번째 돌리면 처음부터 다시 가는게 아니라 이미 끝에 있기에 의미없음
        while(li.hasNext()){
            System.out.print(li.next()+" "); // 출력x
        }
        System.out.println();
        while(li.hasPrevious()){
            System.out.print(li.previous()+" "); // a(4점) a(3점) a(2점) a(1점) a(0점)
        }
        System.out.println();
        // hasPrevious()로 앞으로 돌렸기에 기준점이 앞으로 와있어 hasNext() 다시 작동
        while(li.hasNext()){
            System.out.print(li.next()+" "); // a(0점) a(1점) a(2점) a(3점) a(4점) 
        }
        System.out.println();
        System.out.println();

//        al.add(new Student("a",5));
//        al.add(new Student("a",6));

        // 해당 변수의 1번 인덱스부터 받아오기
        ListIterator<Student> li2 = al.listIterator(1);
        li2.add(new Student("a",5));
        li2.add(new Student("a",6));

        while(li2.hasNext()){
            System.out.print(li2.next()+" "); // a(1점) a(2점) a(3점) a(4점)
        }


//        // 컬렉션 원소 삭제 : iterator() + remove()
//        다시 해볼 것 : https://park0729.tistory.com/4
//        Student del = null;
//        while(li2.hasNext()){
//            System.out.print(li2.next()+" "); // (1점) a(2점) a(3점) a(4점) a(5점) a(6점)
//
//            del = new Student("a",1);
//
////            if(del.equals(new Student("a",1))){
////                li2.remove();
////            }
//            if(del == new Student("a",1)){
//                li2.remove();
//            }
//        }
//        System.out.println(del);




    }

    // ListIterator
    // iterator()
    // 컬렉션 원소 삭제 : iterator() + remove()


    // 1-1. // add(E e):boolean

    // 1-2. add(int index, E element):void

    // 2. size()


    // 3.remove()
    // remove(int index):E
    // remove(Object o):boolean

    // 모델 오버라이딩 후 			// [쌀[50000원], 계란[6000원]]

    // 4. set()
    // set(int index, E e):E
    // == replace 대체


    // 5. get()
    // get(int index):E

    // 6. contains(Object) : boolean
    // 해당 객체를 포함한지 true/false반환


    // 7. indexOf(Object o): int
    // 해당 객체의 인덱스번호 반환
    // 해당 값이 없을경우 -1 반환


    // 8. equals(Object o):boolean
    // 지정된 객체와 목록이 같은지 비교



    // 9. clear()
    // clear():void

    // 10. isEmpty():boolean


}
