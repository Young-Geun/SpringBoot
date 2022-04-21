package choi.web.springboot.controller;

import choi.web.springboot.domain.Board;
import choi.web.springboot.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles(value = "window")
class BoardControllerTest {

    @Autowired
    private BoardService boardService;

    @Test
    void 제목검색() {
        // 게시글
        Board board = new Board();
        board.setTitle("테스트");

        // 조회
        List<Board> list = boardService.findByTitle(board);
        System.out.println(list);

        // 결과비교
        assertEquals(list.size(), 3);
    }

}