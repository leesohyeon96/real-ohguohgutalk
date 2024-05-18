package com.shl.ohguohgutalk.service;

import com.shl.ohguohgutalk.entity.Chat;
import com.shl.ohguohgutalk.repository.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalkService {

    private final TalkRepository talkRepository;

    @Autowired
    public TalkService(TalkRepository repository) {
        this.talkRepository = repository;
    }

    public void saveChats(Chat chat) {
        // mongoDB 사용해서 저장!
        talkRepository.save(chat);
    }

    public List<Chat> getTalkList() {
        // mongoDB 에서 이전 채팅 내용 조회
        return talkRepository.findAll();
    }
}
