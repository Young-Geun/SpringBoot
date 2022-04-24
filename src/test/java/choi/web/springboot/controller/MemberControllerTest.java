package choi.web.springboot.controller;

import choi.web.springboot.domain.Member;
import choi.web.springboot.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles(value = "window")
class MemberControllerTest {

    @Autowired
    private MemberService memberService;

    @Test
    void 이름으로_검색() {
        // 사용자
        Member member = new Member();
        member.setMemberName("최");

        // 조회
        List<Member> list = memberService.findByMemberNameContainingIgnoreCase(member.getMemberName());

        // 결과비교
        assertEquals(3, list.size());
    }

    @Test
    void 이메일로_검색() {
        // 사용자
        Member member = new Member();
        member.setMemberEmail("test");

        // 조회
        List<Member> list = memberService.findByMemberEmailContainingIgnoreCase(member.getMemberEmail());

        // 결과비교
        assertEquals(7, list.size());
    }

}