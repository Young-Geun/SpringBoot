package choi.web.springboot.controller;

import choi.web.springboot.domain.Member;
import choi.web.springboot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChartController {

    private MemberService memberService;

    @Autowired
    public ChartController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/chart/list")
    public String list(Member member, Model model) {
        List<Member> memberList = memberService.selectList("");
        model.addAttribute("memberList", memberList);

        return "chart/list";
    }

}
