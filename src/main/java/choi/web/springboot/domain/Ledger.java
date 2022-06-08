package choi.web.springboot.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class Ledger {

    // 가계부 ID
    private long ledgerId;

    // 사용자 ID
    private long memberId;

    // 거래일시
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transDate;

    // 거래일시(formatted)
    private String transDateFmt;

    // 거래종류 (0000=기타, 0001=식비, 0002=문화생활, 0003=교통비, 0004=통신비, 0005=카드 0006=저축/투자, 0007=교육)
    private String transType;

    // 거래금액
    private long transAmount;

    // 비고
    private String transComment;

    // 검색년월
    private String searchDate;

    // 현재 페이지
    private int currentPage;

}
