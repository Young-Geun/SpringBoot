package choi.web.springboot.tx;


import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
@ActiveProfiles(value = "mac")
public class PropagationTest {

    @Autowired
    PlatformTransactionManager txManager;

    @TestConfiguration
    static class Config {
        /*
            스프링부트가 TransactionManager을 자동으로 등록하지만,
            사용자가 직접 아래처럼 구현을 한다면 해당 TransactionManager를 사용한다.
         */
        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }

    @Test
    void commit() {
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());
        txManager.commit(status);
        /*
            Creating new transaction
            Initiating transaction commit
         */
    }

    @Test
    void rollback() {
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());
        txManager.rollback(status);
        /*
            Creating new transaction
            Initiating transaction rollback
         */
    }

    @Test
    void commit_commit() {
        log.debug("Tx_1 시작");
        TransactionStatus status1 = txManager.getTransaction(new DefaultTransactionAttribute());
        txManager.commit(status1);
        log.debug("Tx_1 종료");
        /*
            Tx_1 시작
            Creating new transaction
            Initiating transaction commit
            Tx_1 종료
         */

        log.debug("Tx_2 시작");
        TransactionStatus status2 = txManager.getTransaction(new DefaultTransactionAttribute());
        txManager.commit(status2);
        log.debug("Tx_2 종료");
        /*
            Tx_2 시작
            Creating new transaction
            Initiating transaction commit
            Tx_2 종료
         */
    }

    @Test
    void commit_rollback() {
        log.debug("Tx_1 시작");
        TransactionStatus status1 = txManager.getTransaction(new DefaultTransactionAttribute());
        txManager.commit(status1);
        log.debug("Tx_1 종료");
        /*
            Tx_1 시작
            Creating new transaction
            Initiating transaction commit
            Tx_1 종료
         */

        log.debug("Tx_2 시작");
        TransactionStatus status2 = txManager.getTransaction(new DefaultTransactionAttribute());
        txManager.rollback(status2);
        log.debug("Tx_2 종료");
        /*
            Tx_2 시작
            Creating new transaction
            Initiating transaction rollback
            Tx_2 종료
         */
    }

    @Test
    void inner_commit_commit() {
        log.debug("Tx_1 시작");
        TransactionStatus status1 = txManager.getTransaction(new DefaultTransactionAttribute());

        log.debug("Tx_2 시작");
        TransactionStatus status2 = txManager.getTransaction(new DefaultTransactionAttribute());
        txManager.commit(status2);
        log.debug("Tx_2 종료");

        txManager.commit(status1);
        log.debug("Tx_1 종료");
        /*
            Tx_1 시작
            Creating new transaction
            Tx_2 시작
            Participating in existing transaction
            Tx_2 종료
            Initiating transaction commit
            Tx_1 종료

            --> 두 번째 트랜잭션은 새로운 트랜잭션을 생성하지 않고, 기존 트랜잭션에 참여한다.(Participating in existing transaction)
                두 번째 트랜잭션은 commit없이 종료하고 첫 번째 트랜잭션만 commit된다.
         */
    }

    @Test
    void inner_commit_rollback() {
        log.debug("Tx_1 시작");
        TransactionStatus status1 = txManager.getTransaction(new DefaultTransactionAttribute());

        log.debug("Tx_2 시작");
        TransactionStatus status2 = txManager.getTransaction(new DefaultTransactionAttribute());
        txManager.rollback(status2);
        log.debug("Tx_2 종료");

        Assertions.assertThatThrownBy(() -> txManager.commit(status1))
                .isInstanceOf(UnexpectedRollbackException.class);
        log.debug("Tx_1 종료");
        /*
            Tx_1 시작
            Creating new transaction
            Tx_2 시작
            Participating in existing transaction
            Participating transaction failed - marking existing transaction as rollback-only
            Tx_2 종료
            Initiating transaction rollback
            Tx_1 종료

            --> 두 번째 트랜잭션은 새로운 트랜잭션을 생성하지 않고, 기존 트랜잭션에 참여한다.(Participating in existing transaction)
                두 번째 트랜잭션은 rollback 마킹한다.(Participating transaction failed - marking existing transaction as rollback-only)
                첫 번째 트랜잭션은 commit하려고 하지만 이전에 롤백 마킹에 의해 오류가 발생한다.
         */
    }

    @Test
    void inner_commit_rollback_with_require_new() {
        log.debug("Tx_1 시작");
        TransactionStatus status1 = txManager.getTransaction(new DefaultTransactionAttribute());

        log.debug("Tx_2 시작");
        DefaultTransactionAttribute attr = new DefaultTransactionAttribute();
        attr.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status2 = txManager.getTransaction(attr);
        txManager.rollback(status2);
        log.debug("Tx_2 종료");

        txManager.commit(status1);
        log.debug("Tx_1 종료");
        /*
            Tx_1 시작
            Creating new transaction
            Tx_2 시작
            Suspending current transaction, creating new transaction
            Initiating transaction rollback
            Tx_2 종료
            Initiating transaction commit
            Tx_1 종료

            --> 첫 번째 트랜잭션 생성
                두 번째 트랜잭션 생성(첫 번째 트랜재션은 잠시 지연시킨다.) - Suspending current transaction, creating new transaction
                두 번째 트랜잭션은 rollback 된다.
                첫 번째 트랜잭션은 두 번째 트랜잭션의 롤백과 상관없이 commit된다.
         */
    }

}
