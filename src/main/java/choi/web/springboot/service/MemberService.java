package choi.web.springboot.service;

import choi.web.springboot.domain.Member;
import choi.web.springboot.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member selectOne(Member member) {
        return memberRepository.selectOne(member);
    }

    public int insert(Member member) {
        if (memberRepository.selectExistOne(member) == null) {
            return memberRepository.insert(member);
        } else {
            return -1;
        }
    }

    public int update(Member member) {
        return memberRepository.update(member);
    }

}
