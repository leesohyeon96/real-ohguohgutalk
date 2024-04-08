package com.shl.ohguohgutalk.service;

import com.shl.ohguohgutalk.domain.LoginMember;
import com.shl.ohguohgutalk.entity.Member;
import com.shl.ohguohgutalk.exception.CustomizedException;
import com.shl.ohguohgutalk.repository.MemberRepository;
import com.shl.ohguohgutalk.util.SHA256Util;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
//@Transactional(readOnly = true) // TODO: readOnly 왜 빨간불?
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
    @Transactional(rollbackOn = Exception.class)
    public void save(Member member) {
        log.info("member : {}", member);
        String password = member.getPassword();

        // TODO: functionalInterface 만들어서 sha-256 암호화 시켜서 저장하기
        // SHA256 암호화 : 단방향 해시 함수 사용
        Member processedMember = sha256Util.getSHA256HashedPwd(member);

        String hashedPwd = processedMember.getPassword();
        if (hashedPwd.equals(password)) {
            log.info("암호화 처리 안됨, hashedPwd = {}", hashedPwd);
            throw new CustomizedException("비밀번호 딴걸로 해주십셔", member);
        }
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

    /**
     * 로그인
     * @param member {@link LoginMember}
     */
    public boolean login(LoginMember member) {
        // 1. userId에 저장된 Salt 조회
        String savedSalt = memberRepository.findSaltByUserId(member.getUserId());
        if (savedSalt == null) return false;

        // 2. 저장된 Salt로 hashPwd 생성
        String hasedPwd = sha256Util.getSHA256HashedPwdBySalt(member.getPassword(), savedSalt);

        // 3. 아이디와 암호화된 비밀번호에 맞는 정보찾기
        Member memberInfo = memberRepository.findMemberByUserIdAndPassword(member.getUserId(), hasedPwd);
        if (memberInfo == null) member.setPassword(member.getPassword()); // 없으면 그냥 password 다시 담아서 보내줘야함

        // 있으면 true, 없으면 false 반환
        return memberInfo != null; // 존재하면 true 반환,
    }
}
