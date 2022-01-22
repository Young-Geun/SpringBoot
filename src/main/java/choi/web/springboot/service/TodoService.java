package choi.web.springboot.service;

import choi.web.springboot.domain.Todo;
import choi.web.springboot.domain.User;
import choi.web.springboot.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodoList(Todo todo) {
        return todoRepository.selectTodoList(todo);
    }

    public int addTodo(Todo todo, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        todo.setRegId(loginUser.getUserId());
        return todoRepository.insertTodo(todo);
    }

    public int deleteTodo(Todo todo, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        todo.setRegId(loginUser.getUserId());
        return todoRepository.deleteTodo(todo);
    }

    public int updateTodo(Todo todo, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        todo.setRegId(loginUser.getUserId());
        todo.setCompleteFlag("Y".equals(todo.getCompleteFlag()) ? "N" : "Y");
        return todoRepository.updateTodo(todo);
    }

}
