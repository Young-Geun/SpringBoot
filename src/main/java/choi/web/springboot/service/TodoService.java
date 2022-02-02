package choi.web.springboot.service;

import choi.web.springboot.domain.Member;
import choi.web.springboot.domain.Todo;
import choi.web.springboot.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> selectAll(Todo todo) {
        return todoRepository.selectAll(todo);
    }

    public int insert(Todo todo, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        todo.setRegId(loginMember.getMemberId());

        return todoRepository.insert(todo);
    }

    public int update(Todo todo, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        todo.setRegId(loginMember.getMemberId());
        todo.setTodoStatus("Y".equals(todo.getTodoStatus()) ? "N" : "Y");

        return todoRepository.update(todo);
    }

    public int delete(Todo todo, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        todo.setRegId(loginMember.getMemberId());

        return todoRepository.delete(todo);
    }

}
