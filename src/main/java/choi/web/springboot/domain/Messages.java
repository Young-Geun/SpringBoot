package choi.web.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "messages")
@SequenceGenerator(
        name = "MESSAGES_SEQ_GENERATOR", // 제너레이터명
        sequenceName = "MESSAGES_SEQ", // 시퀀스명
        initialValue = 1, // 시작 값
        allocationSize = 1 // 할당할 범위 사이즈
)
public class Messages {

    // 메시지 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGES_SEQ_GENERATOR")
    long messagesId;

    @OneToOne
    @JoinColumn(name = "send_id")
    private Member sender;

    // 받는이 아이디
    long recvId;

    // 내용
    String messages;

    // 보낸 날짜
    LocalDateTime sendDate;

}
