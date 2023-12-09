package com.group.libraryapp.prac4.controller;

import com.group.libraryapp.prac4.dto.BookReturnReq;
import com.group.libraryapp.prac4.service.Book2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.PostUpdate;

@RestController
public class Book2Controller {
    private final Book2Service bookService;

    @Autowired
    public Book2Controller(Book2Service bookService) {
        this.bookService = bookService;
    }

    @PutMapping("/book/return")
    public void bookReturn(@RequestBody BookReturnReq request) {
        bookService.bookReturn(request);
    }



}
