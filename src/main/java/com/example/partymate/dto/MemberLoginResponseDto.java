package com.example.partymate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Unagi_zoso
 * @since 2023-11-19
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberLoginResponseDto {
    String nickname;
    String profileImage;
}
