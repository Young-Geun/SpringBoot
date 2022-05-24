package choi.web.springboot.service;

import choi.web.springboot.mybatisrepository.MainRepository;
import choi.web.springboot.mybatisrepository.SubRepository;
import choi.web.springboot.domain.Sample;
import choi.web.springboot.mybatisrepository.MybatisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class MybatisService {

    private final MybatisRepository mybatisRepository;

    private final MainRepository mainRepository;

    private final SubRepository subRepository;

    public Sample findByFirstRow() {
        return mybatisRepository.findByFirstRow();
    }

    public int update(Sample sample) {
        return mybatisRepository.update(sample);
    }

    public Map multiDatasource() {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("mainResult", mainRepository.selectMainResult());
        resultMap.put("subResult", subRepository.selectSubResult());
        return resultMap;
    }
}
