package choi.web.springboot.repository;

import choi.web.springboot.domain.AccessHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface AccessHistoryRepository extends JpaRepository<AccessHistory, Long> {

    @Query(value =
            "SELECT SUM(1) AS login_sum, " +
                    "TO_CHAR(access_date, 'YYYY-MM-DD') AS access_dt " +
                    "FROM access_history " +
                    "WHERE access_path = '/login' " +
                    "GROUP BY TO_CHAR(access_date, 'YYYY-MM-DD') " +
                    "ORDER BY TO_CHAR(access_date, 'YYYY-MM-DD')"
    )
    List<Map<String, Object>> findLoginHist();

}
