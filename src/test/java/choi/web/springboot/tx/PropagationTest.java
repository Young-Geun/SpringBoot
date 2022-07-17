package choi.web.springboot.tx;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;

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
            Initiating transaction commit
         */
    }

    @Test
    void rollback() {
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());
        txManager.rollback(status);
        /*
            Initiating transaction rollback
         */
    }

}
