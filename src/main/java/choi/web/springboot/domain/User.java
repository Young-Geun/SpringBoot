package choi.web.springboot.domain;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class User {

    // 사용자 아이디(이메일 형식)
    @NotBlank(message = "아이디를 입력해주세요.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    String userId;

    // 사용자 비밀번호
    @NotBlank(message = "비밀번호를 입력해주세요.")
    String userPassword;

    // 사용자 비밀번호(재확인)
    @NotBlank(message = "재확인 비밀번호를 입력해주세요.")
    String userPasswordCert;

    // 사용자 이름
    @NotBlank(message = "사용자 이름을 입력해주세요.")
    String userName;

    // 계정 상태
    String userStatus;

    public User(String userId, String userPassword, String userPasswordCert, String userName, String userStatus) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userPasswordCert = userPasswordCert;
        this.userName = userName;
        this.userStatus = userStatus;
    }

    @AssertTrue(message = "비밀번호가 일치하지 않습니다.")
    public boolean isSamePassword() {
        return userPassword.equals(userPasswordCert);
    }
}
