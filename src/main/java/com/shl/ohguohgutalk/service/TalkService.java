package com.shl.ohguohgutalk.service;

import com.shl.ohguohgutalk.entity.Chat;
import com.shl.ohguohgutalk.repository.TalkRepository;
import com.shl.ohguohgutalk.util.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TalkService {

    private final TalkRepository talkRepository;
    private final RedisCacheUtil redisCacheUtil;

    @Autowired
    public TalkService(TalkRepository repository, RedisCacheUtil redisCacheUtil) {
        this.talkRepository = repository;
        this.redisCacheUtil = redisCacheUtil;
    }

    public void saveChats(Chat chat) {
        // mongoDB 사용해서 저장!
        talkRepository.save(chat);
        redisCacheUtil.deleteCachedData("chat");
    }

    public List<Chat> getTalkList() {
        List<Chat> cachedMessages = (List<Chat>) redisCacheUtil.getCachedData("chat");
        if (cachedMessages != null) {
            return cachedMessages;
        }

        List<Chat> messages = talkRepository.findAll();
        if (!messages.isEmpty()) {
            redisCacheUtil.cacheData("chat", messages, 1, TimeUnit.HOURS);
        }
        return messages;
    }
}
