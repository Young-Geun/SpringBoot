package choi.web.springboot.service;

import choi.web.springboot.domain.Ledger;
import choi.web.springboot.repository.mybatissub.LedgerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class LedgerService {

    private final LedgerRepository ledgerRepository;

    public int findTotalCount(long memberId) {
        return ledgerRepository.findTotalCount(memberId);
    }

    public List<Ledger> findAll(long memberId, int page) {
        return ledgerRepository.findAll(memberId, page * 10);
    }

}
