package com.example.partymate.domain.member;

import com.example.partymate.domain.util.Auditable;
import com.example.partymate.domain.util.EntityListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author : Unagi_zoso
 * @date: 2023-10-12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(value = EntityListener.class)
public class Member implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(length = 32, nullable = false)
    private String password;

    @Column(length = 64, nullable = false)
    private String email;

    @Column(length = 256, nullable = false)
    private String profileImageUrl;

    @Column(length = 32, nullable = false)
    private String nickname;

    @Column(length = 32, nullable = false)
    private String name;

    @Column(length = 32, nullable = false)
    private String phone;

    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private LocalDateTime birthDateAndTime;

    @Column(nullable = false)
    private boolean agreeServiceFlag;

    @Column(nullable = false)
    private boolean agreePrivacyFlag;

    @Column(nullable = false)
    private boolean agreeMarketingFlag;

    @Column(nullable = false)
    private Integer erasedFlag;

    @Column(nullable = false)
    LocalDateTime createdDateTime;

    @Column(nullable = false)
    LocalDateTime updatedDateTime;
}
