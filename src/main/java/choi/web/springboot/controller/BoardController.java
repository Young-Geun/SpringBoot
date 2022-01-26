
package choi.web.springboot.controller;

import choi.web.springboot.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/notiBoard")
    public String main(Model model) {
        model.addAttribute("boardList", boardService.getBoardList());
        return "board/list";
    }

}
