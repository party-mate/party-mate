package com.example.partymate.domain.member;

import java.time.LocalDateTime;

/**
 * @author : Unagi_zoso
 * @date : 2023-11-02
 */
public class MemberHelper {

    public static Member generateMember() {

        return Member.builder()
                .memberId(null)
                .memberParties(null)
                .agreeMarketingFlag(1)
                .agreePrivacyFlag(1)
                .agreeServiceFlag(1)
                .birthYearDate(LocalDateTime.now())
                .emailAddress("test@nyank.com")
                .erasedFlag(0)
                .gender(Gender.MALE)
                .name("냑냑")
                .nickname("냑지")
                .password("1234")
                .phoneNumber("010-1234-3456")
                .profileImageUrl(null)
                .build();
    }
}
