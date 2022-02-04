package choi.web.springboot.repository;

import choi.web.springboot.domain.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessagesRepository extends JpaRepository<Messages, Long> {

    List<Messages> findByRecvId(long recvId);

}
