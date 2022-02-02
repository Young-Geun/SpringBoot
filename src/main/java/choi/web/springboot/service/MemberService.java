package choi.web.springboot.service;

import choi.web.springboot.domain.Member;
import choi.web.springboot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member selectOne(String email, String password) {
        return memberRepository.findByMemberEmailAndMemberPassword(email, password);
    }

    public int insert(Member member) {
        int result = 1;
        if (memberRepository.findByMemberEmail(member.getMemberEmail()) == null) {
            try {
                memberRepository.save(member);
            } catch (Exception e) {
                result = 0;
            }
        } else {
            result = -1;
        }

        return result;
    }

    public int update(Member member) {
        int result = 1;
        try {
            memberRepository.save(member);
        } catch (Exception e) {
            result = 0;
        }

        return result;
    }

}
