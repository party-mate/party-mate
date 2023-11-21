package com.example.partymate.controller;

import com.example.partymate.dto.MemberPartyRequestDto;
import com.example.partymate.dto.PartyJoinRequestDto;
import com.example.partymate.repository.MemberRepository;
import com.example.partymate.service.MemberPartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Unagi_zoso
 * @since 2023-11-15
 */
@RequiredArgsConstructor
@RequestMapping("/api/member/party")
@RestController
public class MemberPartyController {
    private final MemberRepository memberRepository;
    private final MemberPartyService memberPartyService;

    @PostMapping
    public void partyAddMember(@RequestBody PartyJoinRequestDto partyJoinRequestDto, @AuthenticationPrincipal String email) {
        Long memberId = memberRepository.findMemberByEmail(email).toEntity().getMemberId();
        memberPartyService.partyAddMember(new MemberPartyRequestDto(memberId, partyJoinRequestDto.getPostId()));
    }
}
