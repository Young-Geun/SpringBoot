package choi.web.springboot.domain;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class Member {

    // 사용자 아이디
    long memberId;

    // 사용자 이메일
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    String memberEmail;

    // 사용자 비밀번호
    @NotBlank(message = "비밀번호를 입력해주세요.")
    String memberPassword;

    // 사용자 비밀번호(재확인)
    @NotBlank(message = "재확인 비밀번호를 입력해주세요.")
    String memberPasswordCert;

    // 사용자 이름
    @NotBlank(message = "사용자 이름을 입력해주세요.")
    String memberName;

    // 계정 상태
    String memberStatus;

    public Member(String memberEmail, String memberPassword, String memberPasswordCert, String memberName, String memberStatus) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberPasswordCert = memberPasswordCert;
        this.memberName = memberName;
        this.memberStatus = memberStatus;
    }

    @AssertTrue(message = "비밀번호가 일치하지 않습니다.")
    public boolean isSamePassword() {
        return memberPassword.equals(memberPasswordCert);
    }
}
