package choi.web.springboot.apicontroller;

import choi.web.springboot.common.ResponseObject;
import choi.web.springboot.common.SystemCode;
import choi.web.springboot.domain.Member;
import choi.web.springboot.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ApiMemberController {

    private final MemberService memberService;

    @GetMapping("/api/members")
    public ResponseEntity findAll() {
        ResponseObject response = null;
        try {
            List<Member> findMembers = memberService.findAll();
            if (findMembers == null) {
                response = ResponseObject.builder()
                        .responseCode(SystemCode.FAIL_SEARCH.getCode())
                        .responseMsg(SystemCode.FAIL_SEARCH.getMessage())
                        .build();
            } else {
                response = ResponseObject.builder()
                        .responseCode(SystemCode.SUCCESS_COMMON.getCode())
                        .responseMsg(SystemCode.SUCCESS_COMMON.getMessage())
                        .responseData(findMembers)
                        .build();
            }
        } catch (Exception e) {
            response = ResponseObject.builder()
                    .responseCode(SystemCode.ERROR_COMMON.getCode())
                    .responseMsg(SystemCode.ERROR_COMMON.getMessage())
                    .build();
        }

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/members/{memberId}")
    public ResponseEntity findById(@PathVariable long memberId) {
        ResponseObject response = null;
        try {
            Member findMember = memberService.findByMemberId(memberId);
            if (findMember == null) {
                response = ResponseObject.builder()
                        .responseCode(SystemCode.FAIL_SEARCH.getCode())
                        .responseMsg(SystemCode.FAIL_SEARCH.getMessage())
                        .build();

            } else {
                response = ResponseObject.builder()
                        .responseCode(SystemCode.SUCCESS_COMMON.getCode())
                        .responseMsg(SystemCode.SUCCESS_COMMON.getMessage())
                        .responseData(findMember)
                        .build();
            }
        } catch (Exception e) {
            response = ResponseObject.builder()
                    .responseCode(SystemCode.ERROR_COMMON.getCode())
                    .responseMsg(SystemCode.ERROR_COMMON.getMessage())
                    .build();
        }

        return ResponseEntity.ok().body(response);
    }

}
