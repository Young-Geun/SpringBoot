package choi.web.springboot.service;

import choi.web.springboot.domain.AccessHistory;
import choi.web.springboot.domain.Board;
import choi.web.springboot.domain.Member;
import choi.web.springboot.repository.AccessHistoryRepository;
import choi.web.springboot.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final AccessHistoryRepository accessHistoryRepository;

    public Page<Board> findAll(int page) {
        return boardRepository.findAll(PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "boardId")));
    }

    public Page<Board> findByKeyword(int page, Board board) {
        String title = board.getTitle() == null ? "" : board.getTitle();
        String name = (board.getPoster() == null || board.getPoster().getMemberName() == null) ? "" : board.getPoster().getMemberName();
        return boardRepository.findByTitleContainingIgnoreCaseAndPosterMemberNameContainingIgnoreCase(
                title,
                name,
                PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "boardId")));
    }

    public Board findById(long boardId) {
        return boardRepository.findById(boardId).get();
    }

    public List<Board> findByTitleLike(Board board) {
        return boardRepository.findByTitleLike("%" + board.getTitle() + "%");
    }

    public List<Board> findByTitleContainingIgnoreCase(Board board) {
        return boardRepository.findByTitleContainingIgnoreCase(board.getTitle());
    }

    public List<Board> findByPosterMemberId(Board board) {
        return boardRepository.findByPosterMemberId(board.getPoster().getMemberId());
    }

    public List<Board> findByPosterMemberName(Board board) {
        return boardRepository.findByPosterMemberName(board.getPoster().getMemberName());
    }

    public List<Board> findByPosterMemberNameContainingIgnoreCase(Board board) {
        return boardRepository.findByPosterMemberNameContainingIgnoreCase(board.getPoster().getMemberName());
    }

    public List<Board> findByTitleOrPoster(Board board) {
        return boardRepository.findByTitleContainingIgnoreCaseOrPosterMemberNameContainingIgnoreCase(
                board.getTitle(),
                board.getPoster().getMemberName());
    }

    public List<Map<String, Object>> findGroupByForStatistics() {
        return boardRepository.findGroupByForStatistics();
    }

    @Transactional
    public void insert(Board board, HttpSession session) throws Exception {
        Member loginMember = (Member) session.getAttribute("loginMember");
        board.setPoster(loginMember);
        board.setRegDate(LocalDateTime.now());
        boardRepository.save(board);
    }

    @Transactional
    public void update(Board board) {
        boardRepository.save(board);
    }

    @Transactional
    public void delete(Board board) {
        boardRepository.deleteById(board.getBoardId());
    }

}
