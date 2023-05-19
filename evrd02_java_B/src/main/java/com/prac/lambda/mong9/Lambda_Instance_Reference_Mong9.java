package com.prac.lambda;




import com.prac.lambda.mong9.Member;

import java.util.List;
import java.util.stream.Collectors;


/*

람다식에서 메서드참조/생성자 참조 사용법
https://mong9data.tistory.com/130

 */

public class Lambda_Instance_Reference_Mong9 {
    public static void main(String[] args) {

        List<String> memberNameList = List.of("A","B","C");
        List<Member> memberList = memberNameList.stream().map(name -> new Member(name)).collect(Collectors.toList());
        memberList.forEach(member -> System.out.println(member.getName()));
    
        // 일반 람다식
        memberList = memberNameList.stream().map(name -> new Member(name)).collect(Collectors.toList());
        // 메서드 참조 표현식
        memberList = memberNameList.stream().map(Member::new).collect(Collectors.toList());


/*  memberList를 초기화 할 때 stream 생성 이후 map메서드에서 람다식을 사용
    이때 map에 걸리는 요소들은 String객체지만 Member클래스의 인스턴스를 만들 때 인자로 들어감
    이때도 메서드 참조 표현식 가능

    // 일반 람다식
    List<Member> memberList = memberNameList.stream().map(name -> new Member(name)).collect(Collectors.toList());
    // 메서드 참조 표현식
    List<Member> memberList = memberNameList.stream().map(Member::new).collect(Collectors.toList());



 */

    }

}
