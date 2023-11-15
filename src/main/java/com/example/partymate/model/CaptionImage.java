package com.example.partymate.model;

import static javax.persistence.CascadeType.ALL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Unagi_zoso
 * @since 2023-10-17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CaptionImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long captionImageId;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(length = 511, nullable = false)
    private String imageUrl;

    @Column(length = 511, nullable = false)
    private String thumbnailImageUrl;

    public static CaptionImage toCaptionImage(String imageUrl, String thumbnailImageUrl, Post post) {
        return CaptionImage.builder()
                .imageUrl(imageUrl)
                .thumbnailImageUrl(thumbnailImageUrl)
                .post(post)
                .build();
    }
}
