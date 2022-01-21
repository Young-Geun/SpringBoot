package choi.web.springboot.repository;

import choi.web.springboot.domain.Todo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoRepository {

    List<Todo> selectTodoList(Todo todo);

}
