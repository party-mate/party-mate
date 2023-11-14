package com.example.partymate.service;

import static com.example.partymate.model.Member.toMember;

import com.example.partymate.dto.MemberSaveRequestDto;
import com.example.partymate.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author : Unagi_zoso
 * @since : 2023-11-12
 */
@RequiredArgsConstructor
@Service
public class MemberService {
    public final MemberRepository memberRepository;

    public void saveMember(MemberSaveRequestDto memberSaveRequestDto) {
        validateAgreement(memberSaveRequestDto);
        memberRepository.save(toMember(memberSaveRequestDto));
    }

    private void validateAgreement(MemberSaveRequestDto memberSaveRequestDto) {
        if (memberSaveRequestDto.getAgreement().getAgreePrivacyFlag() == 0 ||
                memberSaveRequestDto.getAgreement().getAgreeServiceFlag() == 0 ||
                memberSaveRequestDto.getAgreement().getAgreeMarketingFlag() == 0) {
            throw new IllegalArgumentException("약관에 동의해주세요.");
        }
    }

    public void updateMember(Long memberId, String name, String email, String password) {
        // memberRepository.save (memberId, name, email, password);
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
