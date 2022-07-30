package choi.web.springboot.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
@SpringBootTest(properties = "server.port = 8888")
@ActiveProfiles(value = "mac")
class ConfigPropTest {

    @Autowired
    private ConfigProp configProp;

    @Value("${server.port}")
    private String severPort;

    @Test
    void 설정값_가져오기() {
        // 결과비교
        assertEquals("/Users/choi/dev/upload/", configProp.getFileDir());
    }

    @Test
    void 원래_값_가져오기() {
        // @SpringBootTest 으로 실행
        assertEquals("8084", severPort);
    }

    @Test
    void 재정의_값_가져오기() {
        // @SpringBootTest(properties = "server.port = 8888") 으로 실행
        assertEquals("8888", severPort);
    }

}