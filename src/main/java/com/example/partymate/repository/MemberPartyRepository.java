package com.example.partymate.repository;

import com.example.partymate.model.MemberParty;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Unagi_zoso
 * @since 2023-10-13
 */
public interface MemberPartyRepository extends JpaRepository<MemberParty, Long>, MemberPartyCustomRepository {

    @Transactional
    @Modifying
    @Query(value = "UPDATE member_party SET member_party.erased_flag = 1 WHERE member_party.member_party_id = :memberPartyId", nativeQuery = true)
    void turnOnErasedFlagById(Long memberPartyId);
}
