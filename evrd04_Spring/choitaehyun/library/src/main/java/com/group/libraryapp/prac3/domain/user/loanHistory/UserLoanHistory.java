package com.group.libraryapp.prac3.domain.user.loanHistory;

import com.group.libraryapp.prac3.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @JoinColumn(nullable = false)
    @ManyToOne
    private User user;
    private String bookName;
    private boolean isReturn;


    protected UserLoanHistory(){}

    public UserLoanHistory(Long id, User user, String bookName, boolean isReturn) {
        this.id = id;
        this.user = user;
        this.bookName = bookName;
        this.isReturn = isReturn;
    }




}
