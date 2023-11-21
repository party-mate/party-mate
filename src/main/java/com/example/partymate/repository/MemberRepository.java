package com.example.partymate.repository;

import com.example.partymate.model.Member;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Unagi_zoso
 * @since 2023-10-13
 * update : 2023-11-09 / JJDabean
 */
public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {
    // findBy규칙 > Username 문법
    Member findByEmailAddress(String emailAddress); // jpa Query methods

    Member findByMemberId(Long memberId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE member SET member.erased_flag = 1 WHERE member.member_id = :memberId", nativeQuery = true)
    void turnOnErasedFlagById(Long memberId);
}
