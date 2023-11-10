package com.example.partymate.domain.member;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.example.partymate.domain.member.MemberHelper.*;
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
        memberRepository.save(generateMember());
        memberRepository.save(generateMember());

        int numOfAllMembers = memberRepository.findAll().size();

        assertEquals(2, numOfAllMembers);
    }
}