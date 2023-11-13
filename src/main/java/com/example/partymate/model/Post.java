package com.example.partymate.model;

import com.example.partymate.dto.PostSaveRequestDto;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author : Unagi_zoso
 * @date : 2023-10-17
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private LocalDate duration;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "party_id")
    private Party party;

    public static Post toPost(PostSaveRequestDto postSaveRequestDto) {
        return Post.builder()
            .title(postSaveRequestDto.getTitle())
            .content(postSaveRequestDto.getContent())
            .duration(postSaveRequestDto.getDuration())
            .build();
    }
}

