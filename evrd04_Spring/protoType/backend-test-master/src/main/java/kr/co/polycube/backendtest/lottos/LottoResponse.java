package kr.co.polycube.backendtest.lottos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class LottoResponse {

    private List<Integer> numbers;

}
