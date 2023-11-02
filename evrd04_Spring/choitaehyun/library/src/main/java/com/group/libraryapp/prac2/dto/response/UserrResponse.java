package com.group.libraryapp.prac2.dto.response;

import com.group.libraryapp.prac2.domain.Userr;

public class UserrResponse {
    private Long id;
    private String name;
    private Integer age;

    public UserrResponse(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public UserrResponse(Userr user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
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
}
