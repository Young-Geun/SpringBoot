package choi.web.springboot.repository;

import choi.web.springboot.domain.Todo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional
public interface TodoRepository {

    List<Todo> selectTodoList(Todo todo);

    int insertTodo(Todo todo);

    int deleteTodo(Todo todo);

    int updateTodo(Todo todo);

}
