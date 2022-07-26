package choi.web.springboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SampleService {

    static int accessCount = 1;

    public int retry() {
        return accessCount++;
    }

}
