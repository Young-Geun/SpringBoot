package choi.web.springboot.controller;

import choi.web.springboot.domain.Member;
import choi.web.springboot.service.MemberService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles(value = "mac")
class MainControllerTest {

    @Autowired
    private MemberService memberService;

    @Test
    void login() {
        Member existMember = memberService.selectOne("choi", "1");
        Member notExistMember = memberService.selectOne("choi@google.com", "1");
        Member passwordIncorrectMember = memberService.selectOne("choi", "1234");

        assertEquals(existMember.getMemberEmail(), "choi");
        assertNull(notExistMember);
        assertNull(passwordIncorrectMember);
    }
}