package com.shl.ohguohgutalk.service;

import com.shl.ohguohgutalk.entity.Member;
import com.shl.ohguohgutalk.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;


    public void save(Member member) {
        loginRepository.save(member);
    }

}
