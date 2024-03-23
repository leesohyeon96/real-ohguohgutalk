package com.shl.ohguohgutalk.service;

import com.shl.ohguohgutalk.entity.Member;
import com.shl.ohguohgutalk.exception.CustomizedException;
import com.shl.ohguohgutalk.repository.MemberRepository;
import com.shl.ohguohgutalk.util.SHA256Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final SHA256Util sha256Util;

    @Autowired
    public MemberService(MemberRepository memberRepository, SHA256Util sha256Util) {
        this.memberRepository = memberRepository;
        this.sha256Util = sha256Util;
    }


    /**
     * method of saving member
     * @param member {@link Member}
     */
    public void save(Member member) {
        log.info("member : {}", member); // 애초에 여기서부터 문제가있네

        // TODO: functionalInterface 만들어서 sha-256 암호화 시켜서 저장하기
        // SHA256 암호화 : 단방향 해시 함수 사용
        String hashedPwd = sha256Util.getSHA256HashedPwd(member.getPassword());
        if (hashedPwd.equals(member.getPassword())) {
            log.info("암호화 처리 안됨, hashedPwd = {}", hashedPwd);
            throw new CustomizedException("비밀번호 딴걸로 해주십셔", member);
        }
        member.setPassword(hashedPwd);
        memberRepository.save(member);
    }

    /**
     * 아이디 중복체크 기능
     * @param userId 중복체크할 userId
     * @return 중복여부
     */
    public boolean checkUserId(String userId) {
        Member member = memberRepository.findByUserId(userId);
        return member != null; // null이 아니면 존재하는거기때문에 true 반환
    }
}
