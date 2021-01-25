package com.choi.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LoggerAspect {

	@Around("execution(* com.choi..dao.*Dao.*(..))")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object joinPointProceed = joinPoint.proceed();
		
		stopWatch.stop();
		
		System.out.println("[LoggerAspect] " + joinPoint.getSignature().getName() + "() run : " + stopWatch.getTotalTimeMillis() + " ms");
		
		return joinPointProceed;
	}

}