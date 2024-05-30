package kr.co.polycube.backendtest.lottos;

import kr.co.polycube.backendtest.lottos.domain.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnerRepository extends JpaRepository<Winner, Long> {
}
