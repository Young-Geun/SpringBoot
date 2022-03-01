package choi.web.springboot.controller;

import choi.web.springboot.domain.Member;
import choi.web.springboot.service.BoardService;
import choi.web.springboot.service.MemberService;
import choi.web.springboot.service.MessagesService;
import choi.web.springboot.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class MainController {

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    private final BoardService boardService;
    private final TodoService todoService;
    private final MessagesService messagesService;
    private final MemberService memberService;

    @GetMapping("/")
    public String signIn(Member member) {
        return "main/login";
    }

    @GetMapping("/logout")
    public String logout(Member member, HttpSession session) {
        session.invalidate();
        return "main/login";
    }

    @GetMapping("/main")
    public String main(Model model, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            model.addAttribute("result", "세션이 만료되었습니다.");

            return "main/login";
        } else {
            // 메인화면 구성
            model.addAttribute("todoList", todoService.selectAll(loginMember.getMemberId()));

            return "main/main";
        }
    }

    @PostMapping("/main")
    public String main(Member member, Model model, HttpServletRequest request) {
        Member loginMember = memberService.selectOne(member.getMemberEmail(), member.getMemberPassword());
        if (loginMember == null) {
            model.addAttribute("result", "아이디 또는 비밀번호를 확인해주세요.");

            return "main/login";
        } else {
            // TODO: 2022/01/21. 로그인 로직 분리할 것
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", loginMember);
            logger.info("Login Member = {}", loginMember.getMemberEmail());

            // 메인화면 구성
            session.setAttribute("boardList", boardService.selectAll(0));
            session.setAttribute("messagesList", messagesService.selectAll(0, loginMember.getMemberId()));
            model.addAttribute("todoList", todoService.selectAll(loginMember.getMemberId()));

            return "main/main";
        }
    }

}
