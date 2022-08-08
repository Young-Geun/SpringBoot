package choi.web.springboot.apicontroller;

import choi.web.springboot.common.ResponseObject;
import choi.web.springboot.domain.Board;
import choi.web.springboot.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiBoardController {

    private final BoardService boardService;

    @PostMapping("/api/boards")
    public ResponseEntity findAll(Board board,
                                  @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                                  @RequestParam(required = false, defaultValue = "1", value = "range") int range) {
        ResponseObject response = null;
        try {
            Page<Board> boardList = boardService.findByKeyword(page, board);
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
