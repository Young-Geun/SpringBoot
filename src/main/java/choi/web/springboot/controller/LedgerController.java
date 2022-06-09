
package choi.web.springboot.controller;

import choi.web.springboot.common.Pagination;
import choi.web.springboot.domain.Ledger;
import choi.web.springboot.domain.Member;
import choi.web.springboot.service.LedgerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Controller
@Slf4j
public class LedgerController {

    private final LedgerService ledgerService;

    @GetMapping("/ledger/list")
    public String list(Ledger ledger,
                       Model model,
                       @SessionAttribute(required = false) Member loginMember,
                       @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                       @RequestParam(required = false, defaultValue = "1", value = "range") int range) {
        // valid check
        if (ledger.getSearchDate() == null || "".equals(ledger.getSearchDate())) {
            ledger.setSearchDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMM")));
        }

        // total count
        int totalCount = ledgerService.findTotalCount(ledger, loginMember.getMemberId());
        model.addAttribute("totalCount", totalCount);

        // list
        model.addAttribute("list", ledgerService.findAll(ledger, loginMember.getMemberId(), page));

        // pagination
        Pagination pagination = new Pagination();
        pagination.pageInfo(page + 1, range, totalCount);
        model.addAttribute("pages", pagination);

        return "ledger/list";
    }

    @GetMapping("/ledger/summary")
    public String summary(@SessionAttribute(required = false) Member loginMember, Model model) {
        // list
        model.addAttribute("list", ledgerService.findSummary(loginMember.getMemberId()));
        return "ledger/summary";
    }

    @GetMapping("/ledger/detail")
    public String detail(Ledger ledger, Model model) {
        Ledger result = ledgerService.findById(ledger.getLedgerId());
        model.addAttribute("result", result);

        return "ledger/detail";
    }

    @GetMapping("/ledger/insert")
    public String insert(Ledger ledger) {
        return "ledger/insert";
    }

    @PostMapping("/ledger/insert")
    public String insert(@Validated Ledger ledger, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "ledger/insert";
        }

        try {
            ledgerService.insert(ledger, session);
            return "redirect:/ledger/list";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("result", "등록에 실패하였습니다.");
            return "ledger/insert";
        }
    }

    @PostMapping("/ledger/update")
    public String update(Ledger ledger) {
        ledgerService.update(ledger);
        return "redirect:/ledger/list";
    }

    @PostMapping("/ledger/delete")
    public String delete(Ledger ledger) {
        ledgerService.delete(ledger);
        return "redirect:/ledger/list";
    }

}
