
package choi.web.springboot.controller;

import choi.web.springboot.common.Pagination;
import choi.web.springboot.domain.Board;
import choi.web.springboot.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board/list")
    public String list(Model model,
                       @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                       @RequestParam(required = false, defaultValue = "1", value = "range") int range) {
        Page<Board> list = boardService.selectAll(page);
        model.addAttribute("boardList", list);

        Pagination pagination = new Pagination();
        pagination.pageInfo(page + 1, range, (int) list.getTotalElements());
        model.addAttribute("pages", pagination);

        return "board/list";
    }

    @GetMapping("/board/detail")
    public String list(Model model, Board board) {
        Board result = boardService.selectOne(board.getBoardId());
        model.addAttribute("result", result);

        return "board/detail";
    }

    @PostMapping("/board/update")
    public String update(Board board) {
        boardService.update(board);
        return "redirect:/board/list";
    }

    @PostMapping("/board/delete")
    public String delete(Board board) {
        boardService.delete(board);
        return "redirect:/board/list";
    }

}
