package com.group.libraryapp.prac3.domain.user;

import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    private String name;
    private Integer age;


    protected User() {

    }


    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User(String name, Integer age) {
        if (name == null || name.isBlank()) { // name 값이 필수이기 때문에 유효성 체크
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.",name));
        }
        this.name = name;
        this.age = age;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }


    public void updateUser(String name) {
        this.name = name;
    }


}