package choi.web.springboot.repository;

import choi.web.springboot.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Transactional
public interface MemberRepository {

    Member selectOne(Member member);

    Member selectExistOne(Member member);

    int insert(Member member);

    int update(Member member);

}
