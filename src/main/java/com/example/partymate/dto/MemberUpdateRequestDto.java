package com.example.partymate.dto;

import com.example.partymate.model.Agreement;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Unagi_zoso
 * @since 2023-11-16
 */

@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {
    private String password;
    private String emailAddress;
    private String phoneNumber;
    private String nickname;
}
