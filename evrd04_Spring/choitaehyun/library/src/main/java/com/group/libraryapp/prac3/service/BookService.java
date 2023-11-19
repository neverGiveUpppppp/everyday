package com.group.libraryapp.prac3.service;

import com.group.libraryapp.prac3.domain.book.Book;
import com.group.libraryapp.prac3.domain.user.loanHistory.UserLoanHistory;
import com.group.libraryapp.prac3.dto.book.request.BookCreateRequest;
import com.group.libraryapp.prac3.dto.book.request.BookLoanRequest;
import com.group.libraryapp.prac3.repository.BookRepository;
import com.group.libraryapp.prac3.repository.UserLoanHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        Book book = bookRepository.findByBookName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        if(book == null)
            throw new IllegalArgumentException();
        bookRepository.save(new Book(request.getBookName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        Book book = bookRepository.findByBookName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        boolean isLoan = userLoanHistoryRepository.existByBookNameAndIsReturn(book.getBookName(), false);
        if(isLoan)
            throw new IllegalArgumentException("대출 중인 책입니다.");
        // 유저정보 가져와야함

    }




}

