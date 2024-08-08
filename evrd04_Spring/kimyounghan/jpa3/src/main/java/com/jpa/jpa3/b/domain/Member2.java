package com.jpa.jpa3.b.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member2 {

    @Id @GeneratedValue
    private long id;

    private String name;

    @Embedded
    private Address2 address;

    @OneToMany(mappedBy = "member")
    private List<Order2> orders = new ArrayList<>();

}

