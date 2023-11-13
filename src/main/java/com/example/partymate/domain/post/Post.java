package com.example.partymate.domain.post;

import com.example.partymate.domain.comment.Comment;
import com.example.partymate.domain.captionimage.CaptionImage;
import com.example.partymate.domain.member.Member;
import com.example.partymate.domain.party.Party;
import com.example.partymate.domain.util.BaseEntity;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Unagi_zoso
 * @date : 2023-10-17
 */

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

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "posts")
    private Member member;

    @Column
    private LocalDateTime duration;

    @OneToOne
    @JoinColumn(name = "post")
    private Party party;

    @OneToMany(mappedBy = "post")
    private List<CaptionImage> captionImages;
}
