package choi.web.springboot.controller;

import choi.web.springboot.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    BoardService boardService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("boardList", boardService.getGuestbookList());
        return "main";
    }

}
