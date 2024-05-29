package kr.co.polycube.backendtest.lotto_batch;

import kr.co.polycube.backendtest.lotto_batch.domain.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnerRepository extends JpaRepository<Winner, Long> {
}
