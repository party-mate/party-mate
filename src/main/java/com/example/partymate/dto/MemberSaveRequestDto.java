package com.example.partymate.dto;

import com.example.partymate.model.Agreement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Unagi_zoso
 * @since : 2023-11-12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSaveRequestDto {
    private String password;
    private String emailAddress;
    private String phoneNumber;
    private String nickname;
    private String gender;
    private Agreement agreement;
    private String authCode;
}