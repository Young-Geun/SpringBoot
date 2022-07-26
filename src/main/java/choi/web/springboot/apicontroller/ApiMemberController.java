
package choi.web.springboot.apicontroller;

import choi.web.springboot.domain.Member;
import choi.web.springboot.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ApiMemberController {

    private final MemberService memberService;

    @GetMapping("/api/members")
    public @ResponseBody List<Member> findAll() {
        return memberService.findAll();
    }

    @GetMapping("/api/members/{memberId}")
    public @ResponseBody Member findById(@PathVariable long memberId) {
        return memberService.findByMemberId(memberId);
    }

}
