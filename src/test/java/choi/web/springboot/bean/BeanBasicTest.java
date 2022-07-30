package choi.web.springboot.bean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "mac")
class BeanBasicTest {

    @Test
    void 빈_등록방식_기본() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

        // A가 'beanA'로 등록된다.
        A a = applicationContext.getBean("beanA", A.class);
        a.helloA(); // hello A

        // B는 빈으로 등록되지 않는다.
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(B.class));
    }

    static class BasicConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }
    }

    static class A {
        public void helloA() {
            System.out.println("hello A");
        }
    }

    static class B {
        public void helloB() {
            System.out.println("hello B");
        }
    }

}