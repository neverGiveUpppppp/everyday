package com.group.libraryapp.service.book;


import com.group.libraryapp.prac2.Repository.BookRepository;
import com.group.libraryapp.prac2.domain.book.Book;
import com.group.libraryapp.prac2.dto.request.BookCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Transactional
    public void createBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

}
