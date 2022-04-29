package choi.web.springboot.repository;

import choi.web.springboot.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByTitleContainingIgnoreCaseAndPosterMemberNameContainingIgnoreCase(String title, String memberName, Pageable pageable);

    List<Board> findByTitleLike(String title);

    List<Board> findByTitleContainingIgnoreCase(String title);

    List<Board> findByPosterMemberId(long memberId);

    List<Board> findByPosterMemberName(String memberName);

    List<Board> findByPosterMemberNameContainingIgnoreCase(String memberName);

    List<Board> findByTitleContainingIgnoreCaseOrPosterMemberNameContainingIgnoreCase(String title, String memberName);

    @Query(value =
            "SELECT SUM(1) AS REG_SUM, " +
            "TO_CHAR(reg_date, 'YYYY-MM-DD') AS REG_DT " +
            "FROM board " +
            "GROUP BY TO_CHAR(reg_date, 'YYYY-MM-DD') " +
            "ORDER BY TO_CHAR(reg_date, 'YYYY-MM-DD')"
    )
    List<Map<String, Object>> findGroupByForStatistics();

}
