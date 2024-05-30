package kr.co.polycube.backendtest.lottos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class LottoResponse {

    private List<Integer> numbers;

}
