package choi.web.springboot.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Board {

    // 게시물 아이디
    int boardId;

    // 제목
    String title;

    // 내용
    String contents;

    // 등록자
    String regId;

    // 등록일시
    String regDate;

    // 공지게시판 여부
    String notiFlag;

}
