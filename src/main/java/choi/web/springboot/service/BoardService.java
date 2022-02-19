package choi.web.springboot.service;

import choi.web.springboot.domain.Board;
import choi.web.springboot.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

}
