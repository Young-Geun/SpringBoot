
package choi.web.springboot.controller;

import choi.web.springboot.common.Pagination;
import choi.web.springboot.domain.Board;
import choi.web.springboot.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
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

    @GetMapping("/detail")
    public String list(Model model, Board board) {
        Board result = boardService.selectOne(board.getBoardId());
        model.addAttribute("result", result);

        return "board/detail";
    }

    @GetMapping("/insert")
    public String insert(Board board) {
        return "board/insert";
    }

    @PostMapping("/insert")
    public String insert(@Validated Board board, BindingResult bindingResult,
                         Model model, HttpServletRequest request, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "board/insert";
        }

        int result = boardService.insert(board, request, session);
        if (result == 0) {
            model.addAttribute("result", "등록에 실패하였습니다.");
            return "board/insert";
        } else {
            return "redirect:/board/list";
        }
    }

    @PostMapping("/update")
    public String update(Board board) {
        boardService.update(board);
        return "redirect:/board/list";
    }

    @PostMapping("/delete")
    public String delete(Board board) {
        boardService.delete(board);
        return "redirect:/board/list";
    }

}
