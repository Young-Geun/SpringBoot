package choi.web.springboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimeTraceAop {

    @Around("execution(* choi.web.springboot..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            log.info("Execution time[{}_{}()] : {}ms",
                    joinPoint.getTarget().getClass().getName(),
                    joinPoint.getSignature().getName(),
                    (System.currentTimeMillis() - startTime));
        }
    }

}
