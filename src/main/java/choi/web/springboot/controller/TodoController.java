package choi.web.springboot.controller;

import choi.web.springboot.domain.Todo;
import choi.web.springboot.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/insert")
    public String insert(Todo todo, HttpSession session) {
        todoService.insert(todo, session);
        return "redirect:/main";
    }

    @GetMapping("/update")
    public String update(Todo todo, HttpSession session) {
        todoService.update(todo, session);
        return "redirect:/main";
    }

    @GetMapping("/delete")
    public String delete(Todo todo, HttpSession session) {
        todoService.delete(todo);
        return "redirect:/main";
    }

}
