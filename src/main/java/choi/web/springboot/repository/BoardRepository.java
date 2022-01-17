package choi.web.springboot.repository;

import choi.web.springboot.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardRepository {

    List<Board> selectBoardList();

}
