package com.group.libraryapp.prac4.service;


import com.group.libraryapp.prac4.dto.BookReturnReq;
import com.group.libraryapp.prac4.repository.Book2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Book2ServiceImpl implements Book2Service {

    private final Book2Repository bookRepository;
//    private final User

    @Autowired
    public Book2ServiceImpl(Book2Repository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public void bookReturn(BookReturnReq request) {
        // 해당 유저와 북이 있는 지 확인
        // 있다면 책 반환하기

    }

}
