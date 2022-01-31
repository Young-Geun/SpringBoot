package choi.web.springboot.domain;

import lombok.Data;

@Data
public class Todo {

    // 할일 아이디
    long todoId;

    // 내용
    String contents;

    // 등록자
    long regId;

    // 상태(Y=완료, N=미완료)
    String todoStatus;

}
