package com.example.partymate.service;

import static com.example.partymate.model.PartyMemberRoleConstants.MEMBER;

import com.example.partymate.dto.MemberPartyRequestDto;
import com.example.partymate.model.Member;
import com.example.partymate.model.MemberParty;
import com.example.partymate.model.Party;
import com.example.partymate.repository.MemberPartyRepository;
import com.example.partymate.repository.MemberRepository;
import com.example.partymate.repository.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Unagi_zoso
 * @since 2023-11-13
 */
@Service
@RequiredArgsConstructor
public class MemberPartyService {
    private final MemberRepository  memberRepository;
    private final PartyRepository partyRepository;
    private final MemberPartyRepository memberPartyRepository;

    public void partyAddMember(MemberPartyRequestDto memberPartyRequestDto) {
        Member member = memberRepository.findById(memberPartyRequestDto.getMemberId()).orElseThrow(RuntimeException::new);
        Party party = partyRepository.findById(memberPartyRequestDto.getPartyId()).orElseThrow(RuntimeException::new);
        if (party.getCurrentMemberCount() >= party.getMaxPartyMemberCount()) {
            throw new RuntimeException("파티 인원이 가득 찼습니다.");
        }
        if (existsByMemberAndParty(member, party)) {
            throw new RuntimeException("이미 가입한 파티입니다.");
        }
        memberPartyRepository.save(new MemberParty(member, party, MEMBER));
    }

    private boolean existsByMemberAndParty(Member member, Party party) {
        return memberPartyRepository.existMemberInParty(member.getMemberId(), party.getPartyId());
    }
}
