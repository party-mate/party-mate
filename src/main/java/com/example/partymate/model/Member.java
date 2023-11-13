package com.example.partymate.model;

import com.example.partymate.dto.MemberSaveRequestDto;
import static com.example.partymate.model.GenderConstants.convertTo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Unagi_zoso
 * @date: 2023-10-12
 */

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

    @Column(length = 256)
    private String profileImageUrl;

    @Column(length = 32, nullable = false)
    private String phoneNumber;

    @Column(length = 32, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private GenderConstants gender;

    @Column(nullable = false)
    private LocalDate birthYearDate;

    @Embedded
    private Agreement agreement;

    public Member copy() {
        return Member.builder()
                .memberId(this.memberId)
                .birthYearDate(this.birthYearDate)
                .emailAddress(this.emailAddress)
                .nickname(this.nickname)
                .password(this.password)
                .gender(this.gender)
                .phoneNumber(this.phoneNumber)
                .profileImageUrl(this.profileImageUrl)
                .agreement(this.agreement.copy())
                .build();
    }

    public static Member toMember(MemberSaveRequestDto memberSaveRequestDto, String profileImageUrl) {
        return  Member.builder()
                .birthYearDate(memberSaveRequestDto.getBirthYearDate())
                .emailAddress(memberSaveRequestDto.getEmailAddress())
                .gender(convertTo(memberSaveRequestDto.getGender()))
                .nickname(memberSaveRequestDto.getNickname())
                .password(memberSaveRequestDto.getPassword())
                .phoneNumber(memberSaveRequestDto.getPhoneNumber())
                .profileImageUrl(profileImageUrl)
                .agreement(Agreement.builder()
                        .agreePrivacyFlag(memberSaveRequestDto.getAgreement().getAgreePrivacyFlag())
                        .agreeServiceFlag(memberSaveRequestDto.getAgreement().getAgreeServiceFlag())
                        .agreeMarketingFlag(memberSaveRequestDto.getAgreement().getAgreeMarketingFlag())
                        .build())
                .build();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberResponse {

        private Long memberId;
        private String emailAddress;
        private String profileImageUrl;
        private String phoneNumber;
        private String nickname;
        private String password;
        private GenderConstants gender;
        private LocalDate birthYearDate;
        private Agreement agreement;
        private Integer erasedFlag;
        private LocalDateTime createdDateTime;
        private LocalDateTime updatedDateTime;

        public MemberResponse(Member member) {
            this.memberId = member.getMemberId();
            this.emailAddress = member.getEmailAddress();
            this.profileImageUrl = member.getProfileImageUrl();
            this.phoneNumber = member.getPhoneNumber();
            this.nickname = member.getNickname();
            this.password = member.getPassword();
            this.gender = member.getGender();
            this.birthYearDate = member.getBirthYearDate();
            this.agreement = member.getAgreement();
            this.erasedFlag = member.getErasedFlag();
            this.createdDateTime = member.getCreatedDateTime();
            this.updatedDateTime = member.getUpdatedDateTime();
            this.agreement = member.getAgreement().copy();
        }

        @Builder
        public MemberResponse(
                Long memberId,
                String emailAddress,
                String profileImageUrl,
                String phoneNumber,
                String nickname,
                String password,
                GenderConstants gender,
                LocalDate birthYearDate,
                Agreement agreement,
                Integer erasedFlag,
                LocalDateTime createdDateTime,
                LocalDateTime updatedDateTime
        ) {
            this.memberId = memberId;
            this.emailAddress = emailAddress;
            this.profileImageUrl = profileImageUrl;
            this.phoneNumber = phoneNumber;
            this.nickname = nickname;
            this.password = password;
            this.gender = gender;
            this.birthYearDate = birthYearDate;
            this.agreement = agreement;
            this.erasedFlag = erasedFlag;
            this.createdDateTime = createdDateTime;
            this.updatedDateTime = updatedDateTime;
        }
    }
}
