package choi.web.springboot.repository;

import choi.web.springboot.domain.Member;
import choi.web.springboot.domain.Messages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages, Long> {

    Page<Messages> findByReceiver(Member receiver, Pageable pageable);

    Page<Messages> findBySender(Member sender, Pageable pageable);

}
