package com.group.libraryapp.prac4.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class User2 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(name = "user2", length = 255, nullable = false)
    private String name;

    private Integer age;


    public User2(){
    }

    public User2(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User2(User2 user2) { // 객체를 넣으므로써 메소드참조 사용 가능해짐 & 파라미터로 일일히 안넣어도 됨
        this.name = user2.getName();
        this.age = user2.getAge();
    }



    // 생성자, 객체로 받기
//    public User(User user) { // 파라미터 하나씩 받는 대신 User 객체 통으로 받음. 심플해지고, 메소드참조 사용 가능해짐
//        if(user.name == null || user.name.isBlank())
//            throw new IllegalArgumentException(String.format("잘못된 name(%s)값이 들어왔습니다.", name));
//        this.name = user.getName();
//        this.age = user.getAge();
//    }



}
