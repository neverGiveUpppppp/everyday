package kr.co.polycube.backendtest.lottos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Lotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2)
    private int number1;
    @Column(nullable = false, length = 2)
    private int number2;
    @Column(nullable = false, length = 2)
    private int number3;
    @Column(nullable = false, length = 2)
    private int number4;
    @Column(nullable = false, length = 2)
    private int number5;
    @Column(nullable = false, length = 2)
    private int number6;



}
