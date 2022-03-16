package choi.web.springboot.controller;

import choi.web.springboot.domain.Member;
import choi.web.springboot.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chart")
public class ChartController {

    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Member member, Model model) {
        List<Member> memberList = memberService.selectList("");
        model.addAttribute("memberList", memberList);

        return "chart/list";
    }

}
