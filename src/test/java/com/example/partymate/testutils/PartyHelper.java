package com.example.partymate.testutils;

import com.example.partymate.model.Party;

/**
 * @author : Unagi_zoso
 * @date : 2023-11-08
 */
public class PartyHelper {
    public static Party generateParty() {
        return Party.builder()
                .partyName("테스트 파티")
                .currentMemberCount(0)
                .maxPartyMemberCount(4)
                .build();
    }

    public static Party generateParty(String position) {
        return Party.builder()
                .partyName("테스트 파티")
                .currentMemberCount(0)
                .maxPartyMemberCount(4)
                .build();
    }
}
