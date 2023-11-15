package com.example.partymate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Unagi_zoso
 * @since 2023-11-15
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberPartyRequestDto {
    private Long memberId;
    private Long partyId;
}
