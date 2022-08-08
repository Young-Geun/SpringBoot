package choi.web.springboot.apicontroller;

import choi.web.springboot.common.ResponseObject;
import choi.web.springboot.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiBoardController {

    private final BoardRepository boardRepository;

    @GetMapping("/api/boards/{page}")
    public ResponseEntity findAll(@PathVariable int page) {
        ResponseObject response = null;
        try {
            Page boardList = boardRepository.findAll(PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "boardId")));
            if (boardList == null) {
                response = ResponseObject.builder()
                        .responseCode("9999")
                        .responseMsg("검색결과가 없습니다.")
                        .build();
            } else {
                response = ResponseObject.builder()
                        .responseCode("0000")
                        .responseMsg("성공하였습니다.")
                        .responseData(boardList)
                        .build();
            }
        } catch (Exception e) {
            response = ResponseObject.builder()
                    .responseCode("9999")
                    .responseMsg("오류가 발생하였습니다.")
                    .build();
        }

        return ResponseEntity.ok().body(response);
    }

}
