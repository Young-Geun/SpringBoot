package choi.web.springboot.controller;

import choi.web.springboot.domain.Todo;
import choi.web.springboot.domain.User;
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

    @PostMapping("/addTodo")
    public String addTodo(Todo todo, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        todo.setRegId(loginUser.getUserId());
        todoService.addTodo(todo);
        return "redirect:/main";
    }

    @GetMapping("/deleteTodo")
    public String deleteTodo(Todo todo, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        todo.setRegId(loginUser.getUserId());
        todoService.deleteTodo(todo);
        return "redirect:/main";
    }

}
