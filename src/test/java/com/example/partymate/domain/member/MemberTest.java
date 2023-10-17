package com.example.partymate.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Unagi_zoso
 * @date : 2023-10-14
 */

@ActiveProfiles("test")
@DataJpaTest
class MemberTest {

    @Autowired
    private TestEntityManager entityManager;

    @DisplayName("Member 인스턴스를 DB에 저장하기")
    @Test
    void Save_Persist_Member() {
        Member member = entityManager.persistAndFlush(Member.builder()
                .memberId(1)
                .memberPartyList(null)
                .agreeMarketingFlag(1)
                .agreePrivacyFlag(1)
                .agreeServiceFlag(1)
                .birthYearDate(null)
                .emailAddress("test@nyank.com")
                .erasedFlag(0)
                .gender(Gender.MALE)
                .name("냑냑")
                .nickname("냑지")
                .password("1234")
                .phoneNumber("010-1234-3456")
                .profileImageUrl(null).build());

        assertEquals(1, member.getMemberId());
        assertEquals("냑냑", member.getName());
        assertEquals("test@nyank.com", member.getEmailAddress());
        assertEquals("냑지", member.getNickname());
        assertEquals("010-1234-3456", member.getPhoneNumber());
    }
}