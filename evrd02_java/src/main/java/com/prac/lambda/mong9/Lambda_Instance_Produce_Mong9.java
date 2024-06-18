package com.prac.lambda;





import com.prac.lambda.mong9.Member;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*

람다식에서 메서드참조/생성자 참조 사용법
https://mong9data.tistory.com/130

 */
public class Lambda_Instance_Produce_Mong9 {
    public static void main(String[] args) {
        List<Member> memberList = Arrays.asList(new Member("A"), new Member("B"), new Member("C"));
        String collect = memberList.stream().map(element -> element.getName()).collect(Collectors.joining());
        System.out.println(collect);

        // 일반 람다식
        collect = memberList.stream().map(element -> element.getName()).collect(Collectors.joining());

        // 메서드 참조 표현식
        collect = memberList.stream().map(Member::getName).collect(Collectors.joining());

/*

        // 일반 람다식
        String collect = memberList.stream().map(element -> element.getName()).collect(Collectors.joining());

        // 메서드 참조 표현식
        String collect = memberList.stream().map(Member::getName).collect(Collectors.joining());


        // 일반 람다식
        (인스턴스 -> 인스턴스.메서드명)

        //메서드 참조 표현식
        (인스턴스의 클래스명::메서드명)

 */
//        public static class Member{
//            private String name;
//
//            public Member(String name){
//                this.name = name;
//            }
//            public String getName(){
//                return name;
//            }
//        }


    }
}
