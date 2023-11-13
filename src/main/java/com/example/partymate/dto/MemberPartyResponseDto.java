package com.example.partymate.dto;

import com.example.partymate.model.MemberParty;
import com.example.partymate.model.PartyMemberRoleConstants;
import com.querydsl.core.annotations.QueryProjection;
import static lombok.AccessLevel.PROTECTED;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Unagi_zoso
 * @date : 2023-11-12
 */
@Getter
@NoArgsConstructor(access = PROTECTED)
public class MemberPartyResponseDto {
    private String partyName;
    private String memberName;
    private PartyMemberRoleConstants  memberRole;

    public MemberPartyResponseDto(MemberParty memberParty) {
        this.partyName = memberParty.getParty().getPartyName();
        this.memberName = memberParty.getMember().getNickname();
        this.memberRole = memberParty.getPartyMemberRole();
    }

    @QueryProjection
    public MemberPartyResponseDto(String partyName, String memberName, PartyMemberRoleConstants memberRole) {
        this.partyName = partyName;
        this.memberName = memberName;
        this.memberRole = memberRole;
    }
}
