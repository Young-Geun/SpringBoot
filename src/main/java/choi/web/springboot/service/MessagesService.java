package choi.web.springboot.service;

import choi.web.springboot.domain.Member;
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

    public Page<Messages> selectRecvMessagesList(int page, long memberId) {
        Member receiver = new Member();
        receiver.setMemberId(memberId);
        return messagesRepository.findByReceiver(receiver, PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "messagesId")));
    }

    public Page<Messages> selectSendMessagesList(int page, long memberId) {
        Member sender = new Member();
        sender.setMemberId(memberId);
        return messagesRepository.findBySender(sender, PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "messagesId")));
    }

}
