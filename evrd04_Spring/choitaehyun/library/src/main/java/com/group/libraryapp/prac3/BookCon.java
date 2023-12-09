package com.group.libraryapp.prac3;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController("book")
public class BookCon {

    private final BookServ bookServ;

    public BookCon(BookServ bookServ) {
        this.bookServ = bookServ;
    }

    @PutMapping("/book/return")
    public void returnBook(BookReturnRequest request) {
        bookServ.bookReturn(request);
    }

    @PostMapping("/book/loan/prac")
    public void loanBook(BookLoanRequest request) {
        bookServ.loanBook(request);
    }


}
