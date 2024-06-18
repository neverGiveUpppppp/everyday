package com.prac.collection.list.controller;





/*
목차
    1.ArrayList methods
        1)add
        2)get
        3)size
        4)contains
        5)remove
    2.제네릭스(Generics)
    3.다양한 방법으로 ArrayList 만들기
    4.String.join
    5.ArrayList.sort() : 리스트 정렬하기


Array와 List의 차이
    array : 크기 고정
    list : 크기 동적

List 자료형
List 인터페이스를 구현한 자료형
    종류 : ArrayList, Vector, LinkedList 등


 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class ArrayList_Method{
    public static void main(String[] args) {
        ArrayList pitches = new ArrayList();    // 제네릭스 없는 ArrayList에는 Object타입으로 인식됨
// 1.ArrayList methods
        // 1) add
        pitches.add("138");
        pitches.add(0,"129");
        pitches.add(1,"142");

        // 2) get
        pitches.get(1);
        System.out.println(pitches.get(1)); // 142

        // 3) size
        pitches.size();
        System.out.println(pitches.size()); // 3

        // 4) contains
        pitches.contains("142");
        System.out.println(pitches.contains("142")); // true

        // 5) remove
        //      a.remove(객체)
        //      b.remove(인덱스)
        pitches.remove(0);
        pitches.remove("142");
        System.out.println(pitches.remove("129"));



// 2. 제네릭스(Generics)
        // 필요성 : 컬렉션에 모든 타입이 들어갈 수 있는 게 장점이지만, 그렇기에 자료형을 매번 확인해야하고 헷갈릴 수 있음
        //          잘못된 형변환으로 인한 오류 발생 가능성
        //              따라서, 타입이 강제되어지는 기능이 필요
        ArrayList generics_object = new ArrayList();
        generics_object.add("vector");
        generics_object.add("LinkedList");

//        String one = pitches.get(0);    // 컴파일 에러 발생 - 제네릭을 안 쓴 ArrayList는 Object타입으로 들어감
                                          // Required type: String
                                          // Provided: Object       
        String one = (String) pitches.get(0);
        String two = (String) pitches.get(1);
    }
}


class Array_to_ArrayList{
    public static void main(String[] args) {
        
        // 배열(array)에서 ArrayList로 변환하기 : 변수명 사용
        String[] data = {"138","129","142"};
        ArrayList<String> array1 = new ArrayList<>(Arrays.asList(data));
        System.out.println(array1); // [138, 129, 142] 출력

        // 배열(array)에서 ArrayList로 변환하기 : 배열값 직접삽입
        ArrayList<String> array2 = new ArrayList<>(Arrays.asList("138","129","142")); // 배열의 소괄호 필요없이 각 속성값만

        // ArrayList를 String값으로 빼기
        String rslt = "";           // loop돌려서 값을 받을 변수 지정
        for(int i = 0; i < array2.size();i++){
            rslt += array2.get(i);  // 실제 값 넣기
            rslt += ",";            //  구분자 넣기
        }
        rslt = rslt.substring(0, rslt.length()-1); // 마지막 값인 ,는 빼고 출력
        System.out.println(rslt);   // 138,129,142
                    // StringBuilder도 위와 똑같이 가능
                    StringBuilder sb = new StringBuilder();
                    sb.substring(0, sb.length()-1);

        // String.join
        //      위의 코드를 간단하게
        // String.join("구분자", 리스트객체) : 리스트의 각 요소에 "구분자"를 삽입하여 하나의 문자열로 만들기
        ArrayList<String> join = new ArrayList<>(Arrays.asList("138","129","142"));
        String result = String.join(",", join);
        System.out.println("String.join : " + result); // "138","129","142"

        // 배열에도 사용가능
        String[] join_array = new String[]{"138", "129", "142"};    // 배열 값 이렇게도 넣기 가능 : new String[]{"138", "129", "142"}
        String rslt2 = String.join(",", join_array);    // String.join()에 배열값을 넣어서 사용
        System.out.println(rslt2);  // 138,129,142 출력


    }
}


class List_Sort{
    public static void main(String[] args) {

        // sort() : 정렬
        //      sort 메서드에는 정렬기준을 파라미터로 전달해야함 //  Java8 ↑
        //      ex) 변수.sort( Comparator.naturalOrder())
        //      오름차순(순방향) 정렬 : Comparator.naturalOrder()
        //      내림차순(역방향) 정렬 : Comparator.reverseOrder()
        ArrayList<String> list_sort = new ArrayList<>(Arrays.asList("138", "129", "142"));
        list_sort.sort(Comparator.naturalOrder()); // ASC 정렬

        System.out.println(list_sort);  // [129, 138, 142]

    }
}


public class List_JumpToJava {



}





