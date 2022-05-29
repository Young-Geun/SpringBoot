package choi.web.springboot.repository.mybatissub;

import choi.web.springboot.domain.Ledger;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedgerRepository {

    List<Ledger> findAll(long memberId);

}
