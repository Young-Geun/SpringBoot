package choi.web.springboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    private Logger logger = LoggerFactory.getLogger(TimeTraceAop.class);

    @Around("execution(* choi.web.springboot..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            logger.info("Execution time[{}_{}()] : {}ms",
                    joinPoint.getTarget().getClass().getName(),
                    joinPoint.getSignature().getName(),
                    (System.currentTimeMillis() - startTime));
        }
    }

}
