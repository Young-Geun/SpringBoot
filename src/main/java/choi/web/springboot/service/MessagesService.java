package choi.web.springboot.service;

import choi.web.springboot.domain.Member;
import choi.web.springboot.domain.Messages;
import choi.web.springboot.repository.MessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class MessagesService {

    private final MessagesRepository messagesRepository;

    public Page<Messages> findByReceiver(int page, long memberId) {
        Member receiver = new Member();
        receiver.setMemberId(memberId);
        return messagesRepository.findByReceiver(receiver, PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "messagesId")));
    }

    public Page<Messages> findBySender(int page, long memberId) {
        Member sender = new Member();
        sender.setMemberId(memberId);
        return messagesRepository.findBySender(sender, PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "messagesId")));
    }

    public int sendMessages(Member member, String content, HttpSession session) {
        Messages messages = new Messages();
        messages.setSender((Member) session.getAttribute("loginMember"));
        messages.setReceiver(member);
        messages.setMessages(content);
        messages.setSendDate(LocalDateTime.now());
        try {
            messagesRepository.save(messages);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

}
