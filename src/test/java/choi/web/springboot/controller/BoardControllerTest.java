package choi.web.springboot.controller;

import choi.web.springboot.domain.Board;
import choi.web.springboot.domain.Member;
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
    void 제목으로_검색() {
        // 게시글
        Board board = new Board();
        board.setTitle("테스트");

        // 조회
        List<Board> list = boardService.findByTitleLike(board);

        // 결과비교
        assertEquals(list.size(), 3);
    }

    @Test
    void 제목으로_검색_메서드비교() {
        // 게시글
        Board board = new Board();
        board.setTitle("ab");

        // 조회
        List<Board> list1 = boardService.findByTitleLike(board);
        List<Board> list2 = boardService.findByTitleContainingIgnoreCase(board);

        /*
            결과비교
            - 현재 등록된 게시글의 제목
              1. abc
              2. ABC

            - findByTitleLike()
              : 파라미터 형태 = "%" + board.getTitle() + "%"
              : like 비교
              : 조회결과 = abc만 검색된다.
            - findByTitleContainingIgnoreCase()
              : 파라미터 형태 = board.getTitle()
              : Containing = 포함하는 결과를 찾는다.
              : IgnoreCase = 대소문자를 구별하지 않는다.
              : 조회결과 = abc, ABC 모두 검색된다.
         */
        assertEquals(list1.size(), 1);
        assertEquals(list2.size(), 2);
    }

    @Test
    void 작성자ID로_검색() {
        // 게시글
        Member member = new Member();
        member.setMemberId(2);
        Board board = new Board();
        board.setPoster(member);

        // 조회
        List<Board> list = boardService.findByPosterMemberId(board);

        // 결과비교
        assertEquals(list.size(), 1);
    }

    @Test
    void 작성자명으로_검색() {
        // 게시글
        Member member = new Member();
        member.setMemberName("최영근");
        Board board = new Board();
        board.setPoster(member);

        // 조회
        List<Board> list = boardService.findByPosterMemberName(board);

        // 결과비교
        assertEquals(list.size(), 8);
    }

    @Test
    void 작성자명으로_LIKE_검색() {
        // 게시글
        Member member = new Member();
        member.setMemberName("최");
        Board board = new Board();
        board.setPoster(member);

        // 조회
        List<Board> list = boardService.findByPosterMemberNameContainingIgnoreCase(board);

        // 결과비교
        assertEquals(list.size(), 9);
    }

}