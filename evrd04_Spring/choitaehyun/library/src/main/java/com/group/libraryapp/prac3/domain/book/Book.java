package com.group.libraryapp.prac3.domain.book;


import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(length = 255)
    private String bookName;


    protected Book() {
    }

    public Book(String bookName) {
    }


}
