package choi.web.springboot.controller;

import choi.web.springboot.domain.Member;
import choi.web.springboot.repository.BoardRepository;
import choi.web.springboot.service.AccessHistoryService;
import choi.web.springboot.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chart")
public class ChartController {

    private final MemberService memberService;
    private final BoardRepository boardRepository;

    private final AccessHistoryService accessHistoryService;

    @GetMapping("/list")
    public String list(Member member, Model model) {
        // 사용자 계정 상태
        List<Member> memberList = memberService.findAll();
        model.addAttribute("memberList", memberList);

        // 사용자 로그인 현황
        List<Map<String, Object>> loginHistList = accessHistoryService.findLoginHist();
        model.addAttribute("loginHistList", loginHistList);

        // 게시글 등록 현황
        List<Map<String, Object>> list = boardRepository.findGroupByForStatistics();
        model.addAttribute("boardStatisticsList", list);

        return "chart/list";
    }

}
