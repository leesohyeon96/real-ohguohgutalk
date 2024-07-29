package com.shl.ohguohgutalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories // Spring Data mongoDB 사용하기 위함
@EnableCaching // 캐싱기능 사용하기 위함
public class OhguohgutalkApplication {

	// TODO 1) docker compose 만들어서 docker container 관리해보기

	// TODO 2) 내가 한건 spring-data-redis 임(그 spring-data-jpa처럼;) > 캐시 manage 처럼 사용하기 위한 설정을 한게 아닌듯??
	// https://developer-nyong.tistory.com/21#article-5--@enablecaching
	// 요걸로 시도해보기

	public static void main(String[] args) {
		SpringApplication.run(OhguohgutalkApplication.class, args);
	}

}
