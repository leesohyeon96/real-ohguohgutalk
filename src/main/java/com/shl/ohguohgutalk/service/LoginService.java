package com.shl.ohguohgutalk.service;

import com.shl.ohguohgutalk.entity.Member;
import com.shl.ohguohgutalk.repository.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }


    /**
     * method of saving member
     * @param member {@link Member}
     */
    public void save(Member member) {
        log.info("member : {}", member);
        loginRepository.save(member);
    }

}
