package choi.web.springboot.aop;

import choi.web.springboot.aop.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class RetryAop {

    @Around("@annotation(retry)")
    public Object execute(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        Exception exceptionHolder = null;
        for (int i = 0; i < retry.value(); i++) {
            try {
                return joinPoint.proceed();
            } catch (Exception e) {
                log.info("Retry Count = {}", (i + 1));
                exceptionHolder = e;
            }
        }
        throw exceptionHolder;
    }

}
