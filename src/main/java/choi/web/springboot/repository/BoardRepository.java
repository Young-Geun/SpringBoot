package choi.web.springboot.repository;

import choi.web.springboot.domain.Board;
import choi.web.springboot.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitleLike(String title);

    List<Board> findByTitleContainingIgnoreCase(String title);

    List<Board> findByPosterMemberId(long memberId);

    List<Board> findByPosterMemberName(String memberName);

    List<Board> findByPosterMemberNameContainingIgnoreCase(String memberName);

    List<Board> findByTitleContainingIgnoreCaseOrPosterMemberNameContainingIgnoreCase(String title, String memberName);

}
