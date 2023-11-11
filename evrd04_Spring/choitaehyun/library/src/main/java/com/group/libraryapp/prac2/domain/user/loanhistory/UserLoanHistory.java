package com.group.libraryapp.prac2.domain.user.loanhistory;


import com.group.libraryapp.prac2.domain.user.Userr;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Userr user;
    private String userName;
    private String bookName;
    private boolean isReturn;

    protected UserLoanHistory(){

    }

    public UserLoanHistory(Long id, Userr user, String userName, String bookName) {
        this.id = id;
        this.user = user;
        this.userName = userName;
        this.bookName = bookName;
    }


    public UserLoanHistory(Userr user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }
}
