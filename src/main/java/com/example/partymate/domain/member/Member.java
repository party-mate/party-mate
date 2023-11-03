package com.example.partymate.domain.member;

import com.example.partymate.domain.comment.Comment;
import com.example.partymate.domain.Post.Post;
import com.example.partymate.domain.Role.Role;
import com.example.partymate.domain.memberparty.MemberParty;
import com.example.partymate.domain.util.BaseEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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

    @Column(length = 32, nullable = false)
    private String name;

    @Column(nullable = false)
    private Gender gender;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<MemberParty> memberParties = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "member")
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime birthYearDate;

    @Column(nullable = false)
    private Integer agreeServiceFlag;

    @Column(nullable = false)
    private Integer agreePrivacyFlag;

    @Column(nullable = false)
    private Integer agreeMarketingFlag;

    @Column(nullable = false)
    private Integer erasedFlag; // integer 로 바꿔야하나.. 컨버터 써야할듯 하다
}
