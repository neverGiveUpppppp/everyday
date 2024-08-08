package com.jpa.jpa3.a.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){}


    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
    // 생성자에서 값을 모두 초기화해서 변경 불가능한 클래스 :
    // 생성자 값 초기화
    // Setter X
    // 기본생성자 : protected or public - protected 쪽이 안전
    // JPA가 이런 제약을 두는 이유는 JPA 구현 라이브러리가 객체를 생성할 때 리플랙션 같은 기술을 사용할 수 있도록 지원해야 하기 때문
}

