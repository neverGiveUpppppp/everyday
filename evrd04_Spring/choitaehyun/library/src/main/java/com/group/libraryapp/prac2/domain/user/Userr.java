package com.group.libraryapp.prac2.domain.user;

import com.group.libraryapp.prac2.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.List;

@Entity
public class Userr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, length = 255, name = "name")
    private String name;
    private Integer age;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories;

    public Userr(){

    }

    public Userr(String name, Integer age) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
        }
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void bookLoan(String bookName) {
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
    }

    public void returnBook(String bookName) {

    }
}
