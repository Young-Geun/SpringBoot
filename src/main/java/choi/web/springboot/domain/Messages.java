package choi.web.springboot.domain;

import lombok.Data;

@Data
public class Messages {

    // 게시물 아이디
    int messagesId;

    // 보낸이
    String sendId;

    // 받는이
    String recvId;

    // 내용
    String messages;

}
