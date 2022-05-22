package choi.web.springboot.service;

import choi.web.springboot.dao.MainDao;
import choi.web.springboot.dao.SubDao;
import choi.web.springboot.domain.Sample;
import choi.web.springboot.repository.MybatisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MybatisService {

    private final MybatisRepository mybatisRepository;

    private final MainDao MainDao;

    private final SubDao subDao;

    public Sample findByFirstRow() {
        return mybatisRepository.findByFirstRow();
    }

    public int update(Sample sample) {
        return mybatisRepository.update(sample);
    }

    public void dbConnect() {
        String mainResult = MainDao.selectMainResult();
        String subResult = subDao.selectSubResult();
        log.info("[mainResult = {}] , [subResult = {}]", mainResult, subResult);
    }
}
