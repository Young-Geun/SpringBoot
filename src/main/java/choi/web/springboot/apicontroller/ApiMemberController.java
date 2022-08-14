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
        List<EntityModel<Member>> findMembers = null;
        String resultCode;
        String resultMsg;
        try {
            findMembers = memberService.findAll().stream().map(member -> {
                return EntityModel.of(member,
                        linkTo(methodOn(ApiMemberController.class).findById(member.getMemberId())).withRel("detail"));
            }).collect(Collectors.toList());
            if (findMembers == null) {
                resultCode = SystemCode.FAIL_SEARCH.getCode();
                resultMsg = SystemCode.FAIL_SEARCH.getMessage();
            } else {
                resultCode = SystemCode.SUCCESS_COMMON.getCode();
                resultMsg = SystemCode.SUCCESS_COMMON.getMessage();
            }
        } catch (Exception e) {
            resultCode = SystemCode.ERROR_COMMON.getCode();
            resultMsg = SystemCode.ERROR_COMMON.getMessage();
        }

        return ResponseEntity.ok().body(
                EntityModel
                        .of(buildResponseObject(resultCode, resultMsg, findMembers))
                        .add(linkTo(methodOn(ApiMemberController.class).findAll()).withSelfRel())
        );
    }

    @GetMapping("/api/members/{memberId}")
    public ResponseEntity<EntityModel<ResponseObject>> findById(@PathVariable long memberId) {
        Member findMember = null;
        String resultCode;
        String resultMsg;
        try {
            findMember = memberService.findByMemberId(memberId);
            if (findMember == null) {
                resultCode = SystemCode.FAIL_SEARCH.getCode();
                resultMsg = SystemCode.FAIL_SEARCH.getMessage();
            } else {
                resultCode = SystemCode.SUCCESS_COMMON.getCode();
                resultMsg = SystemCode.SUCCESS_COMMON.getMessage();
            }
        } catch (Exception e) {
            resultCode = SystemCode.ERROR_COMMON.getCode();
            resultMsg = SystemCode.ERROR_COMMON.getMessage();
        }

        return ResponseEntity.ok().body(
                EntityModel
                        .of(buildResponseObject(resultCode, resultMsg, findMember))
                        .add(linkTo(methodOn(ApiMemberController.class).findById(memberId)).withSelfRel())
                        .add(linkTo(methodOn(ApiMemberController.class).findAll()).withRel("list"))
        );
    }

    @PostMapping("/api/members")
    public ResponseEntity<EntityModel<ResponseObject>> save(@RequestBody @Validated Member member, BindingResult bindingResult) {
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
        } catch (Exception e) {
            resultCode = SystemCode.ERROR_COMMON.getCode();
            resultMsg = SystemCode.ERROR_COMMON.getMessage();
        }

        return ResponseEntity.ok().body(
                EntityModel
                        .of(buildResponseObject(resultCode, resultMsg, member))
                        .add(linkTo(methodOn(ApiMemberController.class).findById(member.getMemberId())).withRel("detail"))
                        .add(linkTo(methodOn(ApiMemberController.class).findAll()).withRel("list"))
        );
    }

    @PutMapping("/api/members/{memberId}")
    public ResponseEntity<EntityModel<ResponseObject>> update(@PathVariable long memberId,
                                                              @RequestBody @Validated Member member, BindingResult bindingResult) {
        String resultCode;
        String resultMsg;
        try {
            if (bindingResult.hasErrors()) {
                log.error("member update Error - {}", bindingResult.getFieldError());
                resultCode = SystemCode.FAIL_INVALID_VALUE.getCode();
                resultMsg = SystemCode.FAIL_INVALID_VALUE.getMessage();
            } else {
                Member findMember = memberService.findByMemberId(memberId);
                if (findMember == null) {
                    resultCode = SystemCode.FAIL_SEARCH.getCode();
                    resultMsg = SystemCode.FAIL_SEARCH.getMessage();
                } else {
                    member.setMemberId(memberId);
                    int result = memberService.update(member, findMember, null);
                    if (result == 0) {
                        resultCode = SystemCode.FAIL_UPDATE.getCode();
                        resultMsg = SystemCode.FAIL_UPDATE.getMessage();
                    } else {
                        resultCode = SystemCode.SUCCESS_COMMON.getCode();
                        resultMsg = SystemCode.SUCCESS_COMMON.getMessage();
                    }
                }
            }
        } catch (Exception e) {
            resultCode = SystemCode.ERROR_COMMON.getCode();
            resultMsg = SystemCode.ERROR_COMMON.getMessage();
        }

        return ResponseEntity.ok().body(
                EntityModel
                        .of(buildResponseObject(resultCode, resultMsg, member))
                        .add(linkTo(methodOn(ApiMemberController.class).findById(memberId)).withRel("detail"))
                        .add(linkTo(methodOn(ApiMemberController.class).findAll()).withRel("list"))
        );
    }

    @DeleteMapping("/api/members/{memberId}")
    public ResponseEntity<EntityModel<ResponseObject>> delete(@PathVariable long memberId) {
        Member findMember = null;
        String resultCode;
        String resultMsg;
        try {
            findMember = memberService.findByMemberId(memberId);
            if (findMember == null) {
                resultCode = SystemCode.FAIL_SEARCH.getCode();
                resultMsg = SystemCode.FAIL_SEARCH.getMessage();
            } else {
                memberService.updateStatus(findMember, "N");
                resultCode = SystemCode.SUCCESS_COMMON.getCode();
                resultMsg = SystemCode.SUCCESS_COMMON.getMessage();
            }
        } catch (Exception e) {
            resultCode = SystemCode.ERROR_COMMON.getCode();
            resultMsg = SystemCode.ERROR_COMMON.getMessage();
        }

        return ResponseEntity.ok().body(
                EntityModel
                        .of(buildResponseObject(resultCode, resultMsg, findMember))
                        .add(linkTo(methodOn(ApiMemberController.class).findById(memberId)).withRel("detail"))
                        .add(linkTo(methodOn(ApiMemberController.class).findAll()).withRel("list"))
        );
    }

    private ResponseObject buildResponseObject(String resultCode, String resultMsg, Object resultObj) {
        return ResponseObject.builder()
                .responseCode(resultCode)
                .responseMsg(resultMsg)
                .responseData(resultCode.equals(SystemCode.SUCCESS_COMMON.getCode()) ? resultObj : null)
                .build();
    }

}
