package com.example.partymate.domain.party;

import com.example.partymate.domain.member.Member;
import com.example.partymate.domain.memberparty.MemberParty;
import com.example.partymate.domain.util.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : DABIN
 * @date: 2023-10-12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Party extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long partyId;

    @Column(length = 32, nullable = false)
    private String position;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "party")
    @Column(nullable = false)
    private List<MemberParty> memberParties;

    @Column(nullable = false)
    private Integer erasedFlag;
}
