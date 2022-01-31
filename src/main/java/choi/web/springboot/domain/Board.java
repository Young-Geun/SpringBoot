package choi.web.springboot.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {

    // 게시물 아이디
    long boardId;

    // 제목
    String title;

    // 내용
    String contents;

    // 등록자 아이디
    long regId;

    // 등록자 이름
    String regName;

    // 등록일시
    LocalDateTime regDate;

}
