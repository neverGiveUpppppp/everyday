package kr.co.polycube.backendtest.lottos;

import kr.co.polycube.backendtest.lottos.domain.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoRepository  extends JpaRepository<Lotto, Long> {
}
