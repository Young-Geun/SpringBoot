package choi.web.springboot.repository;

import choi.web.springboot.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByRegId(long regId);

}
