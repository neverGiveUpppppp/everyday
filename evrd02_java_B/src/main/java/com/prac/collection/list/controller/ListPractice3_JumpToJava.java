package com.prac.collection.list.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ListPractice3_JumpToJava {
    public static void main(String[] args) {
        ListPractice3_JumpToJava lp = new ListPractice3_JumpToJava();
        lp.list_methods();
        lp.generic();
        lp.asList();
        lp.String_join();
        lp.ArrayList_sort();

    }



    public void list_methods(){
        ArrayList list = new ArrayList();
        list.add("find");
        list.add("search");
        System.out.println(list); // [find, search]

        list.contains("find");
        System.out.println(list.contains("find"));  // true
        list.remove(1);
        list.remove("find");

        list.indexOf(1);
        System.out.println(list.indexOf(1)); // -1
    }

    public void generic(){
        ArrayList list = new ArrayList<>();
        list.add("Vector");
        list.add("LinkedList");

        String one1 = (String)list.get(0);

        String two1 = (String)(list.get(1));
        String two2 = String.valueOf(list.get(1));

        System.out.println(one1);
        System.out.println(two1);
        System.out.println(two2);
    }


    public void asList(){
        // Array to ArrayList
        //      방법1:변수로 넣기
        String [] array = new String[]{"ps","-ef","tomcat"};
        ArrayList<String> list = new ArrayList<>(Arrays.asList(array));

        System.out.println(list);
        list.add("1");

        //      방법2:직접 배열값 넣기
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList("ps","-ef","java"));
        System.out.println(list2);

        // ArrayList 타입인 list 내부값을 String타입으로 빼오기
        String rslt = "";
        for(int i = 0; i < list2.size(); i++){
            rslt += rslt + list2.get(i);
            rslt += ",";
        }

        String answer = rslt.substring(0, rslt.length()-1);
        System.out.println(answer);

        // StringBuilder도 위와 똑같이 가능
        StringBuilder sb = new StringBuilder(rslt);
        sb.substring(0, sb.length()-1);

    }

    public void String_join() {
        // String class join()
        // 1.ArrayList에 사용
        ArrayList<String> strJoin = new ArrayList<>(Arrays.asList("grep","-i","대소문자"));
        String rslt = String.join(",",strJoin);

        System.out.println("String.join() : " + rslt);

        
        // 2.Array에 사용
        String[] join2 = new String[]{"grep","-r","하위디렉토리 파일까지 모두 출력"};
        String ans1 = String.join(" ", join2);
        String ans2 = String.join(" + ", join2);

        System.out.println(Arrays.toString(join2)); // [grep, -r, 하위디렉토리 파일까지 모두 출력]
        System.out.println(ans1); // grep -r 하위디렉토리 파일까지 모두 출력
        System.out.println(ans2); // grep + -r + 하위디렉토리 파일까지 모두 출력
    }

    public void ArrayList_sort(){
        // sort() : 파라미터로 정렬기준을 넣어줘야함 (java8이상)
        //      오름차순 Comparator.naturalOrder()
        //      내림차순 Comparator.reverseOrder()
        ArrayList<String> list = new ArrayList<>(Arrays.asList("grep", "ps", "sudo", "find"));

        list.sort(Comparator.reverseOrder());  // [find, grep, ps, sudo]
        list.sort(Comparator.naturalOrder());  // [sudo, ps, grep, find]

        System.out.println(list);




    }


}
