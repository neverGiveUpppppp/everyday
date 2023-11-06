package com.group.libraryapp.prac2.Controller;

import com.group.libraryapp.prac2.dto.request.BookCreateRequest;
import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping("/book")
    public void createBook(@RequestBody BookCreateRequest request) {
        bookService.createBook(request);
    }
}
