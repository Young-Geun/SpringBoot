package choi.web.springboot.scheduler;

import choi.web.springboot.domain.Member;
import choi.web.springboot.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final MemberService memberService;

    @Scheduled(cron = "0 0 9 * * *") // 매일 9시에 실행
    public void disableMember() {
        List<Member> memberList = memberService.findByMemberStatus("Y");
        for (Member member : memberList) {
            if (member.getLastLoginDate().isBefore(LocalDateTime.now().minusDays(30))) {
                memberService.updateStatus(member, "N");
                log.info("Disable Member : {}", member.getMemberEmail());
            }
        }
    }

}
