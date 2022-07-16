package choi.web.springboot.tx;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles(value = "mac")
public class CommitAndRollbackTest {

    @Autowired
    RollbackService rollbackService;

    @Test
    void runtimeEx() {
        Assertions.assertThatThrownBy(() -> rollbackService.runtimeEx())
                .isInstanceOf(RuntimeException.class);
        /*
            DEBUG [org.springframework.orm.jpa.JpaTransactionManager] Initiating transaction rollback
            DEBUG [org.springframework.orm.jpa.JpaTransactionManager] Rolling back JPA transaction on EntityManager
         */
    }

    @Test
    void checkedEx() {
        Assertions.assertThatThrownBy(() -> rollbackService.checkedEx())
                .isInstanceOf(MyEx.class);
        /*
            DEBUG [org.springframework.orm.jpa.JpaTransactionManager] Initiating transaction commit
            DEBUG [org.springframework.orm.jpa.JpaTransactionManager] Committing JPA transaction on EntityManager
         */
    }

    @Test
    void checkedExWithRollbackFor() {
        Assertions.assertThatThrownBy(() -> rollbackService.checkedExWithRollbackFor())
                .isInstanceOf(MyEx.class);
        /*
            DEBUG [org.springframework.orm.jpa.JpaTransactionManager] Initiating transaction rollback
            DEBUG [org.springframework.orm.jpa.JpaTransactionManager] Rolling back JPA transaction on EntityManager
         */
    }

    @TestConfiguration
    static class RollbackConfig {
        @Bean
        RollbackService rollbackService() {
            return new RollbackService();
        }
    }

    static class RollbackService {

        private final static Logger log = LoggerFactory.getLogger(RollbackService.class);

        // 런타임예외 = 롤백
        @Transactional
        public void runtimeEx() {
            log.debug("call runtimeEx()");
            throw new RuntimeException();
        }

        // 체크예외 = 커밋
        @Transactional
        public void checkedEx() throws MyEx {
            log.debug("call checkedEx()");
            throw new MyEx();
        }

        // 체크예외 = 롤백(rollbackFor 사용)
        @Transactional(rollbackFor = MyEx.class)
        public void checkedExWithRollbackFor() throws MyEx {
            log.debug("call checkedExWithRollbackFor()");
            throw new MyEx();
        }

    }

    static class MyEx extends Exception {
    }

}
