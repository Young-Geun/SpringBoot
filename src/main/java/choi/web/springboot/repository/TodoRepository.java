package choi.web.springboot.repository;

import choi.web.springboot.domain.Todo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional
public interface TodoRepository {

    List<Todo> selectAll(Todo todo);

    int insert(Todo todo);

    int update(Todo todo);

    int delete(Todo todo);

}
