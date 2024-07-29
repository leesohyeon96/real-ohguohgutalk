package com.shl.ohguohgutalk.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisCacheUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisCacheUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void cacheData(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * key 값을 통해 CachedData get
     * @param key key 값
     * @return CachedData
     */
    public Object getCachedData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteCachedData(String key) {
        redisTemplate.delete(key);
    }
}
