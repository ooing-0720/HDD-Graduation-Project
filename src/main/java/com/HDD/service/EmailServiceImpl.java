package com.HDD.service;

import com.HDD.member.Member;
import com.HDD.repository.MemberRepository;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender mailSender;
    private final MemberRepository memberRepository;

    public static final String ePw = createKey();

    private MimeMessage createMessage(String to) throws Exception {
        System.out.println("보내는 대상 : " + to);
        System.out.println("인증 번호 : " + ePw);
        MimeMessage message = mailSender.createMimeMessage();
        mailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject("이메일 인증 테스트");

        String msg = "";
        msg += "<div style='margin:20px;'>";
        msg += "<h1> ㅎㄷㄷ 인증번호 발송 </h1>";
        msg += "<br>";
        msg += "<p>아래 코드를 복사해 입력해주세요<p>";
        msg += "<br>";
        msg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msg += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msg += "<div style='font-size:130%'>";
        msg += "CODE : <strong>";
        msg += ePw + "</strong><div><br/> ";
        msg += "</div>";

        message.setText(msg, "utf-8", "html");
        message.setFrom(new InternetAddress("ohih897@gmail.com", "ㅎㄷㄷ"));

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {   // 인증 코드 생성
            int index = random.nextInt(3);

            switch (index) {
                case 0:
                    key.append((char) ((int) (random.nextInt(26)) + 97));
                    break;
                case 1:
                    key.append((char) ((int) (random.nextInt(26)) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(10));
                    break;
            }
        }

        System.out.println("key = " + key);
        return key.toString();
    }

    @Override
    public String sendSimpleMessage(String to) throws Exception {
        validateDuplicateEmail(to);
        MimeMessage message = createMessage(to);
        try {
            mailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }

    private void validateDuplicateEmail(String email) {
        Member findMember = memberRepository.findByEmail(email);
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
    }
}