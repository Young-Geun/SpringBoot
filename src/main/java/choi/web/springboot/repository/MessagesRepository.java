package choi.web.springboot.repository;

import choi.web.springboot.domain.Messages;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessagesRepository {

    List<Messages> selectMessagesList(Messages messages);

}
