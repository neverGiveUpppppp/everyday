package com.group.libraryapp.prac3;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @JoinColumn(nullable = false)
    @ManyToOne()
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

    public void doReturn() {
        this.isReturn = true;
    }

}
