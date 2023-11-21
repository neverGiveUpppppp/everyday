package com.group.libraryapp.prac3.repository;

import com.group.libraryapp.prac3.domain.user.loanHistory.UserLoanHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {
    boolean existByBookNameAndIsReturn(String bookName, boolean isReturn);
    Optional<UserLoanHistory> findByUserIdAndBookName(Long userId, String bookName);


}
