package choi.web.springboot.service;

import choi.web.springboot.domain.Member;
import choi.web.springboot.domain.Todo;
import choi.web.springboot.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public List<Todo> findByRegId(long regId) {
        return todoRepository.findByRegId(regId);
    }

    public void insert(Todo todo, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        todo.setRegId(loginMember.getMemberId());
        todo.setTodoStatus("N");
        todoRepository.save(todo);
    }

    public void update(Todo todo, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        todo.setRegId(loginMember.getMemberId());
        todo.setTodoStatus("Y".equals(todo.getTodoStatus()) ? "N" : "Y");
        todoRepository.save(todo);
    }

    public void delete(Todo todo) {
        todoRepository.deleteById(todo.getTodoId());
    }

}
