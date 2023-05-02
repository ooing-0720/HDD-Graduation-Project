package com.HDD.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberFormDto {

    private String email;
    private String certification;
    private String sid;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotBlank(message = "비밀번호가 일치하지 않습니다.")
    private String passwordCheck;

    @NotEmpty
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 문자와 숫자로 이루어진 2~10자리여야 합니다.")
    private String nickname;

    @NotEmpty(message = "전공은 반드시 선택해야합니다.")
    private String major;
    private String doubleMajor = null;

    @Builder
    public MemberFormDto(String email, String certification, String sid, String password, String passwordCheck, String nickname, String major, String doubleMajor) {
        this.email = email;
        this.certification = certification;
        this.sid = sid;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.nickname = nickname;
        this.major = major;
        this.doubleMajor = doubleMajor;
    }
}
