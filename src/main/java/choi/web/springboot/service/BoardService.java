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

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final AccessHistoryRepository accessHistoryRepository;

    public Page<Board> findAll(int page) {
        return boardRepository.findAll(PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "boardId")));
    }

    public Board findById(long boardId) {
        return boardRepository.findById(boardId).get();
    }

    public List<Board> findByTitle(Board board) {
        return boardRepository.findByTitleLike("%" + board.getTitle() + "%");
    }

    @Transactional
    public void insert(Board board, HttpServletRequest request, HttpSession session) throws Exception {
        LocalDateTime regDate = LocalDateTime.now();

        // 게시글 등록
        Member loginMember = (Member) session.getAttribute("loginMember");
        board.setPoster(loginMember);
        board.setRegDate(regDate);
        boardRepository.save(board);

        // 접근이력 등록
        AccessHistory accessHistory = new AccessHistory();
        accessHistory.setAccessMemberId(loginMember.getMemberId());
        accessHistory.setAccessPath(request.getRequestURI());
        accessHistory.setAccessDate(regDate);
        accessHistoryRepository.save(accessHistory);
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
