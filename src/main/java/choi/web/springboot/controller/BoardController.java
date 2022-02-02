
package choi.web.springboot.controller;

import choi.web.springboot.common.Pagination;
import choi.web.springboot.domain.Board;
import choi.web.springboot.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/board/list")
    public String list(Model model, HttpServletRequest request) {
        int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
        int currentRange = request.getParameter("currentRange") == null ? 1 : Integer.parseInt(request.getParameter("currentRange"));
        int totalCount = boardService.selectCount();
        List<Board> list = boardService.selectAll(currentPage);
        model.addAttribute("boardList", list);

        Pagination page = new Pagination();
        page.pageInfo(currentPage, currentRange, totalCount);
        model.addAttribute("page", page);

        return "board/list";
    }

}
