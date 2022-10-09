
package choi.web.springboot.controller;

import choi.web.springboot.config.ConfigProp;
import choi.web.springboot.domain.Member;
import choi.web.springboot.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    private final ConfigProp configProp;

    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Member member, Model model) {
        List<Member> memberList;
        if (member.getMemberName() != null) {
            memberList = memberService.findByMemberNameContainingIgnoreCase(member.getMemberName());
        } else {
            memberList = memberService.findByMemberEmailContainingIgnoreCase(member.getMemberEmail());
        }
        model.addAttribute("memberList", memberList);

        return "member/list";
    }

    @GetMapping("/insert")
    public String insert(Member member, Model model) {
        model.addAttribute("currentYear", LocalDate.now().getYear());
        return "member/insert";
    }

    @PostMapping("/insert")
    public String insert(@Validated Member member, BindingResult bindingResult, Model model) {
        model.addAttribute("currentYear", LocalDate.now().getYear());
        if (bindingResult.hasErrors()) {
            return "member/insert";
        }

        int result = memberService.insert(member);
        if (result == 0) {
            model.addAttribute("result", "등록에 실패하였습니다.");
            return "member/insert";
        } else if (result == -1) {
            model.addAttribute("result", "이미 가입된 아이디(Email)입니다.");
            return "member/insert";
        } else {
            model.addAttribute("result", "가입이 완료되었습니다.");
            return "main/login";
        }
    }

    @GetMapping("/update")
    public String update(Model model, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        String memberBirth = loginMember.getMemberBirth();
        loginMember.setMemberBirthYyyy(memberBirth.substring(0, 4));
        loginMember.setMemberBirthMm(memberBirth.substring(4, 6));
        loginMember.setMemberBirthDd(memberBirth.substring(6, 8));
        model.addAttribute("member", loginMember);
        model.addAttribute("currentYear", LocalDate.now().getYear());

        return "member/update";
    }

    @PostMapping("/update")
    public String update(@Validated Member member, BindingResult bindingResult,
                         @RequestParam("file") MultipartFile files,
                         Model model, HttpSession session) {
        model.addAttribute("currentYear", LocalDate.now().getYear());
        if (bindingResult.hasErrors()) {
            return "member/update";
        }

        int result = memberService.update(member, (Member) session.getAttribute("loginMember"), files);
        if (result == 0) {
            model.addAttribute("result", "수정에 실패하였습니다.");
        } else {
            model.addAttribute("result", "수정이 완료되었습니다.");
            session.setAttribute("loginMember", member);
        }

        return "member/update";
    }

    @ResponseBody
    @GetMapping("/profile")
    public Resource profile(@RequestParam("memberId") long memberId) {
        // 사용자 조회
        Member member = findMember(memberId);

        // 사용자 프로필 셋팅
        try {
            String filePath = configProp.getFileDir() + "member_pic" + File.separator + member.getMemberProfile();
            Resource resource = new UrlResource("file:" + filePath);
            if (resource.exists()) {
                return resource;
            } else {
                return new UrlResource("classpath:static/assets/images/faces-clipart/pic-1.png");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/profile-download")
    public ResponseEntity<Resource> profileDownload(@RequestParam("memberId") long memberId) throws Exception {
        // 사용자 조회
        Member member = findMember(memberId);

        // 사용자 프로필 다운로드
        try {
            String filePath = configProp.getFileDir() + "member_pic" + File.separator + member.getMemberProfile();
            Resource resource = new UrlResource("file:" + filePath);
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + member.getMemberProfile())
                        .body(resource);
            } else {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=pic-1.png")
                        .body(new UrlResource("classpath:static/assets/images/faces-clipart/pic-1.png"));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/find-password")
    public String findPassword(Model model) {
        model.addAttribute("member", new Member());
        return "member/findPassword";
    }

    @PostMapping("/find-password")
    public String findPassword(Member member, Model model) {
        Member findMember = memberService.findByMemberEmailAndMemberName(member.getMemberEmail(), member.getMemberName());
        if (findMember == null) {
            model.addAttribute("result", "등록된 계정이 아닙니다.");
        } else {
            try {
                String initPassword = memberService.initPassword(member.getMemberEmail());
                model.addAttribute("result", "".equals(initPassword) ? "임시 비밀번호 발급 실패" : "임시 비밀번호가 발급되었습니다. 이메일을 확인해주세요.");
            } catch (Exception e) {
                model.addAttribute("result", "오류가 발생하였습니다.");
            }
        }

        return "member/findPassword";
    }

    public Member findMember(long memberId) {
        return memberService.findByMemberId(memberId);
    }

}
