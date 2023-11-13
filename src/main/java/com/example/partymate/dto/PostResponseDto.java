package com.example.partymate.dto;

import com.example.partymate.model.CaptionImage;
import com.example.partymate.model.CategoryConstants;
import com.example.partymate.model.Post;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;
import static lombok.AccessLevel.PROTECTED;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Unagi_zoso
 * @since 2023-11-12
 */
@Getter
@NoArgsConstructor(access = PROTECTED)
public class PostResponseDto {
    private Long postId;
    private String title;
    private String nickname;
    private String content;
    private String imageUrl;
    private LocalDate duration;
    private CategoryConstants categoryName;

    @QueryProjection
    public PostResponseDto(
            Long postId,
            String title,
            String nickname,
            String content,
            String imageUrl,
            LocalDate duration,
            CategoryConstants categoryName
    ) {
        this.postId = postId;
        this.title = title;
        this.nickname = nickname;
        this.content = content;
        this.imageUrl = imageUrl;
        this.duration = duration;
        this.categoryName = categoryName;
    }
}
