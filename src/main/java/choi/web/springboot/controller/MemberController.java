
package choi.web.springboot.controller;

import choi.web.springboot.domain.Member;
import choi.web.springboot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/list")
    public String list(Member member, Model model) {
        List<Member> memberList = memberService.selectList(member.getMemberName());
        model.addAttribute("memberList", memberList);

        return "member/list";
    }

    @GetMapping("/member/insert")
    public String insert(Member member) {
        return "member/insert";
    }

    @PostMapping("/member/insert")
    public String insert(@Validated Member member, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "member/insert";
        }

        int result = memberService.insert(member);
        if (result == 0) {
            model.addAttribute("result", "등록에 실패하였습니다.");
            return "member/insert";
        } else if (result == -1) {
            model.addAttribute("result", "이미 가입된 아이디입니다.");
            return "member/insert";
        } else {
            model.addAttribute("result", "가입이 완료되었습니다.");
            return "main/login";
        }
    }

    @GetMapping("/member/update")
    public String update(Model model, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        model.addAttribute("member", loginMember);

        return "member/update";
    }

    @PostMapping("/member/update")
    public String update(@Validated Member member, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "member/update";
        }

        int result = memberService.update(member);
        if (result == 0) {
            model.addAttribute("result", "수정에 실패하였습니다.");
        } else {
            model.addAttribute("result", "수정이 완료되었습니다.");
            session.setAttribute("loginMember", member);
        }

        return "member/update";
    }

}
