package choi.web.springboot.apicontroller;

import choi.web.springboot.service.BucketService;
import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Slf4j
@RestController
public class BucketController {

    private final BucketService bucketService;

    @GetMapping("/api/bucket/access")
    public ResponseEntity bucketAccess(HttpServletRequest request) {
        Bucket bucket = bucketService.resolveBucket(request);
        log.info("접근 IP = {}", request.getRemoteAddr());

        if (bucket.tryConsume(1)) { // 1개 사용 요청
            return ResponseEntity.ok("[정상응답] 잔여토큰 : " + bucket.getAvailableTokens());
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("[비정상응답] 트래픽 초과");
        }
    }

}
