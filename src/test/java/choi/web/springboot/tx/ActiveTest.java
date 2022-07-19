package choi.web.springboot.tx;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
@ActiveProfiles(value = "mac")
public class ActiveTest {

    @Autowired
    TxService txService;

    @Test
    void callTx() {
        assertEquals(txService.tx(), true);
    }

    @Test
    void callNonTx() {
        assertEquals(txService.nonTx(), false);
    }

    @TestConfiguration
    static class TxConfig {
        @Bean
        TxService txService() {
            return new TxService();
        }
    }


    static class TxService {

        @Transactional
        public boolean tx() {
            boolean isActual = TransactionSynchronizationManager.isActualTransactionActive();
            log.debug("call tx() : {}", isActual);
            return isActual;
        }

        public boolean nonTx() {
            boolean isActual = TransactionSynchronizationManager.isActualTransactionActive();
            log.debug("call nonTx() : {}", isActual);
            return isActual;
        }

    }

}
