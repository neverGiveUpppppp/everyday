package com.group.libraryapp.prac3.service;

import com.group.libraryapp.prac3.domain.book.Book;
import com.group.libraryapp.prac3.dto.book.request.BookCreateRequest;
import com.group.libraryapp.prac3.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        Book book = bookRepository.findByBookName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        if(book == null)
            throw new IllegalArgumentException();
        bookRepository.save(new Book(request.getBookName()));
    }


}

