package choi.web.springboot.controller;

import choi.web.springboot.domain.Messages;
import choi.web.springboot.domain.Todo;
import choi.web.springboot.domain.User;
import choi.web.springboot.service.BoardService;
import choi.web.springboot.service.MessagesService;
import choi.web.springboot.service.TodoService;
import choi.web.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    UserService userService;

    @GetMapping("/")
    public String signIn(Model model) {
        model.addAttribute("user", new User("", "", "", "", "Y"));
        return "/main/signIn";
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("user", new User("", "", "", "", "Y"));
        return "/main/signUp";
    }

    @PostMapping("/registUser")
    public String registUser(@Validated User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/main/signUp";
        }

        int result = userService.registUser(user);
        if (result == 0) {
            model.addAttribute("result", "등록에 실패하였습니다.");
            return "/main/signUp";
        } else if (result == -1) {
            model.addAttribute("result", "이미 가입된 아이디입니다.");
            return "/main/signUp";
        } else {
            model.addAttribute("result", "가입이 완료되었습니다.");
            return "/main/signIn";
        }
    }

    @GetMapping("/main")
    public String main(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            model.addAttribute("result", "세션이 만료되었습니다.");

            return "/main/signIn";
        } else {
            // 메인화면 구성
            model.addAttribute("boardList", boardService.getBoardList());

            Todo todo = new Todo();
            todo.setRegId(loginUser.getUserId());
            model.addAttribute("todoList", todoService.getTodoList(todo));

            Messages messages = new Messages();
            messages.setRecvId(loginUser.getUserId());
            model.addAttribute("messagesList", messagesService.getMessagesList(messages));

            return "/main/main";
        }
    }

    @PostMapping("/main")
    public String main(User user, Model model, HttpServletRequest request) {
        User loginUser = userService.selectUser(user);
        if (loginUser == null) {
            model.addAttribute("result", "아이디 또는 비밀번호를 확인해주세요.");

            return "/main/signIn";
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

            return "/main/main";
        }
    }

    @GetMapping("/setting")
    public String editUser(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("user", loginUser);

        return "/main/setting";
    }

    @PostMapping("/setting")
    public String editUser(@Validated User user, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "/main/setting";
        }

        int result = userService.editUser(user);
        if (result == 0) {
            model.addAttribute("result", "수정에 실패하였습니다.");
        } else {
            model.addAttribute("result", "수정이 완료되었습니다.");
            session.setAttribute("loginUser", user);
        }
        return "/main/setting";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        model.addAttribute("user", new User("", "", "", "", "Y"));
        session.invalidate();

        return "/main/signIn";
    }

    @GetMapping("/shortcut")
    public String shortcut() {
        return "/main/shortcut";
    }

}
