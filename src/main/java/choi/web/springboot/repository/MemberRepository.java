package choi.web.springboot.repository;

import choi.web.springboot.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberEmail(String email);

    Member findByMemberEmailAndMemberPassword(String email, String Password);

}
