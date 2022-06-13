package choi.web.springboot.exception;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles(value = "window")
class CheckedExceptionTest {

    @Test
    void callCatch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void callThrow() {
        Service service = new Service();
        Assertions.assertThatThrownBy(() -> service.callThrow())
                .isInstanceOf(MyCheckedException.class);
    }

    static class Service {
        Repository repository = new Repository();

        // 예외를 직접 처리한다.
        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                System.out.println("예외처리 : " + e);
            }
        }

        // 예외 책임을 전가한다.
        public void callThrow() throws MyCheckedException {
            repository.call();
        }
    }

    static class Repository {
        public void call() throws MyCheckedException {
            throw new MyCheckedException("예외발생");
        }
    }

    /**
     * Exception을 상속받을 경우, Checked Exception이 된다.
     * Checked Exception은 반드시 처리해야한다.(그렇지 않을 경우, 컴파일 오류 발생)
     */
    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }


}