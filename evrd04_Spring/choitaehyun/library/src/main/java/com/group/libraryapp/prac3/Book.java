package com.group.libraryapp.prac3;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @Column(nullable = false)
    private String bookName;

    protected Book(){}

    public Book(Long id, String bookName) {
        this.id = id;
        this.bookName = bookName;
    }



}
