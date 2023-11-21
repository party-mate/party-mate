package com.example.partymate.repository;

import com.example.partymate.model.Party;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Unagi_zoso
 * @since 2023-10-13
 */
public interface PartyRepository extends JpaRepository<Party, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE party SET party.erased_flag = 1 WHERE party.party_id = :partyId", nativeQuery = true)
    void turnOnErasedFlagById(Long partyId);
}
