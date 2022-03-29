package choi.web.springboot.service;

import choi.web.springboot.domain.Member;
import choi.web.springboot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    @Value("${file.dir}")
    private String fileDir;

    private final MemberRepository memberRepository;

    public List<Member> selectList(String name) {
        return memberRepository.findByMemberNameLike("%" + name + "%");
    }

    public Member selectOne(String email, String password) {
        return memberRepository.findByMemberEmailAndMemberPassword(email, password);
    }

    public Member findByMemberId(long id) {
        return memberRepository.findByMemberId(id);
    }

    public int insert(Member member) {
        int result = 1;
        if (memberRepository.findByMemberEmail(member.getMemberEmail()) == null) {
            try {
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

    public int update(Member member, MultipartFile files) {
        int result = 1;
        try {
            // 프로필 저장
            if (!files.isEmpty()) {
                String profilePath = fileDir + "member_pic";
                if (new File(profilePath).mkdirs()) {
                    throw new Exception("사진 업로드 실패");
                }

                String ext = files.getOriginalFilename().substring(files.getOriginalFilename().lastIndexOf("."));
                String filePath = profilePath + File.separator + member.getMemberId() + ext;
                files.transferTo(new File(filePath));

                member.setMemberProfile(member.getMemberId() + ext);
            }

            memberRepository.save(member);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = 0;
        }

        return result;
    }

}
