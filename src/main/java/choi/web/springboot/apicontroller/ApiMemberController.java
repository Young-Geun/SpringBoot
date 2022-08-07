package choi.web.springboot.apicontroller;

import choi.web.springboot.common.ResponseObject;
import choi.web.springboot.domain.Member;
import choi.web.springboot.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiMemberController {

    private final MemberService memberService;

    @GetMapping("/api/members")
    public ResponseEntity findAll() {
        return ResponseEntity.ok().body(memberService.findAll());
    }

    @GetMapping("/api/members/{memberId}")
    public ResponseEntity findById(@PathVariable long memberId) {
        ResponseObject response = null;
        Member findMember = memberService.findByMemberId(memberId);
        if (findMember == null) {
            response = ResponseObject.builder()
                    .responseCode("9999")
                    .responseMsg("검색결과가 없습니다.")
                    .build();

        } else {
            response = ResponseObject.builder()
                    .responseCode("0000")
                    .responseMsg("성공하였습니다.")
                    .responseData(findMember)
                    .build();
        }

        return ResponseEntity.ok().body(response);
    }

}
