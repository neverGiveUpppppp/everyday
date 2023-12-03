package com.group.libraryapp.prac2.Service;


import com.group.libraryapp.prac2.Repository.BookRepository;
import com.group.libraryapp.prac2.domain.book.Book;
import com.group.libraryapp.prac2.domain.user.UserRepository;
import com.group.libraryapp.prac2.domain.user.Userr;
import com.group.libraryapp.prac2.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.prac2.dto.request.BookCreateRequest;
import com.group.libraryapp.prac2.dto.request.BookLoanRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository history;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository history, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.history = history;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createBook(BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void bookLoan(BookLoanRequest request){
        // 여기 서비스 계층의 역할 : 대출 중인지 여부 판별해야함
        //    1)책 정보 가져오기
        //    2)대출기록을 보고 대출중인지 확인
        //    3)만약 대출 중이라면 예외발생 시키기
        //    4)유저 정보 가져 온 후, 유저와 책 정보 기반으로 UserLoanHistory를 저장(대출 기록 생성)
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        if(history.existsByBookNameAndIsReturn(book.getName(),false)){
            throw new IllegalArgumentException("대출중");
        }
        Userr user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.bookLoan(book.getName());

    }


}
