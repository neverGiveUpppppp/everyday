package com.group.libraryapp.prac3.service;

import com.group.libraryapp.prac3.domain.book.Book;
import com.group.libraryapp.prac3.domain.user.User;
import com.group.libraryapp.prac3.domain.user.loanHistory.UserLoanHistory;
import com.group.libraryapp.prac3.dto.book.request.BookCreateRequest;
import com.group.libraryapp.prac3.dto.book.request.BookLoanRequest;
import com.group.libraryapp.prac3.dto.book.request.BookReturnRequest;
import com.group.libraryapp.prac3.dto.book.request.BookReturnRequest2;
import com.group.libraryapp.prac3.repository.BookRepository;
import com.group.libraryapp.prac3.repository.UserLoanHistoryRepository;
import com.group.libraryapp.prac3.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
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
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.loanBook(request.getBookName());
    }

    @Transactional
    public void returnBook(BookReturnRequest request) {
        // 1)userId를 찾아서 가져오기
        // 2)요구사항에 user 정보는 찾아왔으니 userId와 bookname으로 대출 기록 찾기
        // 3)대여가능 상태로 변경하기(책 반납)
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        UserLoanHistory userLoanHistory = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        userLoanHistory.doReturn();
        userLoanHistoryRepository.save(userLoanHistory);

    }

    @Transactional
    public void returnBook2(BookReturnRequest2 request2) {
//        User user = userRepository.findByName(request2.getUserName())
//                .orElseThrow(IllegalArgumentException::new);
//        UserLoanHistory userLoanHistory = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request2.getBookName())
//                .orElseThrow(IllegalArgumentException::new);
//        userLoanHistory.doReturn();
//        userLoanHistoryRepository.save(userLoanHistory);
        User user = userRepository.findByName(request2.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.returnBook(request2.getBookName());

    }

    @Transactional
    public void returnBook3(BookReturnRequest2 request){
        // 1)userId를 찾아서 가져오기
        // 2)요구사항에 user 정보는 찾아왔으니 userId와 bookname으로 대출 기록 찾기
        // 3)대여가능 상태로 변경하기(책 반납)
////        Optional<User> user = userRepository.findByName(request.getUserName());
//        User user = userRepository.findByName(request.getUserName())
//                .orElseThrow(IllegalArgumentException::new);
////        if(user.isPresent()) {
//            UserLoanHistory userLoanHistory = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
//                    .orElseThrow(IllegalArgumentException::new);
////        }
//        userLoanHistory.doReturn();
//        userLoanHistoryRepository.save(userLoanHistory);
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.returnBook3(request.getBookName());
    }


    public void returnBook4(BookReturnRequest2 request) {
        // 1)userId를 찾아서 가져오기
        // 2)요구사항에 user 정보는 찾아왔으니 userId와 bookname으로 대출 기록 찾기
        // 3)대여가능 상태로 변경하기(책 반납)
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        UserLoanHistory history = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        history.doReturn();
    }

    public void returnBook5(BookReturnRequest2 request2) {
//         1)userId를 찾아서 가져오기
//         2)요구사항에 user 정보는 찾아왔으니 userId와 bookname으로 대출 기록 찾기
//         3)대여가능 상태로 변경하기(책 반납)
        User user = userRepository.findByName(request2.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.returnBook5(request2.getBookName());
    }

    @Transactional
    public void returnBook6(BookReturnRequest2 request) {
        // 1)userId를 찾아서 가져오기
        // 2)요구사항에 user 정보는 찾아왔으니 userId와 bookname으로 대출 기록 찾기
        // 3)대여가능 상태로 변경하기(책 반납)
//        Optional<User> user = userRepository.findByName(request.getUserName());
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
            UserLoanHistory userLoanHistory = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
                    .orElseThrow(IllegalArgumentException::new);
        userLoanHistory.doReturn6();
        userLoanHistoryRepository.save(userLoanHistory);
    }

}

