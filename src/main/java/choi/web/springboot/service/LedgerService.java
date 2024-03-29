package choi.web.springboot.service;

import choi.web.springboot.domain.Ledger;
import choi.web.springboot.domain.Member;
import choi.web.springboot.repository.mybatissub.LedgerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class LedgerService {

    private final LedgerRepository ledgerRepository;

    public int findTotalCount(Ledger ledger, long memberId) {
        ledger.setMemberId(memberId);
        return ledgerRepository.findTotalCount(ledger);
    }

    public List<Ledger> findAll(Ledger ledger, long memberId, int page) {
        ledger.setMemberId(memberId);
        ledger.setCurrentPage(page * 10);
        return ledgerRepository.findAll(ledger);
    }

    public List<Ledger> findSummary(long memberId) {
        return ledgerRepository.findSummary(memberId);
    }

    public Ledger findById(long ledgerId) {
        return ledgerRepository.findById(ledgerId);
    }

    @Transactional
    public void insert(Ledger ledger, HttpSession session) throws Exception {
        Member loginMember = (Member) session.getAttribute("loginMember");
        ledger.setMemberId(loginMember.getMemberId());
        ledgerRepository.save(ledger);
    }

    @Transactional
    public void update(Ledger ledger) {
        ledgerRepository.update(ledger);
    }

    @Transactional
    public void delete(Ledger ledger) {
        ledgerRepository.deleteById(ledger.getLedgerId());
    }

}
