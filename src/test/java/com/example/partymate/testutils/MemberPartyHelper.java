package com.example.partymate.testutils;

import com.example.partymate.model.Member;
import com.example.partymate.model.MemberParty;
import com.example.partymate.model.Party;
import com.example.partymate.model.PartyMemberRoleConstants;
import static com.example.partymate.model.PartyMemberRoleConstants.MEMBER;
import static com.example.partymate.testutils.MemberHelper.generateMember;
import static com.example.partymate.testutils.PartyHelper.generateParty;

/**
 * @author Unagi_zoso
 * @since 2023-11-11
 */
public class MemberPartyHelper {
    public static MemberParty generateMemberParty(){
        return MemberParty.builder()
                .member(generateMember())
                .party(generateParty())
                .partyMemberRole(MEMBER)
                .build();
    }

    public static MemberParty generateMemberParty(Member member){
        return MemberParty.builder()
                .member(member)
                .party(generateParty())
                .partyMemberRole(MEMBER)
                .build();
    }

    public static MemberParty generateMemberParty(PartyMemberRoleConstants partyMemberRole){
        return MemberParty.builder()
                .member(generateMember())
                .party(generateParty())
                .partyMemberRole(partyMemberRole)
                .build();
    }

    public static MemberParty generateMemberParty(Member member, Party party){
        return MemberParty.builder()
                .member(member)
                .party(party)
                .partyMemberRole(MEMBER)
                .build();
    }
}
