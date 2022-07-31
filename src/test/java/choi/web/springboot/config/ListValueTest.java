package choi.web.springboot.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "app.config.list-value = a,b,c")
@ActiveProfiles(value = "mac")
class ListValueTest {

    @Value("${app.config.list-value}")
    private String[] arrValue;

    @Value("#{'${app.config.list-value}'.split(',')}")
    private List<String> listValue;

    @Test
    void 배열로_가져오기() {
        assertEquals("a", arrValue[0]);
        assertEquals("b", arrValue[1]);
        assertEquals("c", arrValue[2]);
    }

    @Test
    void 리스트로_가져오기() {
        assertEquals("a", listValue.get(0));
        assertEquals("b", listValue.get(1));
        assertEquals("c", listValue.get(2));
    }

}