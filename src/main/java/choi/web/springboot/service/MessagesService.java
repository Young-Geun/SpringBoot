package choi.web.springboot.service;

import choi.web.springboot.domain.Messages;
import choi.web.springboot.repository.MessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MessagesService {

    private final MessagesRepository messagesRepository;

    public List<Messages> selectAll(long recvId) {
        return messagesRepository.findByRecvId(recvId);
    }

}
