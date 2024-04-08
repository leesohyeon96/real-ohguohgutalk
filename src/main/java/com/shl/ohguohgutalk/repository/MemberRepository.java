package com.shl.ohguohgutalk.repository;

import com.shl.ohguohgutalk.entity.Member;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    @NotNull // TODO: 왼쪽꺼 뭔지 알아보고, 왜 자꾸 노란줄 뜨는지 해결하기!
    Member save(@NotNull Member member);

    Member findByUserId(String userId);

    Member findMemberByUserIdAndPassword(String userId, String password);

    @Query("SELECT e.salt FROM Member e WHERE e.userId = :userId") // JPQL 사용
    String findSaltByUserId(String userId);
}
