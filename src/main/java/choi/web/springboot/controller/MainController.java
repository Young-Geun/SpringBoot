package choi.web.springboot.controller;

import choi.web.springboot.domain.User;
import choi.web.springboot.repository.UserRepository;
import choi.web.springboot.service.BoardService;
import choi.web.springboot.service.MessagesService;
import choi.web.springboot.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    BoardService boardService;

    @Autowired
    TodoService todoService;

    @Autowired
    MessagesService messagesService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/main")
    public String main(User user, Model model) {
        if (userRepository.selectUser(user) == null) {
            model.addAttribute("result", "아이디 또는 비밀번호를 확인해주세요.");

            return "signIn";
        } else {
            model.addAttribute("boardList", boardService.getBoardList());
            model.addAttribute("todoList", todoService.getTodoList());
            model.addAttribute("messagesList", messagesService.getMessagesList());

            return "main";
        }
    }

}
