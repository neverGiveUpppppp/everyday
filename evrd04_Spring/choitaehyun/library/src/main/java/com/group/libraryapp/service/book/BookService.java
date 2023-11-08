package com.group.libraryapp.service.book;


import com.group.libraryapp.prac2.Repository.BookRepository;
import com.group.libraryapp.prac2.domain.user.Userr;
import com.group.libraryapp.prac2.domain.book.Book;
import com.group.libraryapp.prac2.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.prac2.dto.request.BookCreateRequest;
import com.group.libraryapp.prac2.dto.request.BookLoanRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository; // 대출 기록 loanBook() 확인을 위한 필드 추가

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
    }

    @Transactional
    public void createBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void bookLoan(BookLoanRequest request) {
        // 대출할 user 조회 // 해당 책의 대출 유무 확인
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new); // 책을 찾으면 Book객체에 책정보가 들어올 것임
        // 2)대출기록을 보고 대출중인지 확인 & 3)만약 대출 중이라면 예외발생 시키기
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)){ // false면 대여중임
            throw new IllegalArgumentException("진작 대출되어 있는 책입니다");
        }
        // 4)유저 정보를 가져온다.(도메인 객체 리팩토링)
        Userr user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.loanBook(book.getName());
    }
}
