package com.prac.collection.list.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ListPractice2 {
    public static void main(String[] args) {
        ListPractice2 lp = new ListPractice2();
        lp.list_methods();
        lp.generic();
        lp.asList();
        lp.String_join();
        lp.ArrayList_sort();



    }

    public void list_methods(){
        ArrayList list = new ArrayList<>();
        // 1)add()
        list.add(1);
        list.add("String");
        list.add("제네릭 지정안하면 object타입으로 저장");

        // 2)get()
        list.get(1);
        list.get(2);
        System.out.println(list.get(1));
        System.out.println(list.get(2));

        // 3)size()
        list.size();
        System.out.println(list.size());

        // 4)contains()
        list.contains("String");
        System.out.println(list.contains("String"));

        // 5)remove(int or obj)
        list.remove(1);
        list.remove("String");
        System.out.println(list.remove(1));
    }

    public void generic(){
        ArrayList list = new ArrayList();
        list.add("vector");
        list.add("LinkedList");

//        String one1 = list.get(0); // 컴파일 에러 : required는 String provided는 obj
//        String two2 = list.get(1);  //          따라서 파싱이 필요함

        String one2 = (String)list.get(0);
        String two2 = (String)list.get(1);
    }

    public void asList(){
        // 배열 to ArrayList 변환
        //      방법1)변수로 넣기
        String[] array = new String[]{"a","b","c"};
        ArrayList<String> list = new ArrayList<>(Arrays.asList(array));

        System.out.println(list); // [a, b, c]
        // list객체 print하면 객체주소 나오지 않았나? 그건 배열임
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("a");
        System.out.println(list2);  // [a]


        //      방법2)직접 배열값 넣기
        ArrayList<String> list3 = new ArrayList<>(Arrays.asList("a,b"));
        System.out.println(list3);  // [a,b]

        // ArrayList타입인 [a,b]를 String 타입으로 값 빼오기
        String rslt = "";   // loop돌려서 값을 받을 변수지정
        for(int i = 0; i < list3.size(); i++){
            rslt += rslt + list3.get(i);
            rslt += ",";        //  구분자 넣기
        }
        String answer = rslt.substring(0, rslt.length()-1); // 마지막 값인 ,는 빼고 출력
        System.out.println(rslt); // a,b
    }


    public void String_join(){
        // ArrayList에 사용
        ArrayList<String> join1 = new ArrayList<>(Arrays.asList("1,2,3"));
        String rslt = String.join(", ", join1);
        System.out.println("String.join : " + rslt); // String.join : 1,2,3


        // Array에 사용
        String[] join2 = new String[]{"String","join"};
        String result = String.join(" ", join2);
    }

    public void ArrayList_sort(){
        // sort() : 정렬
        //      sort 메서드에는 정렬기준을 파라미터로 전달해야함 //  Java8 ↑
        //      오름차순(순방향) 정렬 : Comparator.naturalOrder()
        //      내림차순(역방향) 정렬 : Comparator.reverseOrder()
        ArrayList<String> list = new ArrayList<>(Arrays.asList("3","2","4","1","5"));
        list.sort(Comparator.naturalOrder());   // [1, 2, 3, 4, 5]
        list.sort(Comparator.reverseOrder());

        list.sort(Comparator.naturalOrder());
        list.sort(Comparator.reverseOrder());

        System.out.println(list);
    }



}




