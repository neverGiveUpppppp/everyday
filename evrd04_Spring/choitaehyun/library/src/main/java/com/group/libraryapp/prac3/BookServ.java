package com.group.libraryapp.prac3;

import com.group.libraryapp.prac3.domain.user.loanHistory.UserLoanHistory;
import com.group.libraryapp.prac3.repository.UserLoanHistoryRepository;
import com.group.libraryapp.prac3.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("book")
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
}
