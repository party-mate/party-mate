package com.example.partymate.model;

import com.example.partymate.dto.PartySaveRequestDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author DABIN
 * @since 2023-10-12
 */
@EqualsAndHashCode(callSuper = true)
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

    public static Party toParty(PartySaveRequestDto partySaveRequestDto) {
        return Party.builder()
            .partyName(partySaveRequestDto.getPartyName())
            .currentMemberCount(0)
            .maxPartyMemberCount(partySaveRequestDto.getMaxPartyMemberCount())
            .build();
    }

    public void addMemberCount() {
        this.currentMemberCount++;
    }
}
