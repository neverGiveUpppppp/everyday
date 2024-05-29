package kr.co.polycube.backendtest.lotto_batch;

import kr.co.polycube.backendtest.lotto_batch.domain.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoRepository  extends JpaRepository<Lotto, Long> {
}
