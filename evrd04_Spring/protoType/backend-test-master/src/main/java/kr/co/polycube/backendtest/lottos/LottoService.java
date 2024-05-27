package kr.co.polycube.backendtest.lottos;

import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class LottoService {

    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int MAX_NUMBER = 45;

    public List<Integer> generateLottoNumbers() {
        Random random = new Random();
        Set<Integer> numbers = new HashSet<>(); // 로또 번호 중복이 없어야하므로 Set사용

        while (numbers.size() < LOTTO_NUMBER_COUNT) {
            int number = random.nextInt(MAX_NUMBER) + 1;    // random 난수생성이 0부터 이므로 +1
            numbers.add(number);
        }

        return numbers.stream()
                .sorted()   // ASC 정렬
                .collect(Collectors.toList());
    }

}
