package choi.web.springboot.service;

import choi.web.springboot.domain.AccessHistory;
import choi.web.springboot.repository.AccessHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AccessHistoryService {

    private final AccessHistoryRepository accessHistoryRepository;

    public List<Map<String, Object>> findLoginHist() {
        return accessHistoryRepository.findLoginHist();
    }

    @Transactional
    public void save(AccessHistory accessHistory) {
        accessHistoryRepository.save(accessHistory);
    }

}
