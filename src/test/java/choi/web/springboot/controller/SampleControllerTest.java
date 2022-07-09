package choi.web.springboot.controller;

import choi.web.springboot.domain.Sample;
import choi.web.springboot.service.MybatisService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles(value = "mac")
@Transactional
class SampleControllerTest {

    @Autowired
    private MybatisService mybatisService;

    /*
        @Transactional
            : @Transactional 어노테이션을 테스트 코드에 작성할 경우,
              데이터를 롤백 시켜준다.
              따라서, 아래의 롤백 코드없이 @Transactional 어노테이션만으로 기존 데이터를 유지할 수 있다.
     */

//    @Autowired
//    private PlatformTransactionManager transactionManager;
//
//    TransactionStatus status;
//
//    @BeforeEach
//    void beforeEach() {
//        status = transactionManager.getTransaction(new DefaultTransactionDefinition());
//    }
//
//    @AfterEach
//    void afterEach() {
//        transactionManager.rollback(status);
//    }

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