package choi.web.springboot.service;

import choi.web.springboot.domain.Sample;
import choi.web.springboot.repository.mybatismain.SampleMainRepository;
import choi.web.springboot.repository.mybatissub.SampleSubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class MybatisService {

    private final SampleMainRepository sampleMainRepository;

    private final SampleSubRepository sampleSubRepository;

    public Sample findByFirstRow() {
        return sampleMainRepository.findByFirstRow();
    }

    public int insert(Sample sample) {
        return sampleMainRepository.insert(sample);
    }

    public int update(Sample sample) {
        return sampleMainRepository.update(sample);
    }

    public Map multiDatasource() {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("mainResult", sampleMainRepository.findResult());
        resultMap.put("subResult", sampleSubRepository.findResult());
        return resultMap;
    }
}
