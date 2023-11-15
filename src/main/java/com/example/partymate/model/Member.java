package com.example.partymate.model;

import static com.example.partymate.model.GenderConstants.convertTo;

import com.example.partymate.dto.MemberSaveRequestDto;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Unagi_zoso
 * @since 2023-10-12
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 32, nullable = false)
    private String password;

    @Column(length = 64, nullable = false)
    private String emailAddress;

    @Column(length = 32, nullable = false)
    private String phoneNumber;

    @Column(length = 32, nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderConstants gender;

    @Embedded
    private Agreement agreement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleConstants role;

    public static Member toMember(MemberSaveRequestDto memberSaveRequestDto) {
        return  Member.builder()
                .gender(convertTo(memberSaveRequestDto.getGender()))
                .emailAddress(memberSaveRequestDto.getEmailAddress())
                .nickname(memberSaveRequestDto.getNickname())
                .password(memberSaveRequestDto.getPassword())
                .phoneNumber(memberSaveRequestDto.getPhoneNumber())
                .agreement(Agreement.builder()
                        .agreePrivacyFlag(memberSaveRequestDto.getAgreement().getAgreePrivacyFlag())
                        .agreeServiceFlag(memberSaveRequestDto.getAgreement().getAgreeServiceFlag())
                        .agreeMarketingFlag(memberSaveRequestDto.getAgreement().getAgreeMarketingFlag())
                        .build())
                .role(RoleConstants.USER)
                .build();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberResponse {

        private Long memberId;
        private String emailAddress;
        private String phoneNumber;
        private String nickname;
        private String password;
        private GenderConstants gender;
        private Agreement agreement;
        private RoleConstants role;
        private Integer erasedFlag;
        private LocalDateTime createdDateTime;
        private LocalDateTime updatedDateTime;

        public MemberResponse(Member member) {
            this.memberId = member.getMemberId();
            this.emailAddress = member.getEmailAddress();
            this.phoneNumber = member.getPhoneNumber();
            this.nickname = member.getNickname();
            this.password = member.getPassword();
            this.gender = member.getGender();
            this.agreement = member.getAgreement();
            this.role = member.getRole();
            this.erasedFlag = member.getErasedFlag();
            this.createdDateTime = member.getCreatedDateTime();
            this.updatedDateTime = member.getUpdatedDateTime();
            this.agreement = member.getAgreement().copy();
        }

        @Builder
        public MemberResponse(
                Long memberId,
                String emailAddress,
                String phoneNumber,
                String nickname,
                String password,
                GenderConstants gender,
                Agreement agreement,
                RoleConstants role,
                Integer erasedFlag,
                LocalDateTime createdDateTime,
                LocalDateTime updatedDateTime
        ) {
            this.memberId = memberId;
            this.emailAddress = emailAddress;
            this.phoneNumber = phoneNumber;
            this.nickname = nickname;
            this.password = password;
            this.gender = gender;
            this.agreement = agreement;
            this.role = role;
            this.erasedFlag = erasedFlag;
            this.createdDateTime = createdDateTime;
            this.updatedDateTime = updatedDateTime;
        }
    }
}
