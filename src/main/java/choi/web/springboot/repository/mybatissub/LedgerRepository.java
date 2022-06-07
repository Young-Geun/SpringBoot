package choi.web.springboot.repository.mybatissub;

import choi.web.springboot.domain.Ledger;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedgerRepository {

    int findTotalCount(Ledger ledger);

    List<Ledger> findAll(Ledger ledger);

    List<Ledger> findSummary(long memberId);

    Ledger findById(long ledgerId);

    int deleteById(long ledgerId);

}
