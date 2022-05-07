package choi.web.springboot.service;

import choi.web.springboot.domain.Sample;
import choi.web.springboot.repository.MybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MybatisService {

    private final MybatisRepository mybatisRepository;

    public Sample findByFirstRow() {
        return mybatisRepository.findByFirstRow();
    }

    public int update(Sample sample) {
        return mybatisRepository.update(sample);
    }

}
