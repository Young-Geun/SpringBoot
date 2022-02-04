package choi.web.springboot.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "todo")
@SequenceGenerator(
        name = "TODO_SEQ_GENERATOR", // 제너레이터명
        sequenceName = "TODO_SEQ", // 시퀀스명
        initialValue = 1, // 시작 값
        allocationSize = 1 // 할당할 범위 사이즈
)
public class Todo {

    // 할일 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TODO_SEQ_GENERATOR")
    long todoId;

    // 내용
    @Column(updatable = false)
    String contents;

    // 등록자
    long regId;

    // 상태(Y=완료, N=미완료)
    String todoStatus;

}
