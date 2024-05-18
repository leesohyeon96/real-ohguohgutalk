package com.shl.ohguohgutalk.repository;

import com.shl.ohguohgutalk.entity.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository // @NoRepositoryBean : runtime 시에 Repository 인터페이스의 인스턴스를 생성하는 것을 방지하는데 사용됨
public interface TalkRepository extends MongoRepository<Chat, String> {
}
