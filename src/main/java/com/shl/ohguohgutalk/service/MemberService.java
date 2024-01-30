package com.shl.ohguohgutalk.service;

import com.shl.ohguohgutalk.entity.Member;
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
        log.info("member : {}", member);

        // TODO: functionalInterface 만들어서 sha-256 암호화 시켜서 저장하기
        // SHA256 암호화 : 단방향 해시 함수 사용
        String hashedPwd = sha256Util.getSHA256HashedPwd(member.getPassword());
        if (hashedPwd.equals(member.getPassword())) {
            log.info("암호화 처리 안됨, hashedPwd = {}", hashedPwd);
            // exceptionHandler 처리 해볼까? > exception 발생시키면 thymeleaf 에서 어떻게 처리되는지도 하기!
        }



        memberRepository.save(member);
    }

}
