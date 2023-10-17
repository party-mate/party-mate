package com.example.partymate.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Unagi_zoso
 * @date : 2023-10-13
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
}
