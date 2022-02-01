package choi.web.springboot.service;

import choi.web.springboot.domain.Board;
import choi.web.springboot.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public int getTotalCount() {
        return boardRepository.selectTotalCount();
    }

    public List<Board> getBoardList(int currentPage) {
        return boardRepository.selectBoardList(currentPage);
    }

}
