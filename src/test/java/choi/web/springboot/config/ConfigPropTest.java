package choi.web.springboot.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles(value = "mac")
class ConfigPropTest {

    @Autowired
    private ConfigProp configProp;

    @Test
    void 설정값_가져오기() {
        // 결과비교
        assertEquals("/Users/choi/dev/upload/", configProp.getFileDir());
    }

}