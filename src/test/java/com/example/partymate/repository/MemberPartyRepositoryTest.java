package com.example.partymate.repository;

import com.example.partymate.dto.MemberPartyResponseDtoList;
import com.example.partymate.model.Member;
import com.example.partymate.model.MemberParty;
import com.example.partymate.model.Party;
import com.example.partymate.testutils.config.TestConfig;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static com.example.partymate.testutils.MemberHelper.generateMember;
import static com.example.partymate.testutils.MemberPartyHelper.generateMemberParty;
import static com.example.partymate.testutils.PartyHelper.generateParty;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Unagi_zoso
 * @since 2023-10-13
 */
@ActiveProfiles("local")
@Import(TestConfig.class)
@DataJpaTest
class MemberPartyRepositoryTest {
    @Autowired
    MemberPartyRepository memberPartyRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PartyRepository partyRepository;

    @Autowired
    EntityManager entityManager;

    final static int NUM_OF_DATA = 3;

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < NUM_OF_DATA; i++) {
            makeGivenData();
        }
    }

    @AfterEach
    public void tearDown() {
        memberPartyRepository.deleteAll();
        memberRepository.deleteAll();
        partyRepository.deleteAll();
        entityManager .createNativeQuery("ALTER TABLE member_party ALTER COLUMN member_party_id RESTART WITH 1") .executeUpdate();
        entityManager .createNativeQuery("ALTER TABLE party ALTER COLUMN party_id RESTART WITH 1") .executeUpdate();
        entityManager .createNativeQuery("ALTER TABLE member ALTER COLUMN member_id RESTART WITH 1") .executeUpdate();
    }

    @DisplayName("partyId로 party에 속한 member들을 조회한다.")
    @Test
    void test_findMembersByPartyId() {
        // given

        // when
        MemberPartyResponseDtoList memberPartyResponseDtoList = memberPartyRepository.findPartyMembersByPartyId(1L);

        // then
        assertEquals(3, memberPartyResponseDtoList.getMemberPartyResponseDtoList().size());
    }

    private void makeGivenData() {
        Party givenParty = generateParty();
        List<Member> memberList = List.of(
                generateMember(),
                generateMember(),
                generateMember()
        );

        List<MemberParty> givenMemberPartyList =  List.of(
                generateMemberParty(memberList.get(0), givenParty),
                generateMemberParty(memberList.get(1), givenParty),
                generateMemberParty(memberList.get(2), givenParty)
        );

        partyRepository.save(givenParty);
        memberPartyRepository.saveAll(givenMemberPartyList);
    }
}