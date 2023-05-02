package com.HDD.repository;

import com.HDD.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findBySid(String sid);
    Member findByEmail(String email);
    Member findByNickname(String nickname);
}