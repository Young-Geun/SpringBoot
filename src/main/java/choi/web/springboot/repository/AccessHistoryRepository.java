package choi.web.springboot.repository;

import choi.web.springboot.domain.AccessHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessHistoryRepository extends JpaRepository<AccessHistory, Long> {

}
