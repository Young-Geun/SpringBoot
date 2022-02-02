package choi.web.springboot.controller;

import choi.web.springboot.domain.Todo;
import choi.web.springboot.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TodoController {

    @Autowired
    TodoService todoService;

    @PostMapping("/todo/insert")
    public String insert(Todo todo, HttpSession session) {
        todoService.insert(todo, session);

        return "redirect:/main";
    }

    @GetMapping("/todo/update")
    public String update(Todo todo, HttpSession session) {
        todoService.update(todo, session);

        return "redirect:/main";
    }

    @GetMapping("/todo/delete")
    public String delete(Todo todo, HttpSession session) {
        todoService.delete(todo, session);

        return "redirect:/main";
    }

}
