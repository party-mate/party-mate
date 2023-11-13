package com.example.partymate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Unagi_zoso
 * @date : 2023-11-10
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Agreement {

    @Column(nullable = false)
    private Integer agreeServiceFlag;

    @Column(nullable = false)
    private Integer agreePrivacyFlag;

    @Column(nullable = false)
    private Integer agreeMarketingFlag;

    public Agreement copy(){
        return Agreement.builder()
                .agreeServiceFlag(this.getAgreeServiceFlag())
                .agreePrivacyFlag(this.getAgreePrivacyFlag())
                .agreeMarketingFlag(this.getAgreeMarketingFlag())
                .build();
    }
}