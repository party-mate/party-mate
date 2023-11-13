package com.example.partymate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long partyId;

    @Column(nullable = false)
    private String partyName;

    @Column(nullable = false)
    private Integer currentMemberCount;

    @Column(nullable = false)
    private Integer maxPartyMemberCount;
}
