package choi.web.springboot.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Messages {

    // 게시물 아이디
    long messagesId;

    // 보낸이 아이디
    long sendId;

    // 보낸이
    String sendName;

    // 받는이 아이디
    long recvId;

    // 내용
    String messages;

    // 보낸 날짜
    LocalDateTime sendDate;

}
