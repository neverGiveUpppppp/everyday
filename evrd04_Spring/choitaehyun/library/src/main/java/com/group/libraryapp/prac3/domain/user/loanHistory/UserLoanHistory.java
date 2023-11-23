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
    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }


    public void doReturn(){   // 대출 중(false)인 것을 반납완료 상태인 대여가능상태(true)로 바꿔주기 위해 추가
        this.isReturn = true;
    }

    public void doReturn2(){
        this.isReturn = true;
    }
    public void doRetur3(){
        this.isReturn = true;
    }

    public String getBookName() {
        return this.bookName;
    }
}
