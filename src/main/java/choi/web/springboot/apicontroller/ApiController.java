package choi.web.springboot.apicontroller;

import choi.web.springboot.domain.common.ResponseData;
import choi.web.springboot.domain.Test;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
public class ApiController {

    @GetMapping("/api/client")
    public String client(Model model) {
        try {
            // uri 주소 생성
            URI uri = UriComponentsBuilder
                    .fromUriString("http://localhost:8084")
                    .path("/api/server")
                    .encode()
                    .build()
                    .toUri();
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ResponseData> response = restTemplate.getForEntity(uri, ResponseData.class);

            log.info("[client] StatusCode : {}", response.getStatusCode());
            log.info("[client] Headers : {}", response.getHeaders());
            log.info("[client] Body : {}", response.getBody());

            model.addAttribute("result", response.getBody());
        } catch (Exception e) {
            log.error("client error : {}", e);
        }

        return "sample/api";
    }

    @GetMapping("/api/server")
    public @ResponseBody ResponseData server() {
        List<Test> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Test test = new Test();
            test.setTestId(i + 100);
            test.setTestName("test_" + i);

            list.add(test);
        }

        ResponseData result = new ResponseData();
        result.setCode("C001");
        result.setMessage("SUCCESS");
        result.setData(list);
        log.info("[server] result : {}", result);

        return result;
    }

}
