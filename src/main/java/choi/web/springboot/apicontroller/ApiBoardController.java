package choi.web.springboot.apicontroller;

import choi.web.springboot.common.ResponseObject;
import choi.web.springboot.common.SystemCode;
import choi.web.springboot.domain.Board;
import choi.web.springboot.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ApiBoardController {

    private final BoardService boardService;

    @PostMapping("/api/boards")
    public ResponseEntity findAll(Board board,
                                  @RequestParam(required = false, defaultValue = "0", value = "page") int page) {
        ResponseObject response = null;
        try {
            Page<Board> boardList = boardService.findByKeyword(page, board);
            if (boardList == null) {
                response = ResponseObject.builder()
                        .responseCode(SystemCode.FAIL_SEARCH.getCode())
                        .responseMsg(SystemCode.FAIL_SEARCH.getMessage())
                        .build();
            } else {
                response = ResponseObject.builder()
                        .responseCode(SystemCode.SUCCESS_COMMON.getCode())
                        .responseMsg(SystemCode.SUCCESS_COMMON.getMessage())
                        .responseData(boardList)
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

    @GetMapping("/api/board/{boardId}")
    public ResponseEntity findAll(@PathVariable long boardId) {
        ResponseObject response = null;
        try {
            Board findBoard = boardService.findById(boardId);
            if (findBoard == null) {
                response = ResponseObject.builder()
                        .responseCode(SystemCode.FAIL_SEARCH.getCode())
                        .responseMsg(SystemCode.FAIL_SEARCH.getMessage())
                        .build();
            } else {
                response = ResponseObject.builder()
                        .responseCode(SystemCode.SUCCESS_COMMON.getCode())
                        .responseMsg(SystemCode.SUCCESS_COMMON.getMessage())
                        .responseData(findBoard)
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
