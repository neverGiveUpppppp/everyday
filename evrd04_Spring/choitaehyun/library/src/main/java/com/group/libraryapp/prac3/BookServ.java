package com.group.libraryapp.prac3;

import com.group.libraryapp.prac3.domain.user.loanHistory.UserLoanHistory;
import com.group.libraryapp.prac3.repository.UserLoanHistoryRepository;
import com.group.libraryapp.prac3.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServ {

    private final BookRepo bookRepo;
    private final UserRepo userRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;

    public BookServ(BookRepo bookRepo, UserRepo userRepository, UserLoanHistoryRepository userLoanHistoryRepository) {
        this.bookRepo = bookRepo;
        this.userRepository = userRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
    }

    @Transactional
    public void bookReturn(BookReturnRequest request) {
//         1)userId를 찾아서 가져오기
//         2)요구사항에 user 정보는 찾아왔으니 userId와 bookname으로 대출 기록 찾기
//         3)대여가능 상태로 변경하기(책 반납)
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        UserLoanHistory userLoanHistory = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        user.bookReturn(request.getBookName());

    }


    public void loanBook(BookLoanRequest request) {
        // 책 빌릴려는 유저가 있는 지 체크(로그인 상태라면 필요x)
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        // 해당 책이 있는 지 확인
        Book book = bookRepo.findByBookname(request.getBookName()).stream()
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        // 책 대여상태로 변경
        if(bookRepo != null)
            user.loanBook(request);

    }

}
