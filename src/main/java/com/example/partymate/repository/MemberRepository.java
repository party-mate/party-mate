package com.example.partymate.repository;

import com.example.partymate.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Unagi_zoso
 * @date : 2023-10-13
 * update : 2023-11-09 / JJDabean
 */
public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {
    // findBy규칙 > Username 문법
    Member findByEmailAddress(String email); // jpa Query methods
}
