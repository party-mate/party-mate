package com.example.partymate.repository;

import com.example.partymate.model.Agreement;
import com.example.partymate.model.Member;
import com.example.partymate.model.Member.MemberResponse;
import com.example.partymate.testutils.config.TestConfig;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static com.example.partymate.testutils.MemberHelper.generateMember;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * @author Unagi_zoso
 * @since  2023-10-13
 */

@ActiveProfiles("local")
@Import(TestConfig.class)
@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager entityManager;

    @AfterEach
    public void tearDown() {
        memberRepository.deleteAll();
        entityManager
                .createNativeQuery("ALTER TABLE member ALTER COLUMN member_id RESTART WITH 1").executeUpdate();
    }

    @DisplayName("member 저장 테스트")
    @Test
    void test_saveMember() {
        memberRepository.save(generateMember());
        memberRepository.save(generateMember());

        int numOfMembers = memberRepository.findAll().size();

        assertEquals(2, numOfMembers);
    }

    @DisplayName("member 조회 테스트")
    @Test
    void test_findMember() {
        //given
        Member givenMember = generateMember();
        Agreement givenAgreement = givenMember.getAgreement();

        //when
        memberRepository.save(givenMember);
        Member returnedMember = memberRepository.findById(1L).orElseThrow();
        Agreement returnedAgreement = returnedMember.getAgreement();

        //then
        assertAll(
                () -> assertEquals(1, returnedMember.getMemberId()),
                () -> assertEquals(givenMember.getNickname(), returnedMember.getNickname()),
                () -> assertEquals(givenMember.getPassword(), returnedMember.getPassword()),
                () -> assertEquals(givenMember.getEmailAddress(), returnedMember.getEmailAddress()),
                () -> assertEquals(givenMember.getErasedFlag(), returnedMember.getErasedFlag()),
                () -> assertEquals(givenMember.getBirthYearDate(), returnedMember.getBirthYearDate()),
                () -> assertEquals(givenMember.getGender(), returnedMember.getGender()),
                () -> assertEquals(givenMember.getPhoneNumber(), returnedMember.getPhoneNumber()),
                () -> assertEquals(givenMember.getProfileImageUrl(), returnedMember.getProfileImageUrl()),
                () -> assertEquals(givenAgreement.getAgreeMarketingFlag(), returnedAgreement.getAgreeMarketingFlag()),
                () -> assertEquals(givenAgreement.getAgreePrivacyFlag(), returnedAgreement.getAgreePrivacyFlag()),
                () -> assertEquals(givenAgreement.getAgreeServiceFlag(), returnedAgreement.getAgreeServiceFlag()),
                () -> assertNotNull(returnedMember.getCreatedDateTime()),
                () -> assertNotNull(returnedMember.getUpdatedDateTime())
        );
    }

    @DisplayName("member 삭제 테스트")
    @Test
    void test_deleteMember() {
        //given
        Member givenMember = generateMember();
        memberRepository.save(givenMember);

        //when
        memberRepository.deleteById(1L);

        //then
        assertEquals(0, memberRepository.findAll().size());
    }

    @DisplayName("member 수정 테스트")
    @Test
    void test_updateMember() {
        //given
        Member givenMember = generateMember();
        memberRepository.save(givenMember);

        //when
        Member returnedMember = memberRepository.findById(1L).orElseThrow();
        returnedMember.setNickname("test2");
        returnedMember.setPassword("12345");
        returnedMember.setEmailAddress("new@test.com");

        //then
        Member updatedMember = memberRepository.findById(1L).orElseThrow();
        assertAll(
                () -> assertEquals("test2", updatedMember.getNickname()),
                () -> assertEquals("12345", updatedMember.getPassword()),
                () -> assertEquals("new@test.com", updatedMember.getEmailAddress())
        );
    }

    @DisplayName("member email로 조회 테스트")
    @Test
    void test_findMemberByEmail() {
        //given
        Member givenMember = generateMember();
        memberRepository.save(givenMember);

        //when
        MemberResponse returnedMember = memberRepository.findMemberByEmail(givenMember.getEmailAddress());

        //then
        assertAll(
                () -> assertEquals(givenMember.getNickname(), returnedMember.getNickname()),
                () -> assertEquals(givenMember.getPassword(), returnedMember.getPassword()),
                () -> assertEquals(givenMember.getEmailAddress(), returnedMember.getEmailAddress()),
                () -> assertEquals(givenMember.getErasedFlag(), returnedMember.getErasedFlag()),
                () -> assertEquals(givenMember.getBirthYearDate(), returnedMember.getBirthYearDate()),
                () -> assertEquals(givenMember.getGender(), returnedMember.getGender()),
                () -> assertEquals(givenMember.getPhoneNumber(), returnedMember.getPhoneNumber()),
                () -> assertEquals(givenMember.getProfileImageUrl(), returnedMember.getProfileImageUrl())
        );
    }

    @DisplayName("전화번호로 member 조회 테스트")
    @Test
    void test_findMemberByPhoneNumber() {
        //given
        Member givenMember = generateMember();
        memberRepository.save(givenMember);

        //when
        MemberResponse returnedMember = memberRepository.findMemberByPhoneNumber(givenMember.getPhoneNumber());

        //then
        assertAll(
                () -> assertEquals(givenMember.getNickname(), returnedMember.getNickname()),
                () -> assertEquals(givenMember.getPassword(), returnedMember.getPassword()),
                () -> assertEquals(givenMember.getEmailAddress(), returnedMember.getEmailAddress()),
                () -> assertEquals(givenMember.getErasedFlag(), returnedMember.getErasedFlag()),
                () -> assertEquals(givenMember.getBirthYearDate(), returnedMember.getBirthYearDate()),
                () -> assertEquals(givenMember.getGender(), returnedMember.getGender()),
                () -> assertEquals(givenMember.getPhoneNumber(), returnedMember.getPhoneNumber()),
                () -> assertEquals(givenMember.getProfileImageUrl(), returnedMember.getProfileImageUrl())
        );
    }
}