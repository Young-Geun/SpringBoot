package choi.web.springboot.controller;

import choi.web.springboot.domain.Member;
import choi.web.springboot.service.BoardService;
import choi.web.springboot.service.MemberService;
import choi.web.springboot.service.MessagesService;
import choi.web.springboot.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
@Slf4j
public class MainController {

    private final BoardService boardService;
    private final TodoService todoService;
    private final MessagesService messagesService;
    private final MemberService memberService;

    @GetMapping("/")
    public String signIn(Member member) {
        return "main/login";
    }

    @PostMapping("/login")
    public String login(Member member, Model model, HttpServletRequest request) {
        Member loginMember = memberService.selectOne(member.getMemberEmail(), member.getMemberPassword());
        if (loginMember == null) {
            model.addAttribute("result", "아이디 또는 비밀번호를 확인해주세요.");

            return "main/login";
        } else {
            if (!"Y".equals(loginMember.getMemberStatus())) {
                model.addAttribute("result", "비활성화 계정입니다.");

                return "main/login";
            } else{
                memberService.updateLastLoginDate(loginMember);
                HttpSession session = request.getSession();
                session.setAttribute("loginMember", loginMember);
                log.info("Login Member = {}", loginMember.getMemberEmail());

                return "redirect:/main";
            }
        }
    }

    @GetMapping("/logout")
    public String logout(Member member, HttpSession session) {
        session.invalidate();
        return "main/login";
    }

    @GetMapping("/main")
    public String main(@SessionAttribute(required = false) Member loginMember, Model model, HttpSession session) {
        // 메인화면 구성
        session.setAttribute("boardList", boardService.selectAll(0));
        session.setAttribute("messagesList", messagesService.selectRecvMessagesList(0, loginMember.getMemberId()));
        model.addAttribute("todoList", todoService.selectAll(loginMember.getMemberId()));

        return "main/main";
    }

}
