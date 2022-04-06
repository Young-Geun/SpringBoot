package choi.web.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "access_history")
@SequenceGenerator(
        name = "ACCESS_HISTORY_SEQ_GENERATOR", // 제너레이터명
        sequenceName = "ACCESS_HISTORY_SEQ", // 시퀀스명
        initialValue = 1, // 시작 값
        allocationSize = 1 // 할당할 범위 사이즈
)
public class AccessHistory {

    // 접근이력 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCESS_HISTORY_SEQ_GENERATOR")
    long accessId;

    // 접근자 아이디
    long accessMemberId;

    // 접근 경로
    String accessPath;

    // 등록일시
    LocalDateTime accessDate;

}
