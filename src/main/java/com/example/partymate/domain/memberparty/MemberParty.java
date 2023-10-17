package com.example.partymate.domain.memberparty;

import com.example.partymate.domain.member.Member;
import com.example.partymate.domain.party.Party;
import com.example.partymate.domain.util.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Unagi_zoso
 * @date : 2023-10-13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MemberParty extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberPartyId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "party_id")
    private Party party;

    @Column(nullable = false)
    private String partyMemberRole;
}
