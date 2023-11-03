package com.example.partymate.domain.captionimage;

import com.example.partymate.domain.Post.Post;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class CaptionImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long captionImageId;

    @ManyToOne
    @JoinColumn(name = "captionImages")
    private Post post;

    @Column(length = 511, nullable = false)
    private String imageUrl;

    @Column(length = 511, nullable = false)
    private String thumbnailImageUrl;
}
