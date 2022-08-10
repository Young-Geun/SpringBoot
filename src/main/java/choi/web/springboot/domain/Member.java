package choi.web.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Entity(name = "member")
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR", // 제너레이터명
        sequenceName = "MEMBER_SEQ", // 시퀀스명
        initialValue = 1, // 시작 값
        allocationSize = 1 // 할당할 범위 사이즈
)
public class Member {

    // 사용자 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    long memberId;

    // 사용자 이메일
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    String memberEmail;

    // 사용자 비밀번호
    @JsonIgnore
    @NotBlank(message = "비밀번호를 입력해주세요.")
    String memberPassword;

    // 사용자 이름
    @NotBlank(message = "사용자 이름을 입력해주세요.")
    String memberName;

    // 사용자 프로필 경로
    String memberProfile;

    // 계정 상태
    String memberStatus;

    // 마지막 로그인
    LocalDateTime lastLoginDate;

}
