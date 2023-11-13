package com.example.partymate.testutils;

import com.example.partymate.model.Agreement;
import com.example.partymate.model.GenderConstants;
import com.example.partymate.model.Member;
import static com.example.partymate.testutils.AgreementHelper.generateAgreement;
import java.time.LocalDate;

/**
 * @author Unagi_zoso
 * @since 2023-11-02
 */
public class MemberHelper {
    public static Member generateMember() {

        return Member.builder()
                .birthYearDate(LocalDate.now())
                .emailAddress("test@nyank.com")
                .gender(GenderConstants.MALE)
                .nickname("test")
                .password("1234")
                .phoneNumber("010-1234-3456")
                .profileImageUrl(null)
                .agreement(generateAgreement())
                .build();
    }
}

class AgreementHelper {
    public static Agreement generateAgreement() {
        return Agreement.builder()
                .agreeMarketingFlag(1)
                .agreePrivacyFlag(1)
                .agreeServiceFlag(1)
                .build();
    }
}
