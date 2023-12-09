package com.group.libraryapp.prac4.dto;

import com.group.libraryapp.prac4.domain.User2;
import lombok.Getter;

@Getter
public class User2Response {

    private Long id;
    private String name;
    private Integer age;

    public User2Response(User2 user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
    }
}
