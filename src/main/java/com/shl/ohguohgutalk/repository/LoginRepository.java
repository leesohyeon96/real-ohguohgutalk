package com.shl.ohguohgutalk.repository;

import com.shl.ohguohgutalk.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class LoginRepository {

    public void save(Member member) {
        log.info("save Member : {}", member);
    }
}
