package com.group.libraryapp.prac3;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @Column(nullable = false, length = 255, name = "name")
    private String name;
    private Integer age;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserLoanHistory> historyList = new ArrayList<>();

    protected User(){
    }

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }


    public void bookReturn(String bookName){
        UserLoanHistory targetHistory = this.historyList.stream()
                .filter(historyList -> historyList.getBookName().equals(bookName)) // 대출 기록에서 책이름을 찾기. 없으면 예외발생 있으면 doReturn()으로 반환 실행
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
    }


}
