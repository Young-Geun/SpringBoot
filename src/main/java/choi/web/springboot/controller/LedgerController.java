
package choi.web.springboot.controller;

import choi.web.springboot.domain.Member;
import choi.web.springboot.service.LedgerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@RequiredArgsConstructor
@Controller
@Slf4j
public class LedgerController {

    private final LedgerService ledgerService;

    @GetMapping("/ledger/list")
    public String list(@SessionAttribute(required = false) Member loginMember, Model model) {
        model.addAttribute("list", ledgerService.findAll(loginMember.getMemberId()));
        return "ledger/list";
    }

}
