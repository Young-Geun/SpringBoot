package choi.web.springboot.service;

import choi.web.springboot.domain.Board;
import choi.web.springboot.domain.Member;
import choi.web.springboot.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> selectAll(int page) {
        return boardRepository.findAll(PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "boardId")));
    }

    public Board selectOne(long boardId) {
        return boardRepository.findById(boardId).get();
    }

    public int insert(Board board, HttpSession session) {
        int result = 1;
        try {
            Member loginMember = (Member) session.getAttribute("loginMember");
            board.setPoster(loginMember);
            board.setRegDate(LocalDateTime.now());

            boardRepository.save(board);
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
