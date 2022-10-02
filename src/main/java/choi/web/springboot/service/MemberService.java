package choi.web.springboot.service;

import choi.web.springboot.config.ConfigProp;
import choi.web.springboot.domain.Member;
import choi.web.springboot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final ConfigProp configProp;

    private final MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public List<Member> findByMemberNameContainingIgnoreCase(String name) {
        return memberRepository.findByMemberNameContainingIgnoreCase(name);
    }

    public List<Member> findByMemberEmailContainingIgnoreCase(String email) {
        return memberRepository.findByMemberEmailContainingIgnoreCase(email);
    }

    public List<Member> findByMemberStatus(String status) {
        return memberRepository.findByMemberStatus(status);
    }

    public Member findByMemberId(long id) {
        return memberRepository.findByMemberId(id);
    }

    public Member findForLogin(String email, String password) {
        return memberRepository.findByMemberEmailAndMemberPassword(email, password);
    }

    public int insert(Member member) {
        int result = 1;
        if (memberRepository.findByMemberEmail(member.getMemberEmail()) == null) {
            try {
                member.setMemberStatus("Y");
                member.setMemberBirth(member.getMemberBirthYyyy() + member.getMemberBirthMm() + member.getMemberBirthDd());
                memberRepository.save(member);
            } catch (Exception e) {
                log.error(e.getMessage());
                result = 0;
            }
        } else {
            result = -1;
        }

        return result;
    }

    public int update(Member member, Member loginMember, MultipartFile files) {
        int result = 1;
        try {
            // 프로필 저장
            if (files != null && !files.isEmpty()) {
                String profilePath = configProp.getFileDir() + "member_pic";
                if (new File(profilePath).mkdirs()) {
                    throw new Exception("사진 업로드 실패");
                }

                String ext = files.getOriginalFilename().substring(files.getOriginalFilename().lastIndexOf("."));
                String filePath = profilePath + File.separator + member.getMemberId() + ext;
                files.transferTo(new File(filePath));

                member.setMemberProfile(member.getMemberId() + ext);
            } else {
                member.setMemberProfile(loginMember.getMemberProfile());
            }
            member.setLastLoginDate(loginMember.getLastLoginDate());

            memberRepository.save(member);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = 0;
        }

        return result;
    }

    public void updateLastLoginDate(Member member) {
        member.setLastLoginDate(LocalDateTime.now());
        memberRepository.save(member);
    }

    public void updateStatus(Member member, String status) {
        member.setMemberStatus(status);
        memberRepository.save(member);
    }

}
