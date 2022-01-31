package choi.web.springboot.controller;

import choi.web.springboot.domain.Messages;
import choi.web.springboot.domain.Todo;
import choi.web.springboot.domain.User;
import choi.web.springboot.repository.UserRepository;
import choi.web.springboot.service.BoardService;
import choi.web.springboot.service.MessagesService;
import choi.web.springboot.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @GetMapping("/main")
    public String main(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            model.addAttribute("result", "세션이 만료되었습니다.");

            return "signIn";
        } else {
            // 메인화면 구성
            model.addAttribute("boardList", boardService.getBoardList());

            Todo todo = new Todo();
            todo.setRegId(loginUser.getUserId());
            model.addAttribute("todoList", todoService.getTodoList(todo));

            Messages messages = new Messages();
            messages.setRecvId(loginUser.getUserId());
            model.addAttribute("messagesList", messagesService.getMessagesList(messages));

            return "main";
        }
    }

    @PostMapping("/main")
    public String main(User user, Model model, HttpServletRequest request) {
        User loginUser = userRepository.selectUser(user);
        if (loginUser == null) {
            model.addAttribute("result", "아이디 또는 비밀번호를 확인해주세요.");

            return "signIn";
        } else {
            // TODO: 2022/01/21. 로그인 로직 분리할 것
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);

            // 메인화면 구성
            model.addAttribute("boardList", boardService.getBoardList());

            Todo todo = new Todo();
            todo.setRegId(loginUser.getUserId());
            model.addAttribute("todoList", todoService.getTodoList(todo));

            Messages messages = new Messages();
            messages.setRecvId(loginUser.getUserId());
            model.addAttribute("messagesList", messagesService.getMessagesList(messages));

            return "main";
        }
    }

    @GetMapping("/shortcut")
    public String shortcut() {
        return "shortcut";
    }

}
