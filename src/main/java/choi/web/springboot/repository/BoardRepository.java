package choi.web.springboot.repository;

import choi.web.springboot.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByTitleContainingIgnoreCaseAndPosterMemberNameContainingIgnoreCase(String title, String memberName, Pageable pageable);

    List<Board> findByTitleLike(String title);

    List<Board> findByTitleContainingIgnoreCase(String title);

    List<Board> findByPosterMemberId(long memberId);

    List<Board> findByPosterMemberName(String memberName);

    List<Board> findByPosterMemberNameContainingIgnoreCase(String memberName);

    List<Board> findByTitleContainingIgnoreCaseOrPosterMemberNameContainingIgnoreCase(String title, String memberName);

}
