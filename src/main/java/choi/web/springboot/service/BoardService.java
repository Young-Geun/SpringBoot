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

    public int selectCount() {
        return boardRepository.selectCount();
    }

    public List<Board> selectAll(int currentPage) {
        return boardRepository.selectAll(currentPage);
    }

}
