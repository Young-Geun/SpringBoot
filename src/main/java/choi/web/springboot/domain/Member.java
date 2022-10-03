package choi.web.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    // @JsonIgnore. 조회API 호출 시 응답 값에서 비밀번호를 제거하기위하여 사용했지만, 등록API 호출 시에도 제외되어서 @JsonProperty로 변경.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "비밀번호를 입력해주세요.")
    String memberPassword;

    // 사용자 이름
    @NotBlank(message = "사용자 이름을 입력해주세요.")
    String memberName;

    // 사용자 생년월일
    @NotBlank(message = "사용자 생년월일을 입력해주세요.")
    String memberBirth;

    // 사용자 생년월일
    @Transient
    @JsonIgnore
    String memberBirthYyyy;

    // 사용자 생년월일
    @Transient
    @JsonIgnore
    String memberBirthMm;

    // 사용자 생년월일
    @Transient
    @JsonIgnore
    String memberBirthDd;

    // 사용자 성별
    @NotBlank(message = "사용자 성별을 선택해주세요.")
    String memberGender;

    // 사용자 전화번호
    @NotBlank(message = "사용자 전화번호를 입력해주세요.")
    String memberTel;

    // 사용자 프로필 경로
    String memberProfile;

    // 계정 상태
    String memberStatus;

    // 마지막 로그인
    LocalDateTime lastLoginDate;

}
