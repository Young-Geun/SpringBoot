package choi.web.springboot.service;

import choi.web.springboot.domain.Messages;
import choi.web.springboot.repository.MessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessagesService {

    private final MessagesRepository messagesRepository;

    public Page<Messages> selectAll(int page, long memberId) {
        return messagesRepository.findByRecvId(memberId, PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "messagesId")));
    }

}
