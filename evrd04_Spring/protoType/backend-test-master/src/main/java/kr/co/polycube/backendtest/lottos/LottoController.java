package kr.co.polycube.backendtest.lottos;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LottoController {

    private final LottoService lottoService;

    @PostMapping("/lottos")
    public ResponseEntity<LottoResponse> generateLottoNumbers() {
        List<Integer> numbers = lottoService.generateLottoNumbers();
        LottoResponse response = new LottoResponse(numbers);
        return ResponseEntity.ok(response);
    }

}

