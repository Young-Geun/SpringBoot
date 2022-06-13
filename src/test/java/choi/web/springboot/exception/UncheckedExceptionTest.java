package choi.web.springboot.exception;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles(value = "window")
class UncheckedExceptionTest {

    @Test
    void callCatch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void callThrow() {
        Service service = new Service();
        Assertions.assertThatThrownBy(() -> service.callThrow())
                .isInstanceOf(MyUncheckedException.class);
    }

    static class Service {
        Repository repository = new Repository();

        // 예외를 직접 처리한다.
        public void callCatch() {
            try {
                repository.call();
            } catch (MyUncheckedException e) {
                System.out.println("예외처리 : " + e);
            }
        }

        // 예외 책임을 전가한다. (throws를 명시적으로 표기하지 않아도 책임을 전가시킨다.)
        public void callThrow() /* throws MyUncheckedException */ {
            repository.call();
        }
    }

    static class Repository {
        public void call() throws MyUncheckedException {
            throw new MyUncheckedException("예외발생");
        }
    }

    /**
     * RuntimeException 상속받을 경우, Unchecked Exception이 된다.
     * Unchecked Exception은 반드시 처리할 필요가 없으며, 예외처리를 하지 않으면 자동으로 책임을 전가시킨다.
     */
    static class MyUncheckedException extends RuntimeException {
        public MyUncheckedException(String message) {
            super(message);
        }
    }


}