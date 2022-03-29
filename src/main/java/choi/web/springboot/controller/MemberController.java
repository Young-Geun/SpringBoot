
package choi.web.springboot.controller;

import choi.web.springboot.domain.Member;
import choi.web.springboot.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    @Value("${file.dir}")
    private String fileDir;

    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Member member, Model model) {
        List<Member> memberList = memberService.selectList(member.getMemberName());
        model.addAttribute("memberList", memberList);

        return "member/list";
    }

    @GetMapping("/insert")
    public String insert(Member member) {
        return "member/insert";
    }

    @PostMapping("/insert")
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

    @GetMapping("/update")
    public String update(Model model, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        model.addAttribute("member", loginMember);

        return "member/update";
    }

    @PostMapping("/update")
    public String update(@Validated Member member, BindingResult bindingResult,
                         @RequestParam("file") MultipartFile files,
                         Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "member/update";
        }

        int result = memberService.update(member, files);
        if (result == 0) {
            model.addAttribute("result", "수정에 실패하였습니다.");
        } else {
            model.addAttribute("result", "수정이 완료되었습니다.");
            session.setAttribute("loginMember", member);
        }

        return "member/update";
    }

    @GetMapping("/profile")
    public ResponseEntity<Resource> display(@RequestParam("memberId") long memberId) {
        // 사용자 조회
        Member member = memberService.findByMemberId(memberId);

        // 사용자 프로필 셋팅
        String filePath = fileDir + "member_pic" + File.separator + member.getMemberProfile();
        Resource resource = new FileSystemResource(filePath);
        HttpHeaders header = new HttpHeaders();
        try {
            Path path = Paths.get(filePath);
            header.add("Content-type", Files.probeContentType(path));
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }

}
