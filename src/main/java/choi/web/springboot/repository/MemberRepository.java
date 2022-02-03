package choi.web.springboot.repository;

import choi.web.springboot.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByMemberNameLike(String name);

    Member findByMemberEmail(String email);

    Member findByMemberEmailAndMemberPassword(String email, String Password);

}
