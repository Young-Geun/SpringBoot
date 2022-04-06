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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final AccessHistoryRepository accessHistoryRepository;

    public Page<Board> selectAll(int page) {
        return boardRepository.findAll(PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "boardId")));
    }

    public Board selectOne(long boardId) {
        return boardRepository.findById(boardId).get();
    }

    public int insert(Board board, HttpServletRequest request, HttpSession session) {
        int result = 1;
        try {
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
        } catch (Exception e) {
            result = 0;
        }

        return result;
    }

    public void update(Board board) {
        boardRepository.save(board);
    }

    public void delete(Board board) {
        boardRepository.deleteById(board.getBoardId());
    }

}
