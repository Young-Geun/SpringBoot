package choi.web.springboot.exception;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;


@SpringBootTest
@ActiveProfiles(value = "mac")
class StackTrace {

    private final static Logger log = LoggerFactory.getLogger(StackTrace.class);

    @Test
    void excute() {
        Service service = new Service();

        // 기존 예외 포함.
        try {
            service.include();
        } catch (MyRuntimeException e) {
            log.info("[기존 예외 포함 로그]", e);
            /*
                [기존 예외 포함 로그]
                choi.web.springboot.exception.StackTrace$MyRuntimeException: java.sql.SQLException: SQL 오류발생
                    at choi.web.springboot.exception.StackTrace$Service.include(StackTrace.java:47) ~[test/:na]
                    at choi.web.springboot.exception.StackTrace.excute(StackTrace.java:26) ~[test/:na]
                    ...
                Caused by: java.sql.SQLException: SQL 오류발생
                    at choi.web.springboot.exception.StackTrace$Repository.call(StackTrace.java:63) ~[test/:na]     // 호출된 메서드를 확인할 수 있다. : Repository.call()
                    at choi.web.springboot.exception.StackTrace$Service.include(StackTrace.java:45) ~[test/:na]     // 호출된 메서드를 확인할 수 있다. : Service.include()
                    ...
             */
        }



        // 기존 예외 미포함.
        try {
            service.exclude1();
        } catch (MyRuntimeException e) {
            log.info("[기존 예외 미포함 로그]", e);
            /*
                [기존 예외 미포함 로그]
                choi.web.springboot.exception.StackTrace$MyRuntimeException: null
                    at choi.web.springboot.exception.StackTrace$Service.exclude1(StackTrace.java:74) ~[test/:na]
                    at choi.web.springboot.exception.StackTrace.excute(StackTrace.java:44) ~[test/:na]
                    ...
             */
        }



        // 기존 예외 미포함(메시지만 표시).
        try {
            service.exclude2();
        } catch (MyRuntimeException e) {
            log.info("[기존 예외 미포함(메시지만 표시) 로그]", e);
            /*
                [기존 예외 미포함(메시지만 표시) 로그]
                choi.web.springboot.exception.StackTrace$MyRuntimeException: java.sql.SQLException: SQL 오류발생
                    at choi.web.springboot.exception.StackTrace$Service.exclude2(StackTrace.java:83) ~[test/:na]
                    at choi.web.springboot.exception.StackTrace.excute(StackTrace.java:51) ~[test/:na]
                    ...
             */
        }
    }

    static class Service {
        Repository repository = new Repository();

        // 기존 예외 포함.
        public void include() {
            try {
                repository.call();
            } catch (SQLException e) {
                throw new MyRuntimeException(e); // 예외를 전환할 때는 기존 예외를 반드시 포함해야한다.
            }
        }

        // 기존 예외 미포함.
        public void exclude1() {
            try {
                repository.call();
            } catch (SQLException e) {
                throw new MyRuntimeException();
            }
        }

        // 기존 예외 미포함(메시지만 표시).
        public void exclude2() {
            try {
                repository.call();
            } catch (SQLException e) {
                throw new MyRuntimeException(e.toString());
            }
        }
    }

    static class Repository {
        public void call() throws SQLException {
            throw new SQLException("SQL 오류발생");
        }
    }

    /**
     * Throwable을 사용하면 기존 오류를 가지고 있어서
     * 로그를 통해 오류를 구체적으로 파악할 수 있다.
     */
    static class MyRuntimeException extends RuntimeException {
        public MyRuntimeException() {
        }

        public MyRuntimeException(String message) {
            super(message);
        }

        public MyRuntimeException(Throwable cause) {
            super(cause);
        }
    }


}