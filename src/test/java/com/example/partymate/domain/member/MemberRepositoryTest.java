package com.example.partymate.domain.member;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Unagi_zoso
 * @date : 2023-10-13
 */

@ActiveProfiles("test")
@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void test_saveMember() {
        memberRepository.save(Member.builder()
                .memberId(1)
                .memberPartyList(null)
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
                .profileImageUrl(null).build());

        memberRepository.save(Member.builder()
                .memberId(2)
                .memberPartyList(null)
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
                .profileImageUrl(null).build());

        int numOfAllMembers = memberRepository.findAll().size();

        assertEquals(2, numOfAllMembers);
    }
}