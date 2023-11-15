package com.example.partymate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberLoginRequestDto {
    private String emailAddress;
    private String password;
}
