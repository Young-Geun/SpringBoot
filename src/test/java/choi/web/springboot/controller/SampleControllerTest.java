package choi.web.springboot.controller;

import choi.web.springboot.domain.Sample;
import choi.web.springboot.service.MybatisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles(value = "mac")
class SampleControllerTest {

    @Autowired
    private MybatisService mybatisService;

    @Test
    void 등록() {
        // 사용자
        Sample sample = new Sample();
        sample.setNumColumn1(1);
        sample.setStrColumn1("test1");
        sample.setStrColumn2("test2");
        sample.setDateColumn1(LocalDateTime.now());

        // 조회
        int result = mybatisService.insert(sample);

        // 결과비교
        assertEquals(1, result);
    }

}