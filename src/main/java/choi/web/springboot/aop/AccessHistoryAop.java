package choi.web.springboot.aop;

import choi.web.springboot.domain.AccessHistory;
import choi.web.springboot.domain.Member;
import choi.web.springboot.service.AccessHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AccessHistoryAop {

    private final AccessHistoryService accessHistoryService;

    @Around("execution(* choi.web.springboot.controller.*.*(..)) && !execution(* choi.web.springboot.controller.MemberController.profile(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } finally {
            // 접근이력 등록
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            HttpSession session = request.getSession();

            if (session != null && session.getAttribute("loginMember") != null) {
                AccessHistory accessHistory = AccessHistory.builder()
                        .accessMemberId(((Member) request.getSession().getAttribute("loginMember")).getMemberId())
                        .accessPath(request.getRequestURI())
                        .accessDate(LocalDateTime.now())
                        .build();

                accessHistoryService.save(accessHistory);
            }
        }
    }

}
