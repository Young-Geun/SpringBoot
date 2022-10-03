package choi.web.springboot.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecurityUtilsTest {

    SecurityUtils securityUtils = new SecurityUtils();

    @Test
    void 암호화_테스트() throws Exception {
        // https://www.convertstring.com/ko/Hash/SHA256 (SHA-256 암호화 사이트)

        assertEquals(
                "DFDDB3BB2FDA34F40185F44B2B69399A41662D32CF93FC4EF71C5034A4706757",
                securityUtils.encrypt("choi")
        );

        assertEquals(
                "7E901DF4274B02F77A59B5862C97F43B69924D29FECA6FEADC61735A91321BD8",
                securityUtils.encrypt("springboot")
        );

        assertEquals(
                "38A0963A6364B09AD867AA9A66C6D009673C21E182015461DA236EC361877F77",
                securityUtils.encrypt("java")
        );

    }

}
