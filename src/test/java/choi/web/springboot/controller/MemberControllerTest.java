package choi.web.springboot.controller;

import choi.web.springboot.domain.Member;
import choi.web.springboot.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ActiveProfiles(value = "mac")
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

    @Test
    void 비밀번호_초기화() throws Exception {
        // 임시비밀번호 발급
        String initPassword = memberService.initPassword("enc_test@naver.com");

        // 사용자 조회
        Member LoginMember = memberService.findForLogin("enc_test@naver.com", initPassword);

        // 결과비교
        assertNotNull(LoginMember);
    }

}