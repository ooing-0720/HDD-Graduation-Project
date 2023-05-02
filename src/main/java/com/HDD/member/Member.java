package com.HDD.member;

import com.HDD.web.dto.MemberFormDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@Getter
public class Member {
    private String sid;
    private String email;
    private String password;
    private String nickname;
    private String major;
    private String doubleMajor;
    private MemberRole role;
    private int profile;

    @Builder
    public Member(String sid, String email, String password, String nickname, String major, String doubleMajor, MemberRole role) {
        this.sid = sid;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.major = major;
        this.doubleMajor = doubleMajor;
        this.role = role;
        this.profile = 0;   // default 이미지
    }

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .sid(memberFormDto.getSid())
                .email(memberFormDto.getEmail())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))  // pwd 암호화
                .nickname(memberFormDto.getNickname())
                .major(memberFormDto.getMajor())
                .doubleMajor(memberFormDto.getDoubleMajor())
                .role(MemberRole.MEMBER)    // 회원가입 시 학생 계정으로
                .build();
        return member;
    }
}
