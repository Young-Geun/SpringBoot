package choi.web.springboot.repository;

import choi.web.springboot.domain.Messages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages, Long> {

    Page<Messages> findByRecvId(long recvId, Pageable pageable);

}
