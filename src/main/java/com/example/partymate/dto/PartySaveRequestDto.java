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
public class PartySaveRequestDto {
    private String partyName;
    private Integer maxPartyMemberCount;
}
