package com.group.libraryapp.prac3.controller;

import com.group.libraryapp.prac3.dto.book.request.BookCreateRequest;
import com.group.libraryapp.prac3.dto.book.request.BookLoanRequest;
import com.group.libraryapp.prac3.dto.book.request.BookReturnRequest;
import com.group.libraryapp.prac3.dto.book.request.BookReturnRequest2;
import com.group.libraryapp.prac3.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public void saveBook(@RequestBody BookCreateRequest request) {
        bookService.saveBook(request);
    }

    @PostMapping("/book/loan")
    public void loanBook(@RequestBody BookLoanRequest request) {
        bookService.loanBook(request);
    }

    @PutMapping("/book/return")
    public void returnBook(@RequestBody BookReturnRequest request) {
        bookService.returnBook(request);
    }


    @PutMapping("/book/return")
    public void returnBook2(@RequestBody BookReturnRequest2 request2){
        bookService.returnBook2(request2);
    }


}
