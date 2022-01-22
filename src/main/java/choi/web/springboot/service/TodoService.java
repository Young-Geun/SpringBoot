package choi.web.springboot.service;

import choi.web.springboot.domain.Todo;
import choi.web.springboot.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodoList(Todo todo) {
        return todoRepository.selectTodoList(todo);
    }

    public int addTodo(Todo todo) {
        return todoRepository.insertTodo(todo);
    }

    public int deleteTodo(Todo todo) {
        return todoRepository.deleteTodo(todo);
    }

}
