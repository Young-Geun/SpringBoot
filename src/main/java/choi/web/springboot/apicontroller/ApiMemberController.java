package choi.web.springboot.apicontroller;

import choi.web.springboot.common.ResponseObject;
import choi.web.springboot.common.SystemCode;
import choi.web.springboot.domain.Member;
import choi.web.springboot.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ApiMemberController {

    private final MemberService memberService;

    @GetMapping("/api/members")
    public ResponseEntity<EntityModel<ResponseObject>> findAll() {
        ResponseObject response = null;
        try {
            List<EntityModel<Member>> findMembers = memberService.findAll().stream().map(member -> {
                return EntityModel.of(member,
                        linkTo(methodOn(ApiMemberController.class).findById(member.getMemberId())).withRel("detail"));
            }).collect(Collectors.toList());
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

        return ResponseEntity.ok().body(
                EntityModel
                        .of(response)
                        .add(linkTo(methodOn(ApiMemberController.class).findAll()).withSelfRel())
        );
    }

    @GetMapping("/api/members/{memberId}")
    public ResponseEntity<EntityModel<ResponseObject>> findById(@PathVariable long memberId) {
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

        return ResponseEntity.ok().body(
                EntityModel
                        .of(response)
                        // .add(linkTo(methodOn(ApiMemberController.class).findById(memberId)).withRel("detail"))
                        .add(linkTo(methodOn(ApiMemberController.class).findById(memberId)).withSelfRel())
                        .add(linkTo(methodOn(ApiMemberController.class).findAll()).withRel("list"))
        );
    }

    @PostMapping("/api/members")
    public ResponseEntity<EntityModel<ResponseObject>> save(@RequestBody @Validated Member member, BindingResult bindingResult) {
        ResponseObject response = null;
        String resultCode;
        String resultMsg;
        try {
            if (bindingResult.hasErrors()) {
                log.error("member save Error - {}", bindingResult.getFieldError());
                resultCode = SystemCode.FAIL_INVALID_VALUE.getCode();
                resultMsg = SystemCode.FAIL_INVALID_VALUE.getMessage();
            } else {
                int result = memberService.insert(member);
                if (result == 0) {
                    resultCode = SystemCode.FAIL_INSERT.getCode();
                    resultMsg = SystemCode.FAIL_INSERT.getMessage();
                } else if (result == -1) {
                    resultCode = SystemCode.FAIL_ALREADY_EXIST_VALUE.getCode();
                    resultMsg = SystemCode.FAIL_ALREADY_EXIST_VALUE.getMessage();
                } else {
                    resultCode = SystemCode.SUCCESS_COMMON.getCode();
                    resultMsg = SystemCode.SUCCESS_COMMON.getMessage();
                }
            }

            response = ResponseObject.builder()
                    .responseCode(resultCode)
                    .responseMsg(resultMsg)
                    .build();

        } catch (Exception e) {
            response = ResponseObject.builder()
                    .responseCode(SystemCode.ERROR_COMMON.getCode())
                    .responseMsg(SystemCode.ERROR_COMMON.getMessage())
                    .build();
        }

        return ResponseEntity.ok().body(
                EntityModel
                        .of(response)
                        .add(linkTo(methodOn(ApiMemberController.class).findById(member.getMemberId())).withRel("detail"))
                        .add(linkTo(methodOn(ApiMemberController.class).findAll()).withRel("list"))
        );
    }

}
