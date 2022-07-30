package choi.web.springboot.bean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "mac")
class BeanPostProcessorTest {

    @Test
    void 빈_등록방식_후처리() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PostProcessorConfig.class);

        // 빈 후처리기에 의하여 B가 'beanA'로 등록된다.
        B b = applicationContext.getBean("beanA", B.class);
        b.helloB(); // hello B

        // A는 빈으로 등록되지 않는다.
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(A.class));
    }

    static class PostProcessor implements BeanPostProcessor {

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            System.out.println("call postProcessAfterInitialization()");
            if (bean instanceof A) {
                return  new B();
            }
            return bean;
        }
    }

    static class PostProcessorConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }

        @Bean
        public PostProcessor postProcessor() {
            return new PostProcessor();
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