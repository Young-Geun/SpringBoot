package choi.web.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "board")
@SequenceGenerator(
        name = "BOARD_SEQ_GENERATOR", // 제너레이터명
        sequenceName = "BOARD_SEQ", // 시퀀스명
        initialValue = 1, // 시작 값
        allocationSize = 1 // 할당할 범위 사이즈
)
public class Board {

    // 게시물 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
    long boardId;

    // 제목
    String title;

    // 내용
    String contents;

    // 등록자
    @OneToOne
    @JoinColumn(name = "reg_id")
    private Member poster;

    // 등록일시
    LocalDateTime regDate;

}
