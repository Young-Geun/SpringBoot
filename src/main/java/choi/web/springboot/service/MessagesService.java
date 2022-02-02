package choi.web.springboot.service;

import choi.web.springboot.domain.Messages;
import choi.web.springboot.repository.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;

    public List<Messages> selectAll(Messages messages) {
        return messagesRepository.selectAll(messages);
    }

}
