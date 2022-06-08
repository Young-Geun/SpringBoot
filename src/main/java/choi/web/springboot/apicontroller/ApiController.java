
package choi.web.springboot.apicontroller;

import choi.web.springboot.domain.Test;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Slf4j
@Controller
public class ApiController {

    @GetMapping("/api/client")
    public @ResponseBody String client() {
        try {
            // uri 주소 생성
            URI uri = UriComponentsBuilder
                    .fromUriString("http://localhost:8084")
                    .path("/api/server")
                    .encode()
                    .build()
                    .toUri();

            Test test = new Test(1, "api test", 1, 2, 3, 4);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Test> response = restTemplate.postForEntity(uri, test, Test.class);

            log.info("[client] StatusCode : {}", response.getStatusCode());
            log.info("[client] Headers : {}", response.getHeaders());
            log.info("[client] Body : {}", response.getBody());
        } catch (Exception e) {
            log.error("client error : {}", e);
        }

        return "success";
    }

    @PostMapping("/api/server")
    public @ResponseBody Test server(@RequestBody Test test) {
        log.info("[server] Before processing : {}", test);
        test.setTestName(test.getTestName() + " => done");
        test.setTestNum1(test.getTestNum1() * 2);
        test.setTestNum2(test.getTestNum2() * 2);
        test.setTestNum3(test.getTestNum3() * 2);
        log.info("[server] After processing : {}", test);
        return test;
    }

}
