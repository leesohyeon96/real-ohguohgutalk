package com.shl.ohguohgutalk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Value("${spring.data.redis.password}")
    private String password;


    /**
     * connectionFactory 정의
     * - application.properties 에서도 가능함
     * @return connectionFactory 인스턴스
     */
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        // 서버주소, 포트 custom config
        // [주의] jedisConnectionFactory 의 setHostName, setPort 는 Deprecated 예정이기 때문에 대안 RedisStandaloneConfiguration 구현 필요
        RedisStandaloneConfiguration jedisConnectionFactory = new RedisStandaloneConfiguration(host, port);
        jedisConnectionFactory.setPassword(RedisPassword.of(password)); // redis password
        return new JedisConnectionFactory(jedisConnectionFactory);
    }

    /**
     * JedisConnectionFactory 를 사용하는 RedisTemplate 정의
     * @return RedisTemplate 인스턴스
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());

        // key:value 의 경우 serializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.setEnableTransactionSupport(true);
        return template;
    }
}
