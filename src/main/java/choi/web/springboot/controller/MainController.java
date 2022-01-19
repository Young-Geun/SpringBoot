package choi.web.springboot.controller;

import choi.web.springboot.service.BoardService;
import choi.web.springboot.service.MessagesService;
import choi.web.springboot.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    BoardService boardService;

    @Autowired
    TodoService todoService;

    @Autowired
    MessagesService messagesService;

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("boardList", boardService.getBoardList());
        model.addAttribute("todoList", todoService.getTodoList());
        model.addAttribute("messagesList", messagesService.getMessagesList());

        return "main";
    }

}
