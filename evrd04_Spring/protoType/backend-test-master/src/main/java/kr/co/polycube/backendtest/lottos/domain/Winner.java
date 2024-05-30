package kr.co.polycube.backendtest.lottos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
public class Winner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long lottoId;
    @Column(nullable = false, length = 5)
    private int rank;

}