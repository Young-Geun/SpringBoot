
package choi.web.springboot.controller;

import choi.web.springboot.common.Pagination;
import choi.web.springboot.domain.Ledger;
import choi.web.springboot.domain.Member;
import choi.web.springboot.service.LedgerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

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

}
