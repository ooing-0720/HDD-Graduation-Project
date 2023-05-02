package com.HDD.controller;

import com.HDD.member.Member;
import com.HDD.service.EmailService;
import com.HDD.service.MemberService;
import com.HDD.web.dto.MemberFormDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/agree")
    public String agreeInfo(Model model) {
        boolean checked = false;
        model.addAttribute("checked", checked);
        return "/agree";
    }

    @PostMapping("/agree")
    public String agreeInfo(@ModelAttribute boolean checked) {
        if (checked) {
            return "/signup";
        } else {
            // alert 처리
            return "/agree";
        }
    }

    @GetMapping("/signup")
    public String signUpForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "/signup";
    }

    @PostMapping("/signup")
    public String singUpForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, @RequestParam String action, Model model) throws Exception {
        String certification = "";

        if (action.equals("mailButton")) {
            certification = emailService.sendSimpleMessage(memberFormDto.getEmail());
        } else if (action.equals("certificationButton")) {
            if (certification.isEmpty()) {
                // 인증번호 발송 버튼을 누르지 않고 인증번호 확인 버튼을 누른 경우
                // 필요없음
                model.addAttribute("errorMessage", "인증번호 발송 버튼을 눌러주세요.");
                return "/signup";
            } else {
                if (certification.equals(memberFormDto.getCertification())) {
                    // 생성된 인증번호와 입력받은 인증번호가 일치하는 경우
                    // ?
                } else {
                    model.addAttribute("errorMessage", "인증번호가 일치하지 않습니다.");
                    return "/signup";
                }
            }
        } else if (action.equals("nicknameButton")) {
            try {
                memberService.validateDuplicateNickname(memberFormDto.getNickname());

            } catch (IllegalStateException e) {
                model.addAttribute("errorMessage", e.getMessage());
                return "/signup";
            }
        }

        if (bindingResult.hasErrors()) {
            return "/signup";
        }

        // 비밀번호 확인 일치 X
        if (!memberFormDto.getPassword().equals(memberFormDto.getPasswordCheck())) {
            bindingResult.rejectValue("passwordCheck", "PasswordIncorrect", "패스워드가 일치하지 않습니다.");
            return "/signup";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/signup";
        }

        return "redirect:/";
    }
}
