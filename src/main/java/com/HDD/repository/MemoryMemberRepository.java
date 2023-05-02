package com.HDD.repository;

import com.HDD.member.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemoryMemberRepository {

    private List<Member> members;

    public Member save(Member member){
        members.add(member);
        return member;
    }

    public Optional<Member> findBySid(String sid){
        return members.stream().filter(
                member -> member.getSid().equals(sid)).findAny();
    }

    public Optional<Member> findByEmail(String email) {
        return members.stream().filter(
                member -> member.getEmail().equals(email)).findAny();
    }

    public Optional<Member> findByNickname(String nickname) {
        return members.stream().filter(
                member -> member.getNickname().equals(nickname)).findAny();
    }
}
