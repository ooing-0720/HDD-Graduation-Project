package com.HDD.service;

import com.HDD.member.Member;
//import com.HDD.repository.MemberRepository;
import com.HDD.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
//@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemoryMemberRepository memberRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findBySid(member.getSid());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public void validateDuplicateNickname(String nickname) {
        Optional<Member> findMember = memberRepository.findByNickname(nickname);
        if (findMember != null) {
            throw new IllegalStateException("이미 사용중인 닉네임입니다.");
        }
    }
}