package com.example.partymate.dto;

import com.example.partymate.dto.MemberPartyResponseDto;
import java.util.List;

/**
 * @author : Unagi_zoso
 * @date : 2023-11-12
 */
public class MemberPartyResponseDtoList {
    private final List<MemberPartyResponseDto> memberPartyResponseDtoList;

    public MemberPartyResponseDtoList(List<MemberPartyResponseDto> memberPartyResponseDtoList) {
        this.memberPartyResponseDtoList = memberPartyResponseDtoList;
    }

    public List<MemberPartyResponseDto> getMemberPartyResponseDtoList() {
        return memberPartyResponseDtoList;
    }
}
