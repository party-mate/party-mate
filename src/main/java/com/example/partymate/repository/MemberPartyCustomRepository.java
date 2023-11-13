package com.example.partymate.repository;

import com.example.partymate.dto.MemberPartyResponseDtoList;

/**
 * @author : Unagi_zoso
 * @date : 2023-11-12
 */
public interface MemberPartyCustomRepository {
     MemberPartyResponseDtoList findPartyMembersByPartyId(Long partyId);
}
