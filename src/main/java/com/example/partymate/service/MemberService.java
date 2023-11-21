package com.example.partymate.service;

import com.example.partymate.dto.MemberUpdateRequestDto;
import com.example.partymate.model.Member;
import static com.example.partymate.model.Member.toMember;

import com.example.partymate.dto.MemberSaveRequestDto;
import com.example.partymate.repository.MemberRepository;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author : Unagi_zoso
 * @since : 2023-11-12
 */
@RequiredArgsConstructor
@Service
public class MemberService {
    public static Map<String, String> codeMap = new HashMap<String, String>();
    public static final String AUTH_CODE_PREFIX = "AuthCode ";

    private final MemberRepository memberRepository;

    private final MailService mailService;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;

    public void saveMember(MemberSaveRequestDto memberSaveRequestDto) {
        validateAgreement(memberSaveRequestDto);
        memberRepository.save(toMember(memberSaveRequestDto));
    }

    private void validateAgreement(MemberSaveRequestDto memberSaveRequestDto) {
        if (memberSaveRequestDto.getAgreement().getAgreePrivacyFlag() == 0 ||
                memberSaveRequestDto.getAgreement().getAgreeServiceFlag() == 0) {
            throw new IllegalArgumentException("약관에 동의해주세요.");
        }
    }

    public void selectOne(Long memberId){
        memberRepository.findByMemberId(memberId);
    }

    public Member findMemberByEmailAddress(String emailAddress){
        return memberRepository.findByEmailAddress(emailAddress);
    }

    public void updateMember(MemberUpdateRequestDto memberSaveRequestDto, UserDetails userDetails) {
        /*Member member = memberRepository.findMemberByNickname(userDetails.getUsername()).toEntity();
        if (memberSaveRequestDto.getNickname() != null) {
            member.updateNickname(memberSaveRequestDto.getNickname());
        }
        if (memberSaveRequestDto.getPassword() != null) {
            member.updatePassword(memberSaveRequestDto.getPassword());
        }
        if (memberSaveRequestDto)
        memberRepository.save(member);*/
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }


    public Member findMemberByUsername(String username) {
        Member m = memberRepository.findMemberByEmail(username).toEntity();
        System.out.println(m.getMemberId() + " " + m.getEmailAddress() + " " + m.getPassword() + " " + m.getRole());
        return memberRepository.findById(m.getMemberId()).orElseThrow(IllegalArgumentException::new);
    }

    public void sendCodeToEmail(String toEmail) {
        this.checkDuplicatedEmail(toEmail);
        String title = "Travel with me 이메일 인증 번호";
        String authCode = this.createCode();
        mailService.sendEmail(toEmail, title, authCode);
        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )
        codeMap.put(AUTH_CODE_PREFIX + toEmail, authCode);
    }

    private void checkDuplicatedEmail(String email) {
        Optional<Member> member = Optional.ofNullable(memberRepository.findByEmailAddress(email));
        if (member.isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }
    }

    private String createCode() {
        int lenth = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lenth; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifiedCode(String email, String authCode) {
        this.checkDuplicatedEmail(email);
        String redisAuthCode = codeMap.get(AUTH_CODE_PREFIX + email);
        boolean authResult = codeMap.containsKey(redisAuthCode) && Objects.equals(codeMap.get(AUTH_CODE_PREFIX), redisAuthCode);

        return authResult;
    }

    public boolean checkNickname(String nickname) {
        boolean flag = memberRepository.findMemberByNicknameForCheck(nickname);
        if (flag) {
            return false;
        }
        return true;
    }
}
