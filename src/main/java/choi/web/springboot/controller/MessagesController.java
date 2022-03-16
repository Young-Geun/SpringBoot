
package choi.web.springboot.controller;

import choi.web.springboot.common.Pagination;
import choi.web.springboot.domain.Member;
import choi.web.springboot.domain.Messages;
import choi.web.springboot.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping("/messages")
public class MessagesController {

    private final MessagesService messagesService;

    @GetMapping("/recvList")
    public String recvList(Model model, HttpSession session,
                           @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                           @RequestParam(required = false, defaultValue = "1", value = "range") int range) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        Page<Messages> list = messagesService.selectRecvMessagesList(page, loginMember.getMemberId());
        model.addAttribute("messagesList", list);

        Pagination pagination = new Pagination();
        pagination.pageInfo(page + 1, range, (int) list.getTotalElements());
        model.addAttribute("pages", pagination);

        return "messages/recvList";
    }

    @GetMapping("/sendList")
    public String sendList(Model model,
                           HttpSession session,
                           @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                           @RequestParam(required = false, defaultValue = "1", value = "range") int range) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        Page<Messages> list = messagesService.selectSendMessagesList(page, loginMember.getMemberId());
        model.addAttribute("messagesList", list);

        Pagination pagination = new Pagination();
        pagination.pageInfo(page + 1, range, (int) list.getTotalElements());
        model.addAttribute("pages", pagination);

        return "messages/sendList";
    }

    @GetMapping("/send")
    public String send(Member member) {
        return "messages/sendForm";
    }

    @PostMapping("/send")
    public String send(Member member, String content, Model model, HttpSession session) {
        int result = messagesService.sendMessages(member, content, session);
        if (result == 0) {
            model.addAttribute("resultFlag", false);
            model.addAttribute("resultMsg", "메시지 발송을 실패하였습니다.");
        } else {
            model.addAttribute("resultFlag", true);
            model.addAttribute("resultMsg", "메시지 발송을 성공하였습니다.");
        }

        return "messages/sendForm";
    }

}
